package TaoTaoPerto.springBoot.cliente.controller;

import TaoTaoPerto.springBoot.cliente.dtos.PerfilClienteDto;
import TaoTaoPerto.springBoot.cliente.services.PerfilClienteServices;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.services.UsuarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "EndPoint dos clientes", description = "Endpoints para o cadastro e gerenciamento da conta do usuário (self-service)")
@RestController
@RequestMapping("/ususarios")
public class UsuariosController {

    private final UsuarioServices usuarioServices;
    private final PerfilClienteServices perfilClienteServices;

    public UsuariosController(UsuarioServices usuarioServices, PerfilClienteServices perfilClienteServices) {
        this.usuarioServices = usuarioServices;
        this.perfilClienteServices = perfilClienteServices;
    }

    @Operation(summary = "Cadastra um novo usuário cliente",
            description = "Cria um novo usuário (CLIENTE) e seu perfil de cliente associado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cliente cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PerfilClienteDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos no JSON",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno (ex: violação de constraint do banco)",
                    content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilClienteDto> salvarUsuario(@Valid @RequestBody PerfilClienteDto usuarioDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilClienteServices.salvarPerfilCliente(usuarioDto));
    }

    @Operation(summary = "Busca os dados da conta do usuário ativo",
            description = "Retorna os dados públicos de um usuário específico, desde que ele esteja ativo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou inativo",
                    content = @Content)
    })
    @GetMapping("/conta/{id}")
    public ResponseEntity<UsuarioDto> listarUsuarioPorIdAtivo(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioServices.listarUsuarioPorIdAtivo(id));
    }

    @Operation(summary = "Atualiza os dados da conta do usuário",
            description = "Atualiza dados de um usuário existente (ex: nome, senha).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário atualizado com sucesso (retorna o DTO atualizado)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos no JSON",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @PutMapping("/conta/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuarioPorIdAtivo(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServices.atualizarUsuario(id, usuarioDto));
    }

    @Operation(summary = "Desativa a conta do usuário (Delete Lógico)",
            description = "Realiza a exclusão lógica de um usuário, setando seu status para 'inativo'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário desativado com sucesso (Sem conteúdo no corpo)",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/conta/{id}")
    public ResponseEntity<Void> deletLogicoUsuario(@PathVariable Long id){
        usuarioServices.deletLogicoUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}