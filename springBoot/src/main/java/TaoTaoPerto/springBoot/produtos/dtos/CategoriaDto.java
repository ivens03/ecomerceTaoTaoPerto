package TaoTaoPerto.springBoot.produtos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto {

    private Long id;
    private String nome;
    private String descricao;
    private Long categoriaPaiId;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private Boolean ativo;

}
