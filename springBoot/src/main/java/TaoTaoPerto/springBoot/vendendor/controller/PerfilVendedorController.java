package TaoTaoPerto.springBoot.vendendor.controller;

import TaoTaoPerto.springBoot.vendendor.dtos.PerfilVendedorDto;
import TaoTaoPerto.springBoot.vendendor.services.PerfilVendedorServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendedor")
@Tag(name = "Endpoints dos Vendedores", description = "Endpoints para criar, listar, atualizar e deletar endereços de vendedores")
public class PerfilVendedorController {

    private final PerfilVendedorServices perfilVendedorServices;

    public PerfilVendedorController(PerfilVendedorServices perfilVendedorServices) {
        this.perfilVendedorServices = perfilVendedorServices;
    }

    @Operation(summary = "Cadastra um novo Perfil de Vendedor",
            description = "Cria um novo usuário e seu perfil de vendedor associado em uma única requisição (cadastro aninhado).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil de vendedor criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PerfilVendedorDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos (ex: email inválido, CPF duplicado, campos obrigatórios ausentes)",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilVendedorDto> cadastrarPerfilVendedor(@RequestBody PerfilVendedorDto perfilVendedorDto){
        PerfilVendedorDto perfilVendedorDtoSalvo = perfilVendedorServices.salvarPerfil(perfilVendedorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilVendedorDtoSalvo);
    }

}
