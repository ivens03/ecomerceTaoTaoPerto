-- V7: Cria as estruturas para o Catálogo de Produtos
-- Define os "produtos para venda" dos vendedores.

-- 1. Cria um schema dedicado para o Catálogo
CREATE SCHEMA IF NOT EXISTS catalogo;

-- 2. Tabela de Categorias (Para organização dos produtos)
-- Adequação: Adicionado IF NOT EXISTS (Corrige o erro do log)
CREATE TABLE IF NOT EXISTS catalogo.categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT NULL,
    categoria_pai_id BIGINT NULL REFERENCES catalogo.categorias(id) ON DELETE SET NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Reuso do trigger de timestamp
DROP TRIGGER IF EXISTS set_timestamp ON catalogo.categorias;
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON catalogo.categorias
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- 3. Tabela Principal de Produtos
-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS catalogo.produtos (
    id BIGSERIAL PRIMARY KEY,
    vendedor_perfil_id BIGINT NOT NULL REFERENCES vendas.perfis_vendedores(id) ON DELETE CASCADE,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NULL,
    sku VARCHAR(100) UNIQUE NOT NULL, -- Stock Keeping Unit
    preco_base DECIMAL(10, 2) NOT NULL,
    preco_promocional DECIMAL(10, 2) NULL,
    estoque_quantidade INT NOT NULL DEFAULT 0,
    peso_gramas INT NULL,
    dimensoes_json TEXT NULL, -- Ex: {"altura_cm": 10, "largura_cm": 5, "profundidade_cm": 5}
    ativo BOOLEAN NOT NULL DEFAULT true, -- Se o produto está visível na loja
    conteudo_adulto BOOLEAN NOT NULL DEFAULT false,
    impulsionado BOOLEAN NOT NULL DEFAULT false,
    nota_media DECIMAL(3, 2) NOT NULL DEFAULT 0.00,
    total_avaliacoes INT NOT NULL DEFAULT 0,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Reuso do trigger de timestamp
DROP TRIGGER IF EXISTS set_timestamp ON catalogo.produtos;
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON catalogo.produtos
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- 4. Tabela de Junção (N-para-N) entre Produtos e Categorias
-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS catalogo.produtos_categorias (
                                                            produto_id BIGINT NOT NULL REFERENCES catalogo.produtos(id) ON DELETE CASCADE,
    categoria_id BIGINT NOT NULL REFERENCES catalogo.categorias(id) ON DELETE CASCADE,
    PRIMARY KEY (produto_id, categoria_id)
    );

-- 5. Tabela de Imagens dos Produtos
-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS catalogo.imagens_produtos (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL REFERENCES catalogo.produtos(id) ON DELETE CASCADE,
    imagem_url VARCHAR(500) NOT NULL,
    alt_text VARCHAR(150),
    ordem SMALLINT NOT NULL DEFAULT 0 -- 0 = Imagem principal
    );

-- 6. Tabela de Avaliações de Produtos
-- Adequação: Adicionado IF NOT EXISTS
CREATE TABLE IF NOT EXISTS catalogo.avaliacoes_produtos (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL REFERENCES catalogo.produtos(id) ON DELETE CASCADE,
    cliente_usuario_id BIGINT NOT NULL REFERENCES usuarios.usuarios(id) ON DELETE SET NULL,
    nota SMALLINT NOT NULL CHECK (nota >= 1 AND nota <= 5),
    titulo VARCHAR(100) NULL,
    comentario TEXT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Reuso do trigger de timestamp
DROP TRIGGER IF EXISTS set_timestamp ON catalogo.avaliacoes_produtos;
CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON catalogo.avaliacoes_produtos
    FOR EACH ROW
    EXECUTE FUNCTION auditoria.trigger_set_timestamp();

-- Índices (só criam se não existirem)
CREATE INDEX IF NOT EXISTS idx_produtos_vendedor_id ON catalogo.produtos(vendedor_perfil_id);
CREATE INDEX IF NOT EXISTS idx_produtos_sku ON catalogo.produtos(sku);
CREATE INDEX IF NOT EXISTS idx_imagens_produto_id ON catalogo.imagens_produtos(produto_id);
CREATE INDEX IF NOT EXISTS idx_avaliacoes_produto_id ON catalogo.avaliacoes_produtos(produto_id);