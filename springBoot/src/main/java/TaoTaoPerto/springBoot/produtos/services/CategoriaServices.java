package TaoTaoPerto.springBoot.produtos.services;

import TaoTaoPerto.springBoot.produtos.dtos.CategoriaDto;
import TaoTaoPerto.springBoot.produtos.dtos.CategoriaDtoMapper;
import TaoTaoPerto.springBoot.produtos.model.CategoriaModel;
import TaoTaoPerto.springBoot.produtos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServices {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaDtoMapper categoriaDtoMapper;

    public CategoriaServices(CategoriaRepository categoriaRepository, CategoriaDtoMapper categoriaDtoMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaDtoMapper = categoriaDtoMapper;
    }

    // Salvar uma categoria
    public CategoriaModel salvarCategoriaDeProduto(CategoriaModel categoriaModel) {
        return categoriaRepository.save(categoriaModel);
    }

    // Listar todas as categorias
    public List<CategoriaDto> listarTodosProdutosPorCategoria() {
        return categoriaRepository
                .findAll()
                .stream()
                .map(categoriaDtoMapper::map)
                .toList();
    }

    // Listar categoria por id
    public CategoriaDto listarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaDtoMapper::map)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));
    }

    // Atualizar categoria
    public CategoriaDto atualizarCategoria(Long id, CategoriaDto categoriaDto) {
           var categoriaOptional = categoriaRepository.findById(id);
           if (categoriaOptional.isEmpty()) {
               throw new RuntimeException("Categoria nao encontrada");
           }
           var categoria = categoriaOptional.get();
           categoria.atualizarCategoriaComDto(categoriaDto);
           return categoriaDtoMapper.map(categoriaRepository.save(categoria));
    }

    // Delet logico
    public void deletarCategoria(Long id) {
        var categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("Categoria nao encontrada");
        }
        var categoria = categoriaOptional.get();
        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }
}
