package TaoTaoPerto.springBoot.vendendor.controller;

import TaoTaoPerto.springBoot.vendendor.dtos.PerfilVendedorDto;
import TaoTaoPerto.springBoot.vendendor.services.PerfilVendedorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendedor")
public class PerfilVendedorController {

    private final PerfilVendedorServices perfilVendedorServices;

    public PerfilVendedorController(PerfilVendedorServices perfilVendedorServices) {
        this.perfilVendedorServices = perfilVendedorServices;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilVendedorDto> cadastrarPerfilVendedor(@RequestBody PerfilVendedorDto perfilVendedorDto){
        PerfilVendedorDto perfilVendedorDtoSalvo = perfilVendedorServices.salvarPerfil(perfilVendedorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilVendedorDtoSalvo);
    }

}
