package TaoTaoPerto.springBoot.usuarios.controller;

import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.services.EnderecoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
