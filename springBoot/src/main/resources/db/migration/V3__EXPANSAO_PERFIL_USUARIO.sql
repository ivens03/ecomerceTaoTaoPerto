-- V3: Enriquece o schema 'usuarios' com mais informações de Perfil, 
-- segurança e dados funcionais, servindo a todos os tipos de usuário.

-- Adiciona um campo comum de Perfil que estava faltando na V2.
ALTER TABLE usuarios.usuarios
    ADD COLUMN avatar_url VARCHAR(500) NULL;

-- Tabela de Endereços (Relação 1-para-N)
-- Um usuário (cliente, funcionário) pode ter múltiplos endereços.
CREATE TABLE usuarios.enderecos (
    id BIGSERIAL PRIMARY KEY,

     usuario_id BIGINT NOT NULL REFERENCES usuarios.usuarios(id) ON DELETE CASCADE,
    
    -- Rótulo para o endereço (Ex: 'CASA', 'TRABALHO', 'ENTREGA', 'COBRANCA')
    rotulo VARCHAR(50) NOT NULL DEFAULT 'CASA',
    
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    
    -- Reuso das colunas de auditoria do V2
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Reuso do trigger de timestamp criado na V2
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON usuarios.enderecos
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Índice para acelerar a busca de endereços por usuário
CREATE INDEX idx_enderecos_usuario_id ON usuarios.enderecos(usuario_id);

-- Tabela de Configurações de Segurança (Relação 1-para-1)
-- Armazena dados sensíveis de segurança separados da tabela principal.
CREATE TABLE usuarios.configuracoes_seguranca (
    id BIGSERIAL PRIMARY KEY,
    
    -- Chave 1-para-1 com o usuário.
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuarios.usuarios(id) ON DELETE CASCADE,
    
    -- Colunas para Multi-Factor Authentication (MFA / 2FA)
    mfa_habilitado BOOLEAN NOT NULL DEFAULT false,
    mfa_secret_key VARCHAR(255) NULL, -- Chave secreta para apps (Google Auth, etc)
    
    -- Códigos de recuperação (geralmente armazenados como hash)
    recovery_codes TEXT NULL,
    
    -- Data da última vez que a senha foi alterada (para forçar expiração)
    data_ultima_troca_senha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Reuso da coluna de auditoria
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Reuso do trigger de timestamp
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON usuarios.configuracoes_seguranca
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Tabela de Preferências do Usuário (Relação 1-para-1)
-- Armazena preferências de personalização e comunicação.
CREATE TABLE usuarios.preferencias_usuario (
    id BIGSERIAL PRIMARY KEY,
    
    -- Chave 1-para-1 com o usuário.
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuarios.usuarios(id) ON DELETE CASCADE,
    
    -- Preferências de Localização
    idioma VARCHAR(10) NOT NULL DEFAULT 'pt-BR',
    fuso_horario VARCHAR(50) NOT NULL DEFAULT 'America/Sao_Paulo',
    
    -- Preferências de Notificação
    notificar_por_email BOOLEAN NOT NULL DEFAULT true,
    notificar_por_sms BOOLEAN NOT NULL DEFAULT false,
    notificar_por_push BOOLEAN NOT NULL DEFAULT true, -- Para apps mobile
    
    -- Reuso da coluna de auditoria
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Reuso do trigger de timestamp
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON usuarios.preferencias_usuario
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Tabela de Perfil de Funcionário (Relação 1-para-1, Opcional)
-- Guarda dados *apenas* para usuários que são funcionários.
-- Um usuário 'CLIENTE' não terá um registro nesta tabela.
CREATE TABLE usuarios.perfis_funcionarios (
    id BIGSERIAL PRIMARY KEY,
    
    -- Chave 1-para-1 com o usuário.
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuarios.usuarios(id) ON DELETE CASCADE,
    
    matricula VARCHAR(50) UNIQUE, -- Matrícula interna
    cargo VARCHAR(100),           -- Ex: 'Engenheiro de Software'
    departamento VARCHAR(100),    -- Ex: 'Tecnologia'
    
    -- Auto-relacionamento para estrutura de gestão (quem é o líder direto)
    gestor_id BIGINT NULL REFERENCES usuarios.usuarios(id) ON DELETE SET NULL,
    
    data_admissao DATE,
    data_desligamento DATE NULL,
    
    -- Reuso das colunas de auditoria
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Reuso do trigger de timestamp
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON usuarios.perfis_funcionarios
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Trigger de Automação (Criação de Perfis Satélite)
-- Cria automaticamente os registros 1-para-1 (segurança e preferências)
-- que todo usuário deve ter, assim que ele é inserido na tabela principal.
CREATE OR REPLACE FUNCTION usuarios.trigger_criar_perfis_satelite()
RETURNS TRIGGER AS $$
BEGIN
    -- Cria perfis 1-para-1 que todo usuário deve ter
    INSERT INTO usuarios.configuracoes_seguranca (usuario_id) VALUES (NEW.id);
    INSERT INTO usuarios.preferencias_usuario (usuario_id) VALUES (NEW.id);
    
    -- Nota: 'perfis_funcionarios' não é criado aqui, pois é opcional
    -- e deve ser criado pela lógica de negócio (ex: processo de "Contratar Funcionário").
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Anexa o trigger à tabela de usuários (criada na V2)
CREATE TRIGGER criar_perfis_satelite_apos_insert_usuario
    AFTER INSERT ON usuarios.usuarios
    FOR EACH ROW
    EXECUTE FUNCTION usuarios.trigger_criar_perfis_satelite();