-- V8: Estrutura de Pedidos e Observalidade de Comissão

-- Adiciona a comissão padrão por VENDEDOR
ALTER TABLE vendas.perfis_vendedores
    ADD COLUMN IF NOT EXISTS comissao_percentual_padrao DECIMAL(5, 2) NOT NULL DEFAULT 10.00;

COMMENT ON COLUMN vendas.perfis_vendedores.comissao_percentual_padrao IS
'Percentual de comissão padrão da plataforma para este vendedor (Ex: 10.50 para 10.5%).';

-- Adiciona a comissão específica por CATEGORIA
ALTER TABLE catalogo.categorias
    ADD COLUMN IF NOT EXISTS comissao_percentual_override DECIMAL(5, 2) NULL;

COMMENT ON COLUMN catalogo.categorias.comissao_percentual_override IS
'Percentual de comissão específico para esta categoria. Se NULL, usa a comissão padrão do vendedor.';

CREATE SCHEMA IF NOT EXISTS pedidos;

-- Adequação: Enum para Status do Pedido (só cria se não existir)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'pedido_status_enum') THEN
CREATE TYPE pedidos.pedido_status_enum AS ENUM (
            'CARRINHO',
            'AGUARDANDO_PAGAMENTO',
            'PAGAMENTO_APROVADO',
            'PAGAMENTO_REJEITADO',
            'EM_SEPARACAO',
            'ENVIADO',
            'ENTREGUE',
            'CANCELADO',
            'REEMBOLSADO'
        );
END IF;
END$$;

-- Adequação: Enum para Fonte da Comissão (só cria se não existir)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'comissao_fonte_enum') THEN
CREATE TYPE pedidos.comissao_fonte_enum AS ENUM (
            'PADRAO_VENDEDOR',     -- Usou a taxa da tabela vendas.perfis_vendedores
            'OVERRIDE_CATEGORIA'   -- Usou a taxa da tabela catalogo.categorias
        );
END IF;
END$$;

-- Tabela Principal de Pedidos (Header)
-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS pedidos.pedidos (
    id BIGSERIAL PRIMARY KEY,
    cliente_usuario_id BIGINT NOT NULL REFERENCES usuarios.usuarios(id) ON DELETE RESTRICT,
    endereco_entrega_id BIGINT NULL REFERENCES usuarios.enderecos(id) ON DELETE RESTRICT,
    status pedidos.pedido_status_enum NOT NULL DEFAULT 'AGUARDANDO_PAGAMENTO',
    valor_produtos DECIMAL(10, 2) NOT NULL,
    valor_frete DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    valor_desconto DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    valor_total DECIMAL(10, 2) NOT NULL,
    detalhes_pagamento_json TEXT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Reuso do trigger de timestamp
DROP TRIGGER IF EXISTS set_timestamp ON pedidos.pedidos;
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON pedidos.pedidos
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Tabela de Itens do Pedido (Onde a comissão é auditada)
-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS pedidos.pedido_itens (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT NOT NULL REFERENCES pedidos.pedidos(id) ON DELETE CASCADE,
    vendedor_perfil_id BIGINT NOT NULL REFERENCES vendas.perfis_vendedores(id) ON DELETE RESTRICT,
    produto_id BIGINT NOT NULL REFERENCES catalogo.produtos(id) ON DELETE RESTRICT,
    nome_produto VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL CHECK (quantidade > 0),
    preco_unitario_venda DECIMAL(10, 2) NOT NULL,
    valor_bruto_itens DECIMAL(10, 2) NOT NULL,
    percentual_comissao_aplicado DECIMAL(5, 2) NOT NULL,
    valor_comissao_plataforma DECIMAL(10, 2) NOT NULL,
    valor_liquido_vendedor DECIMAL(10, 2) NOT NULL,
    fonte_comissao pedidos.comissao_fonte_enum NOT NULL
    );

COMMENT ON COLUMN pedidos.pedido_itens.percentual_comissao_aplicado IS
'OBSERVABILIDADE: Qual taxa % foi usada para calcular a comissão (Ex: 12.50).';

COMMENT ON COLUMN pedidos.pedido_itens.valor_comissao_plataforma IS
'OBSERVABILIDADE: O valor final (R$) da comissão da plataforma para este item.';

COMMENT ON COLUMN pedidos.pedido_itens.valor_liquido_vendedor IS
'OBSERVABILIDADE: O valor líquido (Bruto - Comissão) a ser repassado ao vendedor.';

COMMENT ON COLUMN pedidos.pedido_itens.fonte_comissao IS
'OBSERVABILIDADE: De onde veio a regra de comissão (PADRAO_VENDEDOR ou OVERRIDE_CATEGORIA).';

-- Índices (só criam se não existirem)
CREATE INDEX IF NOT EXISTS idx_pedidos_cliente_id ON pedidos.pedidos(cliente_usuario_id);
CREATE INDEX IF NOT EXISTS idx_pedidos_status ON pedidos.pedidos(status);
CREATE INDEX IF NOT EXISTS idx_pedido_itens_pedido_id ON pedidos.pedido_itens(pedido_id);
CREATE INDEX IF NOT EXISTS idx_pedido_itens_vendedor_id ON pedidos.pedido_itens(vendedor_perfil_id);
CREATE INDEX IF NOT EXISTS idx_pedido_itens_produto_id ON pedidos.pedido_itens(produto_id);