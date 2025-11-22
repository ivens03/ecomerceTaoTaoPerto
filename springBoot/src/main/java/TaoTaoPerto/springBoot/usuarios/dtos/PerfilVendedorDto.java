package TaoTaoPerto.springBoot.usuarios.dtos;

import TaoTaoPerto.springBoot.usuarios.enums.TipoPessoaEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    @NotNull(message = "O tipo de pessoa é obrigatório. Valores aceitos: [PF, PJ]")
    private TipoPessoaEnum tipoPessoa;

    @Size(max = 255, message = "A Razão Social não pode exceder 255 caracteres.")
    private String razaoSocial;

    @Size(min = 14, max = 14, message = "O CNPJ deve ter exatamente 14 caracteres.")
    private String cnpj;

    @Size(max = 20, message = "A Inscrição Estadual não pode exceder 20 caracteres.")
    private String inscricaoEstadual;
}
