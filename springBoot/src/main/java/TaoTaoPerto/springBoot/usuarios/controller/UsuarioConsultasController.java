package TaoTaoPerto.springBoot.usuarios.controller;

import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.services.EnderecoServices;
import TaoTaoPerto.springBoot.usuarios.services.UsuarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = " Endpoints para consulta de dados.",
        description = "Endpoint responsável pela visualização gerentes terem as informações dos usuarios fazendo consultas")
@RestController
@RequestMapping("/gerentes/dados/usuarios")
public class UsuarioConsultasController {

    private final UsuarioServices usuarioServices;
    private final EnderecoServices enderecoServices;

    public UsuarioConsultasController(UsuarioServices usuarioServices, EnderecoServices enderecoServices) {
        this.usuarioServices = usuarioServices;
        this.enderecoServices = enderecoServices;
    }

    //Listar todos mesmo não ativos
    @Operation(summary = "Listar todos os usuários (incluindo inativos)",
            description = "Retorna uma lista de todos os usuários cadastrados no sistema, independentemente do status (ativo ou inativo).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsuarioDto.class))))
    })
    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioDto>> listarTodosUsuarios(){
        return ResponseEntity.ok(usuarioServices.buscarTodos());
    }

    //Buscar por id mesmo não ativo
    @Operation(summary = "Buscar usuário por ID (incluindo inativos)",
            description = "Busca um usuário específico pelo seu ID. Retorna o usuário se encontrado, ou um corpo vazio se não encontrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso (retorna o usuário ou corpo vazio)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)))
    })
    @GetMapping("/listarPorId/{id}")
    public ResponseEntity<Optional<UsuarioDto>> listarUsuarioPorId(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioServices.listarUsuarioPorId(id));
    }

    //Listar todos ativos
    @GetMapping("/listarTodosAtivos")
    @Operation(summary = "Listar todos os usuários ativos",
            description = "Retorna uma lista de todos os usuários que estão com o status 'ativo' no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários ativos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsuarioDto.class))))
    })
    public ResponseEntity<List<UsuarioDto>> listarTodosAtivos(){
        return ResponseEntity.ok(usuarioServices.buscarTodosAtivos());
    }

    //Buscar por id sendo ativo
    @Operation(summary = "Buscar usuário ativo por ID",
            description = "Busca e retorna um usuário específico pelo seu ID, mas somente se ele estiver ativo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário ativo encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou está inativo",
                    content = @Content) // Corpo vazio para 404
    })
    @GetMapping("/listarPorIdAtivo/{id}")
    public ResponseEntity<UsuarioDto> listarUsuarioPorIdAtivo(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioServices.listarUsuarioPorIdAtivo(id));
    }

    // Listar todos os endereços de todos os usuários
    @GetMapping("/todosEnderecos")
    @Operation(summary = "Listar todos os endereços de todos os usuários",
            description = "Retorna uma lista consolidada de todos os endereços cadastrados para todos os usuários no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EnderecoDto.class))))
    })
    public ResponseEntity<List<EnderecoDto>> listarTodosEnderecos(){
        return ResponseEntity.ok(enderecoServices.buscarTodosEnderecosDeTodosOsUsuarios());
    }
}
