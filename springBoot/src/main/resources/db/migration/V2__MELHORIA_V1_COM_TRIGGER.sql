-- Criação dos schemas
CREATE SCHEMA IF NOT EXISTS usuarios;
CREATE SCHEMA IF NOT EXISTS auditoria;

-- Adequação: Cria o ENUM apenas se ele não existir
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'tipo_usuario_enum') THEN
CREATE TYPE usuarios.tipo_usuario_enum AS ENUM (
            'CLIENTE',
            'ENTREGADORES',
            'GERENTES',
            'SUPORTE',
            'VENDEDORES'
        );
END IF;
END$$;

CREATE OR REPLACE FUNCTION auditoria.trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.atualizado_em = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS usuarios.usuarios (
                                                 id BIGSERIAL PRIMARY KEY,
                                                 email VARCHAR(60) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nome_completo VARCHAR(255),
    cpf VARCHAR(11) UNIQUE,
    celular VARCHAR(15) UNIQUE,
    data_nascimento DATE,

    tipo_usuario usuarios.tipo_usuario_enum NOT NULL DEFAULT 'CLIENTE',
    ativo BOOLEAN NOT NULL DEFAULT true,

    ------ Colunas de auditoria ---
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    desativado_em TIMESTAMP NULL,

    ------ Colunas de Estado de Segurança ---
    ultimo_login_em TIMESTAMP NULL,
    ultimo_login_ip VARCHAR(45) NULL,
    tentativas_falhas_login INT NOT NULL DEFAULT 0
    );

-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS auditoria.log_auditoria_usuarios (
                                                                id BIGSERIAL PRIMARY KEY,
                                                                usuario_id BIGINT, -- Quem sofreu a ação (pode ser nulo se for ação do sistema)

                                                                ator_usuario_id BIGINT REFERENCES usuarios.usuarios(id),
    acao VARCHAR(50) NOT NULL, -- Ex: 'LOGIN_SUCESSO', 'UPDATE_PERFIL', 'DESATIVOU_CONTA'
    ip_acao VARCHAR(45) NOT NULL,

    detalhes_antes TEXT,  -- JSON de como o registro 'usuarios' estava antes
    detalhes_depois TEXT, -- JSON de como o registro 'usuarios' ficou depois

    timestamp_acao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_id) REFERENCES usuarios.usuarios(id) ON DELETE SET NULL
    );

-- Anexar triggers (comandos CREATE OR REPLACE são seguros)
DROP TRIGGER IF EXISTS set_timestamp ON usuarios.usuarios;
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON usuarios.usuarios
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();