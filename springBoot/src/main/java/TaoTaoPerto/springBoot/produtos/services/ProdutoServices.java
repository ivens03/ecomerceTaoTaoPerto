package TaoTaoPerto.springBoot.produtos.services;

import TaoTaoPerto.springBoot.produtos.dtos.ProdutoDto;
import TaoTaoPerto.springBoot.produtos.dtos.ProdutoDtoMapper;
import TaoTaoPerto.springBoot.produtos.model.ProdutoModel;
import TaoTaoPerto.springBoot.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServices {

    private final ProdutoRepository produtoRepository;
    private final ProdutoDtoMapper produtoDtoMapper;

    public ProdutoServices(ProdutoRepository produtoRepository,
                           ProdutoDtoMapper produtoDtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoDtoMapper = produtoDtoMapper;
    }

    // Salvar um produto
    public ProdutoDto salvarProduto(ProdutoDto produtoDto) {
        ProdutoModel produtoModel = produtoDtoMapper.map(produtoDto);
        produtoModel.setCriadoEm(LocalDateTime.now());
        produtoModel.setAtualizadoEm(LocalDateTime.now());
        produtoModel = produtoRepository.save(produtoModel);
        return produtoDtoMapper.map(produtoModel);
    }

    // Listar um produto por id ativo
    public Optional<ProdutoDto> buscarProdutoPorId(Long id) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findByIdAndAtivoTrue(id);
        return produtoModel.map(produtoDtoMapper::map);
    }

    // Listar todos os produtos ativos
    public List<ProdutoDto> buscarTodosOsProdutos() {
        List<ProdutoModel> produtoModels = produtoRepository.findAllByAtivoTrue();
        return produtoModels.stream().map(produtoDtoMapper::map).collect(Collectors.toList());
    }

    // Listar todos os produtos ativos e não ativos ***
    public List<ProdutoDto> buscarTodosOsProdutosAtivosENaoAtivos() {
        List<ProdutoModel> produtoModels = produtoRepository.findAll();
        return produtoModels.stream().map(produtoDtoMapper::map).collect(Collectors.toList());
    }

    // Listar todos os produtos ativos e não ativos por id ***
    public Optional<ProdutoDto> buscarProdutoPorIdAtivoENaoAtivo(Long id) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findById(id);
        return produtoModel.map(produtoDtoMapper::map);
    }

    // Atualizar um produto
    public Optional<ProdutoDto> atualizarProduto(Long id, ProdutoDto produtoDto) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findByIdAndAtivoTrue(id);
        if (produtoModel.isPresent()) {
            produtoModel.get().atualizarProdutoComDto(produtoDto);
            produtoModel = Optional.of(produtoRepository.save(produtoModel.get()));
        }
        System.out.println(produtoModel);
        return produtoModel.map(produtoDtoMapper::map);
    }

    // Delete logico
    public void deleteLogico(Long id) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findByIdAndAtivoTrue(id);
        if (produtoModel.isPresent()) {
            produtoModel.get().setAtivo(false);
            produtoRepository.save(produtoModel.get());
        }
    }
}
