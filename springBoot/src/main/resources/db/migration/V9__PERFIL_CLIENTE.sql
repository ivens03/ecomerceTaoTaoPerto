-- V9: Cria o perfil especializado (1-para-1) para Clientes
-- Segue o padrão de 'perfis_vendedores' e 'perfis_funcionarios'
-- Armazena dados específicos do *papel* de cliente, como pontos e preferências.

-- 1. Tabela de Perfil do Cliente
-- Relação 1-para-1 com usuarios.usuarios
CREATE TABLE IF NOT EXISTS usuarios.perfis_clientes (
    id BIGSERIAL PRIMARY KEY,

    -- Chave 1-para-1 com o usuário.
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuarios.usuarios(id) ON DELETE CASCADE,

    -- Ex: Pontos de fidelidade acumulados
    pontos_fidelidade BIGINT NOT NULL DEFAULT 0,

    -- Ex: Um código de indicação único para este cliente (Ex: IVENS-123)
    codigo_indicacao VARCHAR(20) UNIQUE,

    -- Ex: ID do endereço preferido (default) para checkout
    -- Refere-se a um endereço da tabela 'usuarios.enderecos'
    endereco_padrao_id BIGINT NULL REFERENCES usuarios.enderecos(id) ON DELETE SET NULL,

    -- (Exemplo futuro) Ex: ID do método de pagamento preferido
    -- metodo_pagamento_padrao_id BIGINT NULL,

    -- Reuso das colunas de auditoria
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Reuso do trigger de timestamp
DROP TRIGGER IF EXISTS set_timestamp ON usuarios.perfis_clientes;
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON usuarios.perfis_clientes
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Índice para performance
CREATE INDEX IF NOT EXISTS idx_perfis_clientes_codigo_indicacao ON usuarios.perfis_clientes(codigo_indicacao);


-- 2. Automação (Melhoria do Trigger da V3)
-- Modifica o trigger 'trigger_criar_perfis_satelite'
-- para *também* criar o perfil de cliente automaticamente quando
-- um usuário do tipo 'CLIENTE' for inserido.

-- Primeiro, remove o trigger antigo da V3 (se ele existir)
DROP TRIGGER IF EXISTS criar_perfis_satelite_apos_insert_usuario ON usuarios.usuarios;

-- Recria a FUNÇÃO (com a nova lógica)
CREATE OR REPLACE FUNCTION usuarios.trigger_criar_perfis_satelite_v2()
RETURNS TRIGGER AS $$
BEGIN
    -- Perfis 1-para-1 que TODO usuário deve ter (da V3)
INSERT INTO usuarios.configuracoes_seguranca (usuario_id) VALUES (NEW.id);
INSERT INTO usuarios.preferencias_usuario (usuario_id) VALUES (NEW.id);

-- NOVO (V9): Se o usuário for CLIENTE, cria o perfil de cliente
IF NEW.tipo_usuario = 'CLIENTE' THEN
        INSERT INTO usuarios.perfis_clientes (usuario_id) VALUES (NEW.id);
END IF;

    -- Nota: 'perfis_funcionarios' e
    -- 'perfis_vendedores' não são criados aqui,
    -- pois são criados por lógicas de negócio específicas (Ex: "Contratar", "Virar Vendedor").

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Anexa o novo trigger (com novo nome) à tabela de usuários
CREATE TRIGGER criar_perfis_satelite_v2_apos_insert_usuario
    AFTER INSERT ON usuarios.usuarios
    FOR EACH ROW
    EXECUTE FUNCTION usuarios.trigger_criar_perfis_satelite_v2();