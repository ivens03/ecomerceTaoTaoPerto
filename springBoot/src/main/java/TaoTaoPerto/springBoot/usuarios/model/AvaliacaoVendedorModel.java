package TaoTaoPerto.springBoot.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avaliacoes_vendedores", schema = "vendas")
public class AvaliacaoVendedorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_perfil_id", nullable = false)
    private PerfilVendedorModel perfilVendedor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_usuario_id", nullable = false)
    private UsuarioModel cliente;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Short nota;

    @Column(length = 100)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "resposta_vendedor", columnDefinition = "TEXT")
    private String respostaVendedor;

    @Column(name = "data_resposta")
    private Instant dataResposta;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private Instant atualizadoEm;
}
