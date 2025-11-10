package TaoTaoPerto.springBoot.usuarios.dtos;

import TaoTaoPerto.springBoot.usuarios.enums.TipoMoradiaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

    private Long id;
    private Long usuarioId;

    @NotBlank(message = "O rótulo é obrigatório")
    @Size(max = 50, message = "O rótulo deve ter no máximo 50 caracteres")
    private String rotulo;

    @NotBlank(message = "O CEP é obrigatório")
    @Size(max = 9, message = "O CEP deve ter no máximo 9 caracteres") // Pode usar @Pattern para regex
    private String cep;

    @NotBlank(message = "O logradouro é obrigatório")
    @Size(max = 255, message = "O logradouro deve ter no máximo 255 caracteres")
    private String logradouro;

    @Size(max = 20, message = "O número deve ter no máximo 20 caracteres")
    private String numero;

    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
    private String complemento;

    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @Size(min = 2, max = 2, message = "O estado deve ter exatamente 2 caracteres")
    private String estado;

    //@NotNull(message = "O tipo de moradia é obrigatório (CASA ou CONDOMINIO)")
    private TipoMoradiaEnum tipoMoradia;

    private Boolean ativo;
}