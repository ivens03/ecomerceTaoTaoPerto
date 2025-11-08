package TaoTaoPerto.springBoot.usuarios.controller;

import TaoTaoPerto.springBoot.produtos.dtos.CategoriaDto;
import TaoTaoPerto.springBoot.produtos.dtos.ProdutoDto;
import TaoTaoPerto.springBoot.produtos.services.CategoriaServices;
import TaoTaoPerto.springBoot.produtos.services.ProdutoServices;
import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.services.EnderecoServices;
import TaoTaoPerto.springBoot.usuarios.services.UsuarioServices;
import TaoTaoPerto.springBoot.vendendor.dtos.PerfilVendedorDto;
import TaoTaoPerto.springBoot.vendendor.services.PerfilVendedorServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
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
public class ConsultasDeUsuarioGerenciaController {

    private final UsuarioServices usuarioServices;
    private final EnderecoServices enderecoServices;
    private final PerfilVendedorServices perfilVendedorServices;
    private final CategoriaServices categoriaServices;
    private final ProdutoServices produtoServices;

    public ConsultasDeUsuarioGerenciaController(UsuarioServices usuarioServices,
                                                EnderecoServices enderecoServices,
                                                PerfilVendedorServices perfilVendedorServices,
                                                CategoriaServices categoriaServices,
                                                ProdutoServices produtoServices) {
        this.usuarioServices = usuarioServices;
        this.enderecoServices = enderecoServices;
        this.perfilVendedorServices = perfilVendedorServices;
        this.categoriaServices = categoriaServices;
        this.produtoServices = produtoServices;
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
    public ResponseEntity<Optional<UsuarioDto>> listarUsuarioPorId(@PathVariable Long id){
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
                    content = @Content)
    })
    @GetMapping("/listarPorIdAtivo/{id}")
    public ResponseEntity<UsuarioDto> listarUsuarioPorIdAtivo(@PathVariable Long id) {
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

    //Buscar um vendedor por id mesmo não estando ativo no sistema
    @Operation(summary = "Buscar perfil de vendedor por ID (incluindo inativos)",
            description = "Busca um perfil de vendedor específico pelo seu ID, mesmo que o usuário associado esteja inativo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil de vendedor encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PerfilVendedorDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Perfil de vendedor não encontrado",
                    content = @Content)
    })
    @GetMapping("/vendedores/buscar/listar/{id}")
    public ResponseEntity<Optional<PerfilVendedorDto>> buscarPerfilDoVendedor(@PathVariable("id") Long id){
        return ResponseEntity.ok(perfilVendedorServices.listarPerfilDeVendedorPorId(id));
    }

    //Listar todos perfil de vendedor
    @Operation(summary = "Listar todos os perfis de vendedores (incluindo inativos)",
            description = "Retorna uma lista de todos os perfis de vendedores cadastrados, independentemente do status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de perfis de vendedores retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PerfilVendedorDto.class))))
    })
    @GetMapping("/vendedores/listarTodos")
    public ResponseEntity<List<PerfilVendedorDto>> listarTodosPerfilDeVendedor(){
        return ResponseEntity.ok(perfilVendedorServices.listarTodosPerfisDeVendedores());
    }

    //Listar todos perfil de vendedor ativos
    @Operation(summary = "Listar todos os perfis de vendedores ATIVOS",
            description = "Retorna uma lista de todos os perfis de vendedores cujo usuário associado está com status 'ativo'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de perfis de vendedores ativos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PerfilVendedorDto.class))))
    })
    @GetMapping("/vendedores/listarTodosAtivos")
    public ResponseEntity<List<PerfilVendedorDto>> listarTodosPerfilDeVendedorAtivos(){
        return ResponseEntity.ok(perfilVendedorServices.listarTodosPerfisDeVendedoresAtivos());
    }

    // Listar todas as categorias do produtos sendo a categoria ativa ou não.
    @Operation(summary = "Listar todas as categorias de produtos",
            description = "Retorna uma lista de todas as categorias cadastradas, incluindo ativas e inativas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoriaDto.class))))
    })
    @GetMapping("/vendores/registros/categorias")
    public ResponseEntity<List<CategoriaDto>> listarTodasCategorias() {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaServices.listarTodosProdutosPorCategoria());
    }

    // Listar categoria por id ativa ou não ativa
    @GetMapping("/vendores/registros/categorias/{id}")
    public ResponseEntity<CategoriaDto> listarCategoriaPorIdAtivoOuNaoAtivo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaServices.listarCategoriaPorId(id));
    }

    // Listar todos os produtos ativos e não ativos por id
    @GetMapping("/vendores/registros/produtos/{id}")
    public ResponseEntity<Optional<ProdutoDto>> listarProdutoPorIdAtivoOuNaoAtivo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoServices.buscarProdutoPorIdAtivoENaoAtivo(id));
    }

    // Listar todos os produtos ativos e não ativos
    @GetMapping("/vendores/registros/produtos")
    public ResponseEntity<List<ProdutoDto>> listarTodosProdutosAtivosENaoAtivos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoServices.buscarTodosOsProdutosAtivosENaoAtivos());
    }
}
