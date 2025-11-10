package TaoTaoPerto.springBoot.usuarios.services;

import TaoTaoPerto.springBoot.exception.tratamentoDeErro.UsuarioDesativoOuNaoEncontrado;
import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDtoMapper;
import TaoTaoPerto.springBoot.usuarios.enums.TipoMoradiaEnum;
import TaoTaoPerto.springBoot.usuarios.model.EnderecoModel;
import TaoTaoPerto.springBoot.usuarios.repository.EnderecoRepository;
import TaoTaoPerto.springBoot.usuarios.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

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

    private void validarEndereco(EnderecoDto enderecoDto) {
        if (enderecoDto.getTipoMoradia() == TipoMoradiaEnum.CONDOMINIO) {
            if (enderecoDto.getComplemento() == null || enderecoDto.getComplemento().isBlank()) {
                throw new ValidationException("O complemento é obrigatório para o tipo de moradia 'CONDOMÍNIO'.");
            }
        }
    }

    // salvar
    public EnderecoDto salvarEndereco(EnderecoDto enderecoDto) {
        validarEndereco(enderecoDto);
        enderecoDto.setAtivo(true);
        EnderecoModel enderecoModel = enderecoRepository.save(enderecoDtoMapper.map(enderecoDto));
        return enderecoDtoMapper.map(enderecoModel);
    }

    // Listar todos os endereços de um usuário específico
    public List<EnderecoDto> buscarTodosEnderecosDoMesmoUsuario(Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty() || !usuario.get().getAtivo() ) {
            throw new UsuarioDesativoOuNaoEncontrado("Não foi possivel encontrar o usuario do ID: (" + id + ") ");
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
            throw new UsuarioDesativoOuNaoEncontrado("Não foi possivel encontrar o usuario do ID: (" + id + ") ");
        }
        return enderecoRepository.findByUsuarioIdAndAtivoTrue(id)
                .stream()
                //.filter(EnderecoModel::getAtivo)
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
        var usuario = usuarioRepository.findById(enderecoDto.getUsuarioId());
        if (usuario.isEmpty() || !usuario.get().getAtivo()) {
            throw new UsuarioDesativoOuNaoEncontrado("Não foi possivel encontrar o usuario do ID: (" + enderecoDto.getUsuarioId() + ") ");
        }
        validarEndereco(enderecoDto);
        EnderecoModel enderecoModel = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com o ID: " + id));
        if (!enderecoModel.getUsuario().getId().equals(enderecoDto.getUsuarioId())) {
            throw new ValidationException("Este endereço (ID: " + id + ") não pertence ao usuário (ID: " + enderecoDto.getUsuarioId() + ").");
        }
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
