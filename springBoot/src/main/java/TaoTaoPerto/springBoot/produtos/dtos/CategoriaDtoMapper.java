package TaoTaoPerto.springBoot.produtos.dtos;

import TaoTaoPerto.springBoot.produtos.model.CategoriaModel;
import org.springframework.stereotype.Component;

@Component
public class CategoriaDtoMapper {

    public CategoriaModel map(CategoriaDto categoriaDto) {
        if (categoriaDto == null) {
            return null;
        }

        CategoriaModel categoriaModel = new CategoriaModel();
        categoriaModel.setId(categoriaDto.getId());
        categoriaModel.setNome(categoriaDto.getNome());
        categoriaModel.setDescricao(categoriaDto.getDescricao());
        categoriaModel.setCriadoEm(categoriaDto.getCriadoEm());
        categoriaModel.setAtualizadoEm(categoriaDto.getAtualizadoEm());
        categoriaModel.setAtivo(categoriaDto.getAtivo());

        if (categoriaDto.getCategoriaPaiId() != null) {
            CategoriaModel categoriaPai = new CategoriaModel();
            categoriaPai.setId(categoriaDto.getCategoriaPaiId());
            categoriaModel.setCategoriaPai(categoriaPai);
        }

        return categoriaModel;
    }

    public CategoriaDto map(CategoriaModel categoriaModel) {
        if (categoriaModel == null) {
            return null;
        }

        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(categoriaModel.getId());
        categoriaDto.setNome(categoriaModel.getNome());
        categoriaDto.setDescricao(categoriaModel.getDescricao());
        categoriaDto.setCriadoEm(categoriaModel.getCriadoEm());
        categoriaDto.setAtualizadoEm(categoriaModel.getAtualizadoEm());
        categoriaDto.setAtivo(categoriaModel.getAtivo());

        if (categoriaModel.getCategoriaPai() != null) {
            categoriaDto.setCategoriaPaiId(categoriaModel.getCategoriaPai().getId());
        }

        return categoriaDto;
    }

}
