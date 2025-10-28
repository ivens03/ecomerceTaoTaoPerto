package TaoTaoPerto.springBoot.usuarios.controller;

import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.services.UsuarioServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerentes/dados/usuarios")
public class UsuarioConsultasController {

    private final UsuarioServices usuarioServices;

    public UsuarioConsultasController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    //Listar todos mesmo não ativos
    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioDto>> listarTodosUsuarios(){
        return ResponseEntity.ok(usuarioServices.buscarTodos());
    }

    //Buscar por id mesmo não ativo
    @GetMapping("/listarPorId/{id}")
    public ResponseEntity<Optional<UsuarioDto>> listarUsuarioPorId(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioServices.listarUsuarioPorId(id));
    }

    //Listar todos ativos
    @GetMapping("/listarTodosAtivos")
    public ResponseEntity<List<UsuarioDto>> listarTodosAtivos(){
        return ResponseEntity.ok(usuarioServices.buscarTodosAtivos());
    }

    //Buscar por id sendo ativo
    @GetMapping("/listarPorIdAtivo/{id}")
    public ResponseEntity<UsuarioDto> listarUsuarioPorIdAtivo(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioServices.listarUsuarioPorIdAtivo(id));
    }

}
