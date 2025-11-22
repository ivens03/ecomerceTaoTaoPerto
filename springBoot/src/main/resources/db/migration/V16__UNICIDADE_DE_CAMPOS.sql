-- V16: Torna as colunas CPF e Celular obrigatórias (NOT NULL) E únicas (UNIQUE)
-- na tabela principal de usuários.
--
-- MOTIVAÇÃO:
-- 1. Unicidade: Garante que um mesmo CPF ou Celular não possa ser cadastrado
--    mais de uma vez na base, mesmo que o e-mail seja diferente (problema original).
-- 2. Obrigatoriedade: Garante que os campos não possam ser nulos, o que anularia
--    a eficácia do índice UNIQUE no PostgreSQL.

-- PASSO 1: Aplica a restrição NOT NULL na coluna CPF.
ALTER TABLE usuarios.usuarios
    ALTER COLUMN cpf SET NOT NULL;

-- PASSO 2: Adiciona a restrição UNIQUE na coluna CPF.
ALTER TABLE usuarios.usuarios
    ADD CONSTRAINT uq_usuarios_cpf UNIQUE (cpf);

COMMENT ON COLUMN usuarios.usuarios.cpf IS
    'Obrigatório e Único. NOT NULL e UNIQUE aplicados na V16 para garantir a unicidade de dados.';


-- PASSO 3: Aplica a restrição NOT NULL na coluna Celular.
ALTER TABLE usuarios.usuarios
    ALTER COLUMN celular SET NOT NULL;

-- PASSO 4: Adiciona a restrição UNIQUE na coluna Celular.
ALTER TABLE usuarios.usuarios
    ADD CONSTRAINT uq_usuarios_celular UNIQUE (celular);

COMMENT ON COLUMN usuarios.usuarios.celular IS
    'Obrigatório e Único. NOT NULL e UNIQUE aplicados na V16 para garantir a unicidade de dados.';