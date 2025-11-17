package TaoTaoPerto.springBoot.vendendor.model;

import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import TaoTaoPerto.springBoot.vendendor.dtos.PerfilVendedorDto;
import TaoTaoPerto.springBoot.vendendor.enums.TipoPessoaEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "usuario_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa", nullable = false)
    private TipoPessoaEnum tipoPessoa;

    @Size(max = 255)
    @Column(name = "razao_social")
    private String razaoSocial;

    @Size(min = 14, max = 14, message = "O CNPJ deve ter exatamente 14 caracteres.")
    @Column(name = "cnpj", length = 14, unique = true)
    private String cnpj;

    @Size(max = 20)
    @Column(name = "inscricao_estadual", length = 20)
    private String inscricaoEstadual;

    // Métodos 'helper' para sincronizar o relacionamento
    public void addAvaliacao(AvaliacaoVendedorModel avaliacao) {
        this.avaliacoes.add(avaliacao);
        avaliacao.setPerfilVendedor(this);
    }

    // Metodod para atualizar
    public void atualizarPerfilVendedorComDto(PerfilVendedorDto perfilVendedorDto) {
        if (perfilVendedorDto.getCnpj() != null) {
            this.cnpj = perfilVendedorDto.getCnpj();
        }
        if (perfilVendedorDto.getDescricao() != null) {
            this.descricao = perfilVendedorDto.getDescricao();
        }
        if (perfilVendedorDto.getLogoUrl() != null) {
            this.logoUrl = perfilVendedorDto.getLogoUrl();
        }
        if (perfilVendedorDto.getBannerUrl() != null) {
            this.bannerUrl = perfilVendedorDto.getBannerUrl();
        }
        if (perfilVendedorDto.getNotaMedia() != null) {
            this.notaMedia = perfilVendedorDto.getNotaMedia();
        }
        if (perfilVendedorDto.getTotalAvaliacoes() != null) {
            this.totalAvaliacoes = perfilVendedorDto.getTotalAvaliacoes();
        }
        if (perfilVendedorDto.getTipoPessoa() != null) {
            this.tipoPessoa = perfilVendedorDto.getTipoPessoa();
        }
        if (perfilVendedorDto.getRazaoSocial() != null) {
            this.razaoSocial = perfilVendedorDto.getRazaoSocial();
        }
        if (perfilVendedorDto.getInscricaoEstadual() != null) {
            this.inscricaoEstadual = perfilVendedorDto.getInscricaoEstadual();
        }
        if (perfilVendedorDto.getNomeLoja() != null) {
            this.nomeLoja = perfilVendedorDto.getNomeLoja();
        }
    }

}
