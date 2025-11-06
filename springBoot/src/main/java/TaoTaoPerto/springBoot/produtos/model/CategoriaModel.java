package TaoTaoPerto.springBoot.produtos.model;

import TaoTaoPerto.springBoot.produtos.dtos.CategoriaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorias", schema = "catalogo")
public class CategoriaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    // Relacionamento de hierarquia: Categoria pode ter uma Categoria Pai
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_pai_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private CategoriaModel categoriaPai;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    @ManyToMany(mappedBy = "categorias")
    private Set<ProdutoModel> produtos = new HashSet<>();

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public void atualizarCategoriaComDto(CategoriaDto categoriaDto) {
        if (categoriaDto.getNome() != null) {
            this.nome = categoriaDto.getNome();
        }
        if (categoriaDto.getDescricao() != null) {
            this.descricao = categoriaDto.getDescricao();
        }
        if (categoriaDto.getAtivo() != null) {
            this.ativo = categoriaDto.getAtivo();
        }
        if (categoriaDto.getCategoriaPaiId() != null) {
            CategoriaModel pai = new CategoriaModel();
            pai.setId(categoriaDto.getCategoriaPaiId());
            this.categoriaPai = pai;
        } else {
            this.categoriaPai = null;
        }
    }
}
