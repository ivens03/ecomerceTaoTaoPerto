-- VAI ALTERAR A COLUNA CRIADA NA V1
ALTER TABLE usuarios.usuarios
-- 1. Altera o tipo da coluna
ALTER COLUMN tipo_usuario TYPE usuarios.tipo_usuario_enum
    -- 2. Diz ao Postgres como converter os dados antigos (VARCHAR) para o novo (ENUM)
    USING (tipo_usuario::usuarios.tipo_usuario_enum),
    -- 3. Define o valor DEFAULT que V1 não tinha
    ALTER COLUMN tipo_usuario SET DEFAULT 'CLIENTE';

-- (O resto do V2, como a criação do log_auditoria e triggers, pode ficar)