package TaoTaoPerto.springBoot.vendendor.model;

import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfis_vendedores", schema = "vendas")
public class PerfilVendedorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id"/*, nullable = false, unique = true*/)
    private UsuarioModel usuario;

    @NotNull
    @Column(name = "nome_loja", nullable = false, unique = true, length = 150)
    private String nomeLoja;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(name = "banner_url", length = 500)
    private String bannerUrl;

    @NotNull
    @DecimalMin("0.0")
    @Column(name = "nota_media", nullable = false, precision = 3, scale = 2)
    private BigDecimal notaMedia = BigDecimal.ZERO;

    @NotNull
    @Column(name = "total_avaliacoes", nullable = false)
    private Integer totalAvaliacoes = 0;

    @OneToMany(
            mappedBy = "perfilVendedor",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<AvaliacaoVendedorModel> avaliacoes = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private Instant atualizadoEm;

    // Métodos 'helper' para sincronizar o relacionamento
    public void addAvaliacao(AvaliacaoVendedorModel avaliacao) {
        this.avaliacoes.add(avaliacao);
        avaliacao.setPerfilVendedor(this);
    }
}
