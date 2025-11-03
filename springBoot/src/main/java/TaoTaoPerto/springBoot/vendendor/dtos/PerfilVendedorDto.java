package TaoTaoPerto.springBoot.vendendor.dtos;

import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilVendedorDto {

    private Long id;

    @Valid
    @NotNull(message = "Os dados do usuário são obrigatórios.")
    private UsuarioDto usuario;

    @NotNull(message = "O nome da loja é obrigatório.")
    @Size(max = 150, message = "O nome da loja não pode exceder 150 caracteres.")
    private String nomeLoja;

    private String descricao;

    @Size(max = 500, message = "A URL do logo não pode exceder 500 caracteres.")
    private String logoUrl;

    @Size(max = 500, message = "A URL do banner não pode exceder 500 caracteres.")
    private String bannerUrl;

    @DecimalMin("0.0")
    private BigDecimal notaMedia;

    private Integer totalAvaliacoes;
    private Instant criadoEm;
    private Instant atualizadoEm;

}
