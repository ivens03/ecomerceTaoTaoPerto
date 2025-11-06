package TaoTaoPerto.springBoot.produtos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private Long id;
    private Long vendedorPerfilId;
    private String nome;
    private String descricao;
    private String sku;
    private BigDecimal precoBase;
    private BigDecimal precoPromocional;
    private Integer estoqueQuantidade = 0;
    private Integer pesoGramas;
    private String dimensoesJson;
    private Boolean ativo;
    private Boolean conteudoAdulto;
    private Boolean impulsionado = false;
    private BigDecimal notaMedia;
    private Integer totalAvaliacoes;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private Set<Long> categoriasIds;

}
