package TaoTaoPerto.springBoot.usuarios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfis_entregadores", schema = "usuarios")
public class PerfilEntregadorModel implements Serializable {

    @Id
    @Column(name = "usuario_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private UsuarioModel usuario;

    @Column(name = "placa_veiculo", length = 10, unique = true)
    private String placaVeiculo;

    @Column(name = "cnh_categoria", length = 5)
    private String cnhCategoria;

    @Column(name = "em_entrega")
    private Boolean emEntrega = false;
}
