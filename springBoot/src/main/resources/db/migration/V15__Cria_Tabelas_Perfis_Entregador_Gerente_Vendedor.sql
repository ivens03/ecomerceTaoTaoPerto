-- V15__Cria_Tabelas_Perfis_Entregador_Gerente_Vendedor.sql

-- 1. Tabela de ENTREGADORES
DROP TABLE IF EXISTS usuarios.perfis_entregadores CASCADE;

CREATE TABLE usuarios.perfis_entregadores (
    usuario_id BIGINT NOT NULL PRIMARY KEY,
    placa_veiculo VARCHAR(10) UNIQUE,
    cnh_categoria VARCHAR(5),
    em_entrega BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_perfil_entregador_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios.usuarios (id)
    ON DELETE CASCADE
);

-- 2. Tabela de GERENTES
DROP TABLE IF EXISTS usuarios.perfis_gerentes CASCADE;

CREATE TABLE usuarios.perfis_gerentes (
    usuario_id BIGINT NOT NULL PRIMARY KEY,
    matricula VARCHAR(20) UNIQUE,
    data_admissao DATE,
    nivel_acesso INTEGER,
    CONSTRAINT fk_perfil_gerente_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios.usuarios (id)
    ON DELETE CASCADE
);

-- 3. Tabela de VENDEDORES (Schema: vendas)
CREATE SCHEMA IF NOT EXISTS vendas;

-- !!! AQUI ESTÁ A CORREÇÃO CRUCIAL !!!
-- Como a V6 cria essa tabela, precisamos derrubá-la antes de recriar a nova versão
DROP TABLE IF EXISTS vendas.perfis_vendedores CASCADE;

CREATE TABLE vendas.perfis_vendedores (
    usuario_id BIGINT NOT NULL PRIMARY KEY,

    nome_loja VARCHAR(150) NOT NULL UNIQUE,
    descricao TEXT,
    logo_url VARCHAR(500),
    banner_url VARCHAR(500),

    nota_media NUMERIC(3, 2) NOT NULL DEFAULT 0.00,
    total_avaliacoes INTEGER NOT NULL DEFAULT 0,

    tipo_pessoa VARCHAR(255) NOT NULL,
    razao_social VARCHAR(255),
    cnpj VARCHAR(14) UNIQUE,
    inscricao_estadual VARCHAR(20),

    criado_em TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    atualizado_em TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_perfil_vendedor_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios.usuarios (id)
    ON DELETE CASCADE
);