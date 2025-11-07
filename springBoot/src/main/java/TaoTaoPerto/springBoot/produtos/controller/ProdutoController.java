package TaoTaoPerto.springBoot.produtos.controller;

import TaoTaoPerto.springBoot.produtos.dtos.ProdutoDto;
import TaoTaoPerto.springBoot.produtos.services.ProdutoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoServices produtoServices;

    public ProdutoController(ProdutoServices produtoServices) {
        this.produtoServices = produtoServices;
    }

    // salvar produto
    @PostMapping("/registrar")
    public ResponseEntity<ProdutoDto> salvarProduto(@RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServices.salvarProduto(produtoDto));
    }

    // atualizar produto
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Optional<ProdutoDto>> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoServices.atualizarProduto(id, produtoDto));
    }

    // Delete logico
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteLogico(@PathVariable Long id) {
        produtoServices.deleteLogico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
