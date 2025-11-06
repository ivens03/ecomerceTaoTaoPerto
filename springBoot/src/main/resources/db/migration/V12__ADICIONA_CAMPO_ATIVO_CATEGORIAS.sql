-- V12: Adiciona o campo 'ativo' à tabela catalogo.categorias
-- Este campo permite desativar categorias sem remover o histórico.

ALTER TABLE catalogo.categorias
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;