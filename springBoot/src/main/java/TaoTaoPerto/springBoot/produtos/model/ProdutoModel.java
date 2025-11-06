package TaoTaoPerto.springBoot.produtos.model;

import TaoTaoPerto.springBoot.produtos.dtos.ProdutoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtos", schema = "catalogo")
public class ProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Coluna para a FK de Vendedor (assumindo que a tabela perfis_vendedores existe no schema vendas)
    @Column(name = "vendedor_perfil_id", nullable = false)
    private Long vendedorPerfilId;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "sku", length = 100, nullable = false, unique = true)
    private String sku; // Stock Keeping Unit

    @Column(name = "preco_base", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoBase;

    @Column(name = "preco_promocional", precision = 10, scale = 2)
    private BigDecimal precoPromocional;

    @Column(name = "estoque_quantidade", nullable = false)
    private Integer estoqueQuantidade = 0;

    @Column(name = "peso_gramas")
    private Integer pesoGramas;

    @Column(name = "dimensoes_json", columnDefinition = "TEXT")
    private String dimensoesJson; // Pode ser mapeado como um tipo JSON/Objeto dependendo da biblioteca/Dialect

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "conteudo_adulto", nullable = false)
    private Boolean conteudoAdulto;

    @Column(name = "impulsionado", nullable = false)
    private Boolean impulsionado = false;

    @Column(name = "nota_media", precision = 3, scale = 2, nullable = false)
    private BigDecimal notaMedia = new BigDecimal("0.00");

    @Column(name = "total_avaliacoes", nullable = false)
    private Integer totalAvaliacoes = 0;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "produtos_categorias",
            schema = "catalogo",
            joinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    )
    private Set<CategoriaModel> categorias = new HashSet<>();

    // Relacionamento One-to-Many com Imagens
    //@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<ImagemProdutoModel> imagens = new HashSet<>();

    // Relacionamento One-to-Many com Avaliações
    //@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<AvaliacaoProdutoModel> avaliacoes = new HashSet<>();

    public void atualizarProdutoComDto(ProdutoDto produtoDto) {
        if (produtoDto.getNome() != null) {
            this.nome = produtoDto.getNome();
        }
        if (produtoDto.getDescricao() != null) {
            this.descricao = produtoDto.getDescricao();
        }
        if (produtoDto.getSku() != null) {
            this.sku = produtoDto.getSku();
        }
        if (produtoDto.getPrecoBase() != null) {
            this.precoBase = produtoDto.getPrecoBase();
        }
        if (produtoDto.getPrecoPromocional() != null) {
            this.precoPromocional = produtoDto.getPrecoPromocional();
        }
        if (produtoDto.getEstoqueQuantidade() != null) {
            this.estoqueQuantidade = produtoDto.getEstoqueQuantidade();
        }
        if (produtoDto.getPesoGramas() != null) {
            this.pesoGramas = produtoDto.getPesoGramas();
        }
        if (produtoDto.getDimensoesJson() != null) {
            this.dimensoesJson = produtoDto.getDimensoesJson();
        }
        if (produtoDto.getAtivo() != null) {
            this.ativo = produtoDto.getAtivo();
        }
        if (produtoDto.getConteudoAdulto() != null) {
            this.conteudoAdulto = produtoDto.getConteudoAdulto();
        }
        if (produtoDto.getImpulsionado() != null) {
            this.impulsionado = produtoDto.getImpulsionado();
        }
        if (produtoDto.getNotaMedia() != null) {
            this.notaMedia = produtoDto.getNotaMedia();
        }
        if (produtoDto.getTotalAvaliacoes() != null) {
            this.totalAvaliacoes = produtoDto.getTotalAvaliacoes();
        }
    }
}
