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
    @JoinColumn(name = "id", nullable = false, unique = true)
    private UsuarioModel usuario;

    @NotNull
    @Column(name = "nome_loja", nullable = false, unique = true, length = 150)
    private String nomeLoja; // Mapeia 'nome_loja VARCHAR(150) NOT NULL UNIQUE'

    @Column(columnDefinition = "TEXT")
    private String descricao; // Mapeia 'descricao TEXT NULL'

    @Column(name = "logo_url", length = 500)
    private String logoUrl; // Mapeia 'logo_url VARCHAR(500) NULL'

    @Column(name = "banner_url", length = 500)
    private String bannerUrl; // Mapeia 'banner_url VARCHAR(500) NULL'

    @NotNull
    @DecimalMin("0.0")
    @Column(name = "nota_media", nullable = false, precision = 3, scale = 2)
    private BigDecimal notaMedia = BigDecimal.ZERO; // Mapeia 'nota_media DECIMAL(3, 2) NOT NULL DEFAULT 0.00'

    @NotNull
    @Column(name = "total_avaliacoes", nullable = false)
    private Integer totalAvaliacoes = 0;

    @OneToMany(
            mappedBy = "perfilVendedor",
            cascade = CascadeType.ALL, // Se apagar o perfil, apaga as avaliações
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<AvaliacaoVendedorModel> avaliacoes = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm; // Mapeia 'criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP'

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private Instant atualizadoEm; // Mapeia 'atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP'

    // (Opcional) Métodos 'helper' para sincronizar o relacionamento
    public void addAvaliacao(AvaliacaoVendedorModel avaliacao) {
        this.avaliacoes.add(avaliacao);
        avaliacao.setPerfilVendedor(this);
    }
}
