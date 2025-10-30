package TaoTaoPerto.springBoot.usuarios.model;

import TaoTaoPerto.springBoot.cliente.model.PerfilClienteModel;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.enums.TiposUsuariosEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @Column(name = "tipo_usuario", nullable = false, columnDefinition = "usuarios.tipo_usuario_enum")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM) // <-- 3. ADICIONE ESTA LINHA
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

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EnderecoModel> enderecos;

    @JsonManagedReference
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private PerfilClienteModel perfilCliente;

//    @JsonManagedReference
//    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private ConfiguracaoSegurancaModel configuracaoSeguranca;

//    @JsonManagedReference
//    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private PreferenciaUsuarioModel preferencias;

//    @JsonManagedReference
//    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
//    private PerfilFuncionarioModel perfilFuncionario;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "gestor", fetch = FetchType.LAZY)
//    private List<PerfilFuncionarioModel> subordinados;

    public void atualizarUsuarioComDto(UsuarioDto usuarioDto) {
        if (usuarioDto.getNomeCompleto() != null) {
            this.nomeCompleto = usuarioDto.getNomeCompleto();
        }
        if (usuarioDto.getCpf() != null) {
            this.cpf = usuarioDto.getCpf();
        }
        if (usuarioDto.getCelular() != null) {
            this.celular = usuarioDto.getCelular();
        }
        if (usuarioDto.getDataNascimento() != null) {
            this.dataNascimento = usuarioDto.getDataNascimento();
        }
        if (usuarioDto.getTipoUsuario() != null) {
            this.tipoUsuario = usuarioDto.getTipoUsuario();
        }
        if (usuarioDto.getAtivo() != null) {
            this.ativo = usuarioDto.getAtivo();
        }
    }
}