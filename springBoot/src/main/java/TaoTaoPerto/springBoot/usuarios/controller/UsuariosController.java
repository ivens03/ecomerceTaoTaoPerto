package TaoTaoPerto.springBoot.usuarios.controller;

import TaoTaoPerto.springBoot.cliente.dtos.PerfilClienteDto;
import TaoTaoPerto.springBoot.cliente.services.PerfilClienteServices;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.services.UsuarioServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ususarios")
public class UsuariosController {

    private final UsuarioServices usuarioServices;
    private final PerfilClienteServices perfilClienteServices;

    public UsuariosController(UsuarioServices usuarioServices, PerfilClienteServices perfilClienteServices) {
        this.usuarioServices = usuarioServices;
        this.perfilClienteServices = perfilClienteServices;
    }

    //Salvar usando o service PerfilClientesServices
    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilClienteDto> salvarUsuario(@RequestBody PerfilClienteDto usuarioDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilClienteServices.salvarPerfilCliente(usuarioDto));
    }

    //Listar por id
    @GetMapping("/conta/{id}")
    public ResponseEntity<UsuarioDto> listarUsuarioPorIdAtivo(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioServices.listarUsuarioPorIdAtivo(id));
    }

    //Atualizar por id
    @PutMapping("/conta/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuarioPorIdAtivo(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServices.atualizarUsuario(id, usuarioDto));
    }

    //Deletar por id
    @DeleteMapping("/conta/{id}")
    public ResponseEntity<UsuarioDto> deletLogicoUsuario(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioServices.deletLogicoUsuario(id));
    }
}
