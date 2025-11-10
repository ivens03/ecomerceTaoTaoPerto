-- V14: Adiciona Tipo de Moradia e torna 'complemento' condicional
-- Esta migração refina a tabela de endereços, diferenciando
-- 'CASA' de 'CONDOMINIO' e ajustando a regra de negócio do campo 'complemento'.

-- 1. Reverte a obrigatoriedade de 'complemento' definida na V13
-- Vamos remover o 'NOT NULL' para que ele possa ser nulo (para 'CASA')
ALTER TABLE usuarios.enderecos
    ALTER COLUMN complemento DROP NOT NULL;

-- 2. Cria o ENUM para o Tipo de Moradia
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'tipo_moradia_enum') THEN
CREATE TYPE usuarios.tipo_moradia_enum AS ENUM (
            'CASA',
            'CONDOMINIO'
        );
END IF;
END$$;

-- 3. Adiciona a coluna 'tipo_moradia' à tabela de endereços
-- Por padrão, definimos como 'CASA' (a opção mais segura)
ALTER TABLE usuarios.enderecos
    ADD COLUMN IF NOT EXISTS tipo_moradia usuarios.tipo_moradia_enum NOT NULL DEFAULT 'CASA';

COMMENT ON COLUMN usuarios.enderecos.tipo_moradia IS 'Diferencia se o endereço é uma CASA (complemento opcional) ou CONDOMINIO (complemento obrigatório).';

-- 4. Atualiza dados existentes (Heurística)
-- Se o complemento (preenchido antes da V13) NÃO for uma string vazia,
-- podemos assumir que era um 'CONDOMINIO'.
UPDATE usuarios.enderecos
SET tipo_moradia = 'CONDOMINIO'
WHERE complemento IS NOT NULL AND complemento != '';

-- 5. Limpa os complementos vazios ('') definidos na V13 para 'CASA'
-- Agora que revertemos o NOT NULL, podemos voltar a usar NULL
-- para endereços do tipo 'CASA' que tinham sido forçados a ser ''.
UPDATE usuarios.enderecos
SET complemento = NULL
WHERE tipo_moradia = 'CASA' AND complemento = '';

-- 6. Adiciona a nova regra de negócio (CHECK Constraint)
-- Esta é a regra principal:
-- 1. Se for 'CASA', o complemento PODE ser nulo.
-- 2. Se for 'CONDOMINIO', o complemento NÃO PODE ser nulo ou vazio.
ALTER TABLE usuarios.enderecos
DROP CONSTRAINT IF EXISTS chk_complemento_obrigatorio_condominio;

ALTER TABLE usuarios.enderecos
    ADD CONSTRAINT chk_complemento_obrigatorio_condominio
        CHECK (
            (tipo_moradia = 'CASA')
                OR
            (tipo_moradia = 'CONDOMINIO' AND complemento IS NOT NULL AND complemento != '')
            );

COMMENT ON CONSTRAINT chk_complemento_obrigatorio_condominio ON usuarios.enderecos IS 'Garante que se o tipo de moradia for CONDOMINIO, o campo complemento não pode ser nulo ou vazio.';