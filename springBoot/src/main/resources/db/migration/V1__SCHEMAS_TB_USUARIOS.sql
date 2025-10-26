-- Criação da tabelas de usuarios e Criação dos schemas.

CREATE SCHEMA IF NOT EXISTS usuarios;
CREATE SCHEMA IF NOT EXISTS auditoria;

CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,

    email VARCHAR(60) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,

    nome_completo VARCHAR (255) NOT NULL,
    cpf VARCHAR(11),
    celular VARCHAR(15),
    data_dascimento DATE,

    tipo_usuario VARCHAR(20) CHECK (tipo_usuario IN ('CLIENTE', 'ENTREGADORES' ,'GERENTES', 'SUPORTE', 'VENDEDORES')),
    ativo BOOLEAN NOT NULL DEFAULT true,

------ Colunas de Estado/Auditoria ---
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    desativado_em TIMESTAMP NULL,

------ Colunas de Estado de Segurança ---
    ultimo_login_em TIMESTAMP NULL,
    ultimo_login_ip VARCHAR(45) NULL,
    tentativas_falhas_login INT NOT NULL DEFAULT 0
);

CREATE TABLE log_auditoria_usuarios (
    id BIGSERIAL PRIMARY KEY,

    usuario_id BIGINT,

    acao VARCHAR(50) NOT NULL, -- Ex: 'LOGIN_SUCESSO', 'LOGIN_FALHA', 'UPDATE_SENHA', 'CRIOU_CONTA'
    ip_acao VARCHAR(45) NOT NULL,

    detalhes_antes TEXT,
    detalhes_depois TEXT,

    timestamp_acao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE SET NULL
);