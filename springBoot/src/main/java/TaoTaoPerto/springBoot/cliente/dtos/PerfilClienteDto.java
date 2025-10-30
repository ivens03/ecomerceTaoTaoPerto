package TaoTaoPerto.springBoot.cliente.dtos;

import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilClienteDto {

    private Long id;
    @Valid
    @NotNull(message = "Os dados do usuário são obrigatórios.")
    private UsuarioDto usuario;
    private Long pontosFidelidade = 0L;
    private String codigoIndicacao;
    @Valid
    private EnderecoDto enderecoPadrao;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

}
