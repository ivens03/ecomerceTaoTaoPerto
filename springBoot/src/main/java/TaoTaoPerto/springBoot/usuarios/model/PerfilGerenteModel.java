package TaoTaoPerto.springBoot.usuarios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfis_gerentes", schema = "usuarios")
public class PerfilGerenteModel implements Serializable {

    @Id
    @Column(name = "usuario_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private UsuarioModel usuario;

    @Column(name = "matricula", length = 20, unique = true)
    private String matricula;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "nivel_acesso")
    private Integer nivelAcesso;

}
