package TaoTaoPerto.springBoot.usuarios.controller;

import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.services.EnderecoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoServices enderecoServices;

    public EnderecoController(EnderecoServices enderecoServices) {
        this.enderecoServices = enderecoServices;
    }

    //Salvar endereco
    @PostMapping("/registro")
    public ResponseEntity<EnderecoDto> cadastrarNovoEndereco(@RequestBody EnderecoDto enderecoDto){
        EnderecoDto enderecoDtoSalvo = enderecoServices.salvarEndereco(enderecoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(enderecoDtoSalvo);
    }

    //Listar todos os endereços registrado com o id do usuario
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<EnderecoDto>> listarTodosEnderecos(@PathVariable Integer id){
        return ResponseEntity.ok(enderecoServices.buscarTodosEnderecosAtivoDoMesmoUsuario(id));
    }

    //Atualizar endereco pelo id
    @PutMapping("/atualizarEndereco/{id}")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@PathVariable Integer id, EnderecoDto enderecoDto) {
        return ResponseEntity.ok(enderecoServices.atualizarEndereco(id, enderecoDto));
    }

    //Delet logico
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<EnderecoDto> deletLogicoEndereco(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(enderecoServices.deletLogicoEndereco(id));
    }
}
