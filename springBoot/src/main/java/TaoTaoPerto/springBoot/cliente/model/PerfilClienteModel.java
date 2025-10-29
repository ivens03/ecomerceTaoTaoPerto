package TaoTaoPerto.springBoot.cliente.model;

import TaoTaoPerto.springBoot.usuarios.model.EnderecoModel;
import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfis_clientes", schema = "usuarios")
public class PerfilClienteModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private UsuarioModel usuario;

    @Column(name = "pontos_fidelidade", nullable = false)
    private Long pontosFidelidade = 0L;

    @Column(name = "codigo_indicacao", length = 20, unique = true)
    private String codigoIndicacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_padrao_id")
    private EnderecoModel enderecoPadrao;

    @Generated(GenerationTime.INSERT)
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "atualizado_em", nullable = false, insertable = false)
    private LocalDateTime atualizadoEm;

}