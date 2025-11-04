-- V11: Adiciona dados Fiscais (PF/PJ) ao perfil do vendedor
-- (Versão corrigida para usar VARCHAR, compatível com Java @Enumerated(EnumType.STRING))

-- 1. Altera a tabela de perfis de vendedores para incluir os campos de PJ
ALTER TABLE vendas.perfis_vendedores

    -- CORREÇÃO: Remove a criação do ENUM nativo.
    -- Cria a coluna como VARCHAR(10) para aceitar as strings 'PF' ou 'PJ' vindas do Java.
    ADD COLUMN IF NOT EXISTS tipo_pessoa VARCHAR(10) NOT NULL DEFAULT 'PF',

    -- Adiciona a Razão Social (Obrigatório se for PJ)
    ADD COLUMN IF NOT EXISTS razao_social VARCHAR(255) NULL,

    -- Adiciona o CNPJ (Obrigatório e único se for PJ)
    ADD COLUMN IF NOT EXISTS cnpj VARCHAR(14) NULL,

    -- Adiciona a Inscrição Estadual (Opcional)
    ADD COLUMN IF NOT EXISTS inscricao_estadual VARCHAR(20) NULL;

-- 2. Adiciona uma constraint de unicidade para o CNPJ
CREATE UNIQUE INDEX IF NOT EXISTS idx_perfis_vendedores_cnpj_unico
    ON vendas.perfis_vendedores(cnpj)
    WHERE (cnpj IS NOT NULL);

-- 3. Adiciona a constraint de checagem
-- (Agora baseada em texto VARCHAR)
ALTER TABLE vendas.perfis_vendedores
DROP CONSTRAINT IF EXISTS chk_dados_pj_obrigatorios;

ALTER TABLE vendas.perfis_vendedores
    ADD CONSTRAINT chk_dados_pj_obrigatorios
        CHECK (
            -- Se for PF, não checa nada
            (tipo_pessoa = 'PF')
                OR
                -- Se for PJ, CNPJ e Razão Social não podem ser nulos
            (tipo_pessoa = 'PJ' AND cnpj IS NOT NULL AND razao_social IS NOT NULL)
            );

COMMENT ON COLUMN vendas.perfis_vendedores.tipo_pessoa IS 'Define se o vendedor opera como PF ou PJ (compatível com Java EnumType.STRING).';
COMMENT ON COLUMN vendas.perfis_vendedores.razao_social IS 'Razão Social da empresa (Obrigatório se tipo_pessoa = PJ).';
COMMENT ON COLUMN vendas.perfis_vendedores.cnpj IS 'CNPJ da empresa (Obrigatório e Único se tipo_pessoa = PJ).';