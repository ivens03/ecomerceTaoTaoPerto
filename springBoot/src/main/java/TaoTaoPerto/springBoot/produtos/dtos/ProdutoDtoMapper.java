package TaoTaoPerto.springBoot.produtos.dtos;

import TaoTaoPerto.springBoot.produtos.model.CategoriaModel;
import TaoTaoPerto.springBoot.produtos.model.ProdutoModel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProdutoDtoMapper {

    public ProdutoModel map(ProdutoDto produtoDto) {

        if (produtoDto == null) {
            return null;
        }

        ProdutoModel produtoModel = new ProdutoModel();

        produtoModel.setId(produtoDto.getId());
        produtoModel.setVendedorPerfilId(produtoDto.getVendedorPerfilId());
        produtoModel.setNome(produtoDto.getNome());
        produtoModel.setDescricao(produtoDto.getDescricao());
        produtoModel.setSku(produtoDto.getSku());
        produtoModel.setPrecoBase(produtoDto.getPrecoBase());
        produtoModel.setPrecoPromocional(produtoDto.getPrecoPromocional());
        produtoModel.setEstoqueQuantidade(produtoDto.getEstoqueQuantidade());
        produtoModel.setPesoGramas(produtoDto.getPesoGramas());
        produtoModel.setDimensoesJson(produtoDto.getDimensoesJson());
        produtoModel.setAtivo(produtoDto.getAtivo());
        produtoModel.setConteudoAdulto(produtoDto.getConteudoAdulto());
        produtoModel.setImpulsionado(produtoDto.getImpulsionado());
        produtoModel.setNotaMedia(produtoDto.getNotaMedia());
        produtoModel.setTotalAvaliacoes(produtoDto.getTotalAvaliacoes());
        produtoModel.setCriadoEm(produtoDto.getCriadoEm());
        produtoModel.setAtualizadoEm(produtoDto.getAtualizadoEm());

        if (produtoDto.getCategoriasIds() != null) {
            Set<CategoriaModel> categorias = produtoDto.getCategoriasIds().stream()
                    .map(id -> {
                        CategoriaModel categoria = new CategoriaModel();
                        categoria.setId(id);
                        return categoria;
                    })
                    .collect(Collectors.toSet());
            produtoModel.setCategorias(categorias);
        }

        return produtoModel;
    }

    public ProdutoDto map(ProdutoModel produtoModel) {

        if (produtoModel == null) {
            return null;
        }

        ProdutoDto produtoDto = new ProdutoDto();

        produtoDto.setId(produtoModel.getId());
        produtoDto.setVendedorPerfilId(produtoModel.getVendedorPerfilId());
        produtoDto.setNome(produtoModel.getNome());
        produtoDto.setDescricao(produtoModel.getDescricao());
        produtoDto.setSku(produtoModel.getSku());
        produtoDto.setPrecoBase(produtoModel.getPrecoBase());
        produtoDto.setPrecoPromocional(produtoModel.getPrecoPromocional());
        produtoDto.setEstoqueQuantidade(produtoModel.getEstoqueQuantidade());
        produtoDto.setPesoGramas(produtoModel.getPesoGramas());
        produtoDto.setDimensoesJson(produtoModel.getDimensoesJson());
        produtoDto.setAtivo(produtoModel.getAtivo());
        produtoDto.setConteudoAdulto(produtoModel.getConteudoAdulto());
        produtoDto.setImpulsionado(produtoModel.getImpulsionado());
        produtoDto.setNotaMedia(produtoModel.getNotaMedia());
        produtoDto.setTotalAvaliacoes(produtoModel.getTotalAvaliacoes());
        produtoDto.setCriadoEm(produtoModel.getCriadoEm());
        produtoDto.setAtualizadoEm(produtoModel.getAtualizadoEm());

        if (produtoModel.getCategorias() != null) {
            Set<Long> ids = produtoModel.getCategorias().stream()
                    .map(CategoriaModel::getId)
                    .collect(Collectors.toSet());
            produtoDto.setCategoriasIds(ids);
        }

        return produtoDto;
    }
}
