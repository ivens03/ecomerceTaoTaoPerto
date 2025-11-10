package TaoTaoPerto.springBoot.usuarios.controller;

import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.services.EnderecoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints dos Endereços",
        description = "Endpoints para criar, listar, atualizar e deletar endereços de usuários.")
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoServices enderecoServices;

    public EnderecoController(EnderecoServices enderecoServices) {
        this.enderecoServices = enderecoServices;
    }

    //Salvar endereco
    @Operation(summary = "Cadastrar um novo endereço",
            description = "Registra um novo endereço e o associa ao usuário dono do DTO. (Requer validação)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", // Assumindo que @Valid será implementado
                    content = @Content)
    })
    @PostMapping("/registro")
    public ResponseEntity<EnderecoDto> cadastrarNovoEndereco(@RequestBody EnderecoDto enderecoDto){
        EnderecoDto enderecoDtoSalvo = enderecoServices.salvarEndereco(enderecoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(enderecoDtoSalvo);
    }

    //Listar todos os endereços registrado com o id do usuario
    @Operation(summary = "Listar endereços ativos de um usuário",
            description = "Retorna uma lista de todos os endereços com status 'ativo' de um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EnderecoDto.class)))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou inativo",
                    content = @Content)
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<EnderecoDto>> listarTodosEnderecos(@PathVariable Long id){
        return ResponseEntity.ok(enderecoServices.buscarTodosEnderecosAtivoDoMesmoUsuario(id));
    }

    //Atualizar endereco pelo id
    @Operation(summary = "Atualizar um endereço",
            description = "Atualiza os dados de um endereço específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário ou Endereço não encontrado",
                    content = @Content)
    })
    @PatchMapping("/atualizarEndereco/{id}")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoDto enderecoDto) {
        return ResponseEntity.ok(enderecoServices.atualizarEndereco(id, enderecoDto));
    }

    //Delet logico
    @Operation(summary = "Desativar um endereço (Delete Lógico)",
            description = "Muda o status do endereço para 'inativo' e retorna o endereço atualizado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço desativado com sucesso (retorna o objeto)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoDto.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<EnderecoDto> deletLogicoEndereco(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(enderecoServices.deletLogicoEndereco(id));
    }
}
