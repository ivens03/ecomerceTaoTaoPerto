-- V5: Adiciona Soft Delete (Exclusão Lógica) na tabela de endereços
-- Esta coluna permite que um endereço seja "desativado" pelo usuário
-- sem ser fisicamente excluído do banco, preservando assim o
-- histórico de pedidos que possam ter feito referência a ele.

ALTER TABLE usuarios.enderecos
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT true;

COMMENT ON COLUMN usuarios.enderecos.ativo IS
'Controle de exclusão lógica. True = Visível para o usuário; False = Oculto (excluído).';

-- Melhoria para performance:
-- Atualiza o índice existente (criado na V3) que buscava apenas pelo usuario_id.
-- Este novo índice é otimizado para a consulta mais comum do frontend:
-- "Quais são os endereços ATIVOS deste usuário?"

-- Remove o índice antigo (se existir)
DROP INDEX IF EXISTS usuarios.idx_enderecos_usuario_id;

-- Cria o novo índice composto, otimizado para buscar (usuario_id + ativo)
-- O 'WHERE ativo = true' o torna um índice parcial, muito eficiente.
CREATE INDEX idx_enderecos_usuario_id_ativo ON usuarios.enderecos(usuario_id, ativo)
    WHERE ativo = true;