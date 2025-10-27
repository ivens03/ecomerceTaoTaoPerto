package TaoTaoPerto.springBoot.usuarios.dtos;

import TaoTaoPerto.springBoot.usuarios.enums.TiposUsuariosEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    @Size(max = 60, message = "O e-mail deve ter no máximo 60 caracteres.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, max = 100)
    private String senha;

    @NotBlank(message = "O nome completo é obrigatório.")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres.")
    private String nomeCompleto;

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 caracteres.")
    private String cpf;

    @Size(max = 15, message = "O celular deve ter no máximo 15 caracteres.")
    private String celular;

    @Past(message = "A data de nascimento deve ser no passado.")
    private LocalDate dataNascimento;
    
    private TiposUsuariosEnum tipoUsuario = TiposUsuariosEnum.CLIENTE;

    private Boolean ativo;
}
