package TaoTaoPerto.springBoot.usuarios.model;

import TaoTaoPerto.springBoot.usuarios.enums.TiposUsuariosEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Generated;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "usuarios", schema = "usuarios")
public class UsuarioModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    @Column(name = "nome_completo", length = 255)
    private String nomeCompleto;

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @Column(name = "celular", length = 15, unique = true)
    private String celular;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TiposUsuariosEnum tipoUsuario = TiposUsuariosEnum.CLIENTE;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    /**
     * Este campo é gerenciado pelo 'DEFAULT CURRENT_TIMESTAMP' do banco.
     * @Generated(EventType.INSERT) diz ao Hibernate para reler o valor do banco
     * após um INSERT. 'updatable = false' garante que o Hibernate nunca o altere.
     */
    @Generated(GenerationTime.INSERT)
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    /**
     * Este campo é gerenciado pelo TRIGGER 'auditoria.trigger_set_timestamp'.
     * @Generated(EventType.ALWAYS) diz ao Hibernate para reler o valor do banco
     * após CADA UPDATE. 'insertable = false' é uma otimização.
     */
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "atualizado_em", nullable = false, insertable = false)
    private LocalDateTime atualizadoEm;

    @Column(name = "desativado_em")
    private LocalDateTime desativadoEm;

    // --- Colunas de Estado de Segurança ---

    @Column(name = "ultimo_login_em")
    private LocalDateTime ultimoLoginEm;

    @Column(name = "ultimo_login_ip", length = 45)
    private String ultimoLoginIp;

    @Column(name = "tentativas_falhas_login", nullable = false)
    private Integer tentativasFalhasLogin = 0;
}