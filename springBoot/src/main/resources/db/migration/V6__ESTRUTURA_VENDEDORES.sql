-- V6: Cria as estruturas para Vendedores (Lojas) e Avaliações
-- O usuário 'VENDEDOR' terá um perfil de loja associado.

-- Schema dedicado para Vendas e Lojas
CREATE SCHEMA IF NOT EXISTS vendas;

-- Tabela de Perfil do Vendedor (Loja)
-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS vendas.perfis_vendedores (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuarios.usuarios(id) ON DELETE CASCADE,
    nome_loja VARCHAR(150) NOT NULL UNIQUE,
    descricao TEXT NULL,
    logo_url VARCHAR(500) NULL,
    banner_url VARCHAR(500) NULL,
    nota_media DECIMAL(3, 2) NOT NULL DEFAULT 0.00,
    total_avaliacoes INT NOT NULL DEFAULT 0,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Reuso do trigger de timestamp criado na V2
DROP TRIGGER IF EXISTS set_timestamp ON vendas.perfis_vendedores;
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON vendas.perfis_vendedores
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Tabela de Avaliações do Vendedor
-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS vendas.avaliacoes_vendedores (
                                                            id BIGSERIAL PRIMARY KEY,
                                                            vendedor_perfil_id BIGINT NOT NULL REFERENCES vendas.perfis_vendedores(id) ON DELETE CASCADE,
    cliente_usuario_id BIGINT NOT NULL REFERENCES usuarios.usuarios(id) ON DELETE SET NULL,
    nota SMALLINT NOT NULL CHECK (nota >= 1 AND nota <= 5),
    titulo VARCHAR(100) NULL,
    comentario TEXT NULL,
    resposta_vendedor TEXT NULL,
    data_resposta TIMESTAMP NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Reuso do trigger de timestamp
DROP TRIGGER IF EXISTS set_timestamp ON vendas.avaliacoes_vendedores;
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON vendas.avaliacoes_vendedores
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Índices (só criam se não existirem)
CREATE INDEX IF NOT EXISTS idx_avaliacoes_vendedor_id ON vendas.avaliacoes_vendedores(vendedor_perfil_id);
CREATE INDEX IF NOT EXISTS idx_avaliacoes_cliente_id ON vendas.avaliacoes_vendedores(cliente_usuario_id);