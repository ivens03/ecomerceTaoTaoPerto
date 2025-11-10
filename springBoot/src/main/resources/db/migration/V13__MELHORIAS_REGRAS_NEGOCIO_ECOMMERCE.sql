-- V13: Melhorias nas Regras de Negócio e Coleta de Métricas
-- Esta migração aplica restrições de nulidade mais rígidas
-- e adiciona novas tabelas e colunas para rastreamento de
-- interações de clientes e métricas de vendedores.

-- 1. Tabela usuarios.enderecos
-- Aplica NOT NULL em colunas que a regra de negócio definiu como obrigatórias.

-- NOTA: Atualizamos valores NULOS existentes para padrões ('S/N', 'N/A', '')
-- para que a aplicação da restrição NOT NULL não falhe em bancos com dados.
UPDATE usuarios.enderecos SET numero = 'S/N' WHERE numero IS NULL;
UPDATE usuarios.enderecos SET complemento = '' WHERE complemento IS NULL;
UPDATE usuarios.enderecos SET bairro = 'N/A' WHERE bairro IS NULL;

-- Aplica as restrições NOT NULL
ALTER TABLE usuarios.enderecos
    ALTER COLUMN numero SET NOT NULL,
ALTER COLUMN complemento SET NOT NULL,
    ALTER COLUMN bairro SET NOT NULL;

-- 2. Tabela usuarios.perfis_funcionarios
-- Adiciona a coluna 'funcao' e aplica restrições NOT NULL
-- para dados obrigatórios do funcionário.

-- Adiciona a nova coluna 'funcao' com um valor default
-- para preencher registros existentes e permitir o NOT NULL.
ALTER TABLE usuarios.perfis_funcionarios
    ADD COLUMN IF NOT EXISTS funcao VARCHAR(255) NOT NULL DEFAULT 'Função não definida';

COMMENT ON COLUMN usuarios.perfis_funcionarios.funcao IS 'Descrição detalhada das responsabilidades ou função do cargo.';

-- Aplica as restrições NOT NULL nas colunas existentes
-- ATENÇÃO: Se houver funcionários existentes com cargo, data_admissao ou
-- departamento NULOS, esta migração FALHARÁ. (Isso é esperado,
-- pois os dados existentes precisam ser corrigidos manualmente se violarem a nova regra).
ALTER TABLE usuarios.perfis_funcionarios
    ALTER COLUMN cargo SET NOT NULL,
ALTER COLUMN data_admissao SET NOT NULL,
    ALTER COLUMN departamento SET NOT NULL;

-- 3. Tabela catalogo.categorias
-- Adiciona flag de 'conteudo_adulto' a nível de categoria.

ALTER TABLE catalogo.categorias
    ADD COLUMN IF NOT EXISTS conteudo_adulto BOOLEAN NOT NULL DEFAULT FALSE;

COMMENT ON COLUMN catalogo.categorias.conteudo_adulto IS 'Se TRUE, indica que a categoria (e por herança, seus produtos) é de conteúdo adulto.';

-- 4. Métricas de Vendedor e Interação do Cliente
-- Cria uma nova estrutura para rastrear cliques/visualizações
-- e adiciona colunas de contagem no perfil do vendedor.

-- 4.1. Cria um novo schema para rastreamento de interações
CREATE SCHEMA IF NOT EXISTS interacoes;

-- 4.2. Cria o ENUM para os tipos de interação
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'interacao_tipo_enum') THEN
CREATE TYPE interacoes.interacao_tipo_enum AS ENUM (
            'VISUALIZACAO_PRODUTO', -- Cliente viu a página de um produto
            'CLICK_PRODUTO',      -- Cliente clicou em um produto (ex: num card de busca)
            'VISUALIZACAO_LOJA',    -- Cliente viu o perfil da loja/vendedor
            'PESQUISA_TERMO'      -- Cliente realizou uma pesquisa
        );
END IF;
END$$;

-- 4.3. Cria a tabela de log de interações
CREATE TABLE IF NOT EXISTS interacoes.log_interacao_cliente (
    id BIGSERIAL PRIMARY KEY,

    -- Quem fez a ação
    usuario_id BIGINT NOT NULL REFERENCES usuarios.usuarios(id) ON DELETE CASCADE,

    -- O que foi visto/clicado
    produto_id BIGINT NULL REFERENCES catalogo.produtos(id) ON DELETE SET NULL,
    vendedor_perfil_id BIGINT NULL REFERENCES vendas.perfis_vendedores(id) ON DELETE SET NULL,

    -- Detalhes da ação
    tipo_interacao interacoes.interacao_tipo_enum NOT NULL,
    termo_pesquisa VARCHAR(255) NULL, -- Preenchido se tipo_interacao = 'PESQUISA_TERMO'

    timestamp_interacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

COMMENT ON TABLE interacoes.log_interacao_cliente IS 'Registra eventos de navegação do cliente (cliques, visualizações, pesquisas) para alimentar métricas e preferências.';
COMMENT ON COLUMN interacoes.log_interacao_cliente.produto_id IS 'O produto que foi visto ou clicado.';
COMMENT ON COLUMN interacoes.log_interacao_cliente.vendedor_perfil_id IS 'A loja que foi vista (ou a loja dona do produto visto).';

-- Índices para otimizar consultas de métricas
CREATE INDEX IF NOT EXISTS idx_log_interacao_usuario_id ON interacoes.log_interacao_cliente(usuario_id);
CREATE INDEX IF NOT EXISTS idx_log_interacao_produto_id ON interacoes.log_interacao_cliente(produto_id);
CREATE INDEX IF NOT EXISTS idx_log_interacao_vendedor_id ON interacoes.log_interacao_cliente(vendedor_perfil_id);
CREATE INDEX IF NOT EXISTS idx_log_interacao_tipo ON interacoes.log_interacao_cliente(tipo_interacao);


-- 4.4. Adiciona colunas de contagem (desnormalizadas) em vendas.perfis_vendedores
ALTER TABLE vendas.perfis_vendedores
    ADD COLUMN IF NOT EXISTS visualizacoes_perfil BIGINT NOT NULL DEFAULT 0,
    ADD COLUMN IF NOT EXISTS visualizacoes_produtos BIGINT NOT NULL DEFAULT 0;

COMMENT ON COLUMN vendas.perfis_vendedores.visualizacoes_perfil IS 'Contador (desnormalizado) de visualizações do perfil da loja. Deve ser incrementado pela aplicação.';
COMMENT ON COLUMN vendas.perfis_vendedores.visualizacoes_produtos IS 'Contador (desnormalizado) de visualizações de todos os produtos deste vendedor. Deve ser incrementado pela aplicação.';