package TaoTaoPerto.springBoot.produtos.controller;

import TaoTaoPerto.springBoot.produtos.dtos.CategoriaDto;
import TaoTaoPerto.springBoot.produtos.model.CategoriaModel;
import TaoTaoPerto.springBoot.produtos.services.CategoriaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categoria/produtos")
public class CategoriaController {


    private final CategoriaServices categoriaServices;

    public CategoriaController(CategoriaServices categoriaServices) {
        this.categoriaServices = categoriaServices;
    }

    // Salvar
    @PostMapping("/registar")
    public ResponseEntity<CategoriaModel> salvarCategoriaDeProduto(@RequestBody CategoriaModel categoriaModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServices.salvarCategoriaDeProduto(categoriaModel));
    }

    // Listar categoria por id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> listarCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaServices.listarCategoriaPorId(id));
    }

    // Atualizar categoria
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServices.atualizarCategoria(id, categoriaDto));
    }

    // Delet logico de uma categoria
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaServices.deletarCategoria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
