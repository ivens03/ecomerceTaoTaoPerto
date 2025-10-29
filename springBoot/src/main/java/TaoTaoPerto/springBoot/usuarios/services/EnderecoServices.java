package TaoTaoPerto.springBoot.usuarios.services;

import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDtoMapper;
import TaoTaoPerto.springBoot.usuarios.model.EnderecoModel;
import TaoTaoPerto.springBoot.usuarios.repository.EnderecoRepository;
import TaoTaoPerto.springBoot.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnderecoServices {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoDtoMapper enderecoDtoMapper;

    public EnderecoServices(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, EnderecoDtoMapper enderecoDtoMapper) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.enderecoDtoMapper = enderecoDtoMapper;
    }

    // salvar
    public EnderecoDto salvarEndereco(EnderecoDto enderecoDto) {
        EnderecoModel enderecoModel = enderecoRepository.save(enderecoDtoMapper.map(enderecoDto));
        return enderecoDtoMapper.map(enderecoModel);
    }

    // Listar todos os endereços de um usuário específico
    public List<EnderecoDto> buscarTodosEnderecosDoMesmoUsuario(Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty() || !usuario.get().getAtivo() ) {
            throw new RuntimeException("Usuario com ID: " + id + " não encontrado ou está desativado");
        }
        return enderecoRepository.findByUsuarioId(id)
                .stream()
                .map(enderecoDtoMapper::map)
                .toList();
    }

    // Listar todos os endereços ativos de um usuário ativo específico "id"
    public List<EnderecoDto> buscarTodosEnderecosAtivoDoMesmoUsuario(Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty() || !usuario.get().getAtivo()) {
            throw new RuntimeException("Usuário com ID: " + id + " não encontrado ou está desativado");
        }
        return enderecoRepository.findByUsuarioId(id)
                .stream()
                .filter(EnderecoModel::getAtivo)
                .map(enderecoDtoMapper::map)
                .toList();
    }

    // Listar todos os endereços de todos os usuários
    public List<EnderecoDto> buscarTodosEnderecosDeTodosOsUsuarios() {
        return enderecoRepository
                .findAll()
                .stream()
                .map(enderecoDtoMapper::map)
                .toList();
    }

    //Atualizar pelo id
    public EnderecoDto atualizarEndereco(Long id, EnderecoDto enderecoDto) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty() || !usuario.get().getAtivo()) {
            throw new RuntimeException("Usuário com ID: " + id + " não encontrado ou está inativo");
        }
        EnderecoModel enderecoModel = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        enderecoModel.atualizarEnderecoComDto(enderecoDto);
        return enderecoDtoMapper.map(enderecoRepository.save(enderecoModel));
    }

    //Delet logico do endereco do usuario
    public EnderecoDto deletLogicoEndereco(Long id){
        EnderecoModel enderecoModel = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        enderecoModel.setAtivo(false);
        EnderecoModel enderecoDesativado = enderecoRepository.save(enderecoModel);
        return enderecoDtoMapper.map(enderecoDesativado);
    }

}
