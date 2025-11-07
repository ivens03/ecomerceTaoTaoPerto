package TaoTaoPerto.springBoot.produtos.controller;

import TaoTaoPerto.springBoot.produtos.dtos.ProdutoDto;
import TaoTaoPerto.springBoot.produtos.services.ProdutoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
