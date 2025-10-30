package TaoTaoPerto.springBoot.usuarios.services;

import TaoTaoPerto.springBoot.exception.UsuarioDesativoOuNaoEncontrado;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDtoMapper;
import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import TaoTaoPerto.springBoot.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioDtoMapper usuarioDtoMapper;

    public UsuarioServices(UsuarioRepository usuarioRepository, UsuarioDtoMapper usuarioDtoMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioDtoMapper = usuarioDtoMapper;
    }

    //salvar
    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        UsuarioModel usuarioModel = usuarioRepository.save(usuarioDtoMapper.map(usuarioDto));
        return usuarioDtoMapper.map(usuarioModel);
    }

    //Buscar todos
    public List<UsuarioDto> buscarTodos(){
        return usuarioRepository
                .findAll()
                .stream()
                .map(usuarioDtoMapper::map)
                .toList();
    }

    //Buscar todos ativos
    public List<UsuarioDto> buscarTodosAtivos(){
        return usuarioRepository
                .findAll()
                .stream()
                .filter(usuario -> usuario.getAtivo())
                .map(usuarioDtoMapper::map)
                .toList();
    }

    //Buscar por id mesmo não sendo ativo
    public Optional<UsuarioDto> listarUsuarioPorId(Long id){
        var buscadorDeUsuarioPorID = usuarioRepository.findById(id)
                .map(usuarioDtoMapper::map);
        if (buscadorDeUsuarioPorID.isEmpty()) {
            throw new RuntimeException("Usuario com ID: " + id + " não foi encontrado");
        }
        return buscadorDeUsuarioPorID;
    }

    //Buscar por id usuarios ativos
    public UsuarioDto listarUsuarioPorIdAtivo(Long id) {
        return usuarioRepository.findByIdAndAtivo(id, true)
                .map(usuarioDtoMapper::map)
                .orElseThrow(() -> new UsuarioDesativoOuNaoEncontrado("Não foi possivel encontrar o usuario do ID: (" + id + ") "));
    }

    //Atualizar
    public UsuarioDto atualizarUsuario(Long id, UsuarioDto usuarioDto) {
        UsuarioModel usuarioModel = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        usuarioModel.atualizarUsuarioComDto(usuarioDto);
        if (usuarioDto.getSenha() != null) {
            usuarioModel.setSenha(usuarioDto.getSenha());
        }
        return usuarioDtoMapper.map(usuarioRepository.save(usuarioModel));
    }

    // Delet Logico
    public UsuarioDto deletLogicoUsuario(Long id){
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario com ID: " + id + " não encontrado"));
        usuario.setAtivo(false);
        usuario.setDesativadoEm(LocalDateTime.now());
        UsuarioModel usuarioDesativado = usuarioRepository.save(usuario);
        return usuarioDtoMapper.map(usuarioDesativado);
    }

}
