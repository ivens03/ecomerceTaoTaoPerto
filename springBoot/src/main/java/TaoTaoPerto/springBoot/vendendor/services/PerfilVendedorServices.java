package TaoTaoPerto.springBoot.vendendor.services;

import TaoTaoPerto.springBoot.exception.tratamentoDeErro.UsuarioDesativoOuNaoEncontrado;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import TaoTaoPerto.springBoot.usuarios.repository.UsuarioRepository;
import TaoTaoPerto.springBoot.usuarios.services.UsuarioServices;
import TaoTaoPerto.springBoot.vendendor.dtos.PerfilVendedorDto;
import TaoTaoPerto.springBoot.vendendor.dtos.PerfilVendedorDtoMapper;
import TaoTaoPerto.springBoot.vendendor.model.PerfilVendedorModel;
import TaoTaoPerto.springBoot.vendendor.repository.PerfilVendedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilVendedorServices {

    private final PerfilVendedorRepository perfilVendedorRepository;
    private final PerfilVendedorDtoMapper perfilVendedorDtoMapper;
    private final UsuarioServices usuarioServices;
    private final UsuarioRepository usuarioRepository;

    public PerfilVendedorServices(PerfilVendedorRepository perfilVendedorRepository,
                                  PerfilVendedorDtoMapper perfilVendedorDtoMapper,
                                  UsuarioServices usuarioServices,
                                  UsuarioRepository usuarioRepository) {
        this.perfilVendedorRepository = perfilVendedorRepository;
        this.perfilVendedorDtoMapper = perfilVendedorDtoMapper;
        this.usuarioServices = usuarioServices;
        this.usuarioRepository = usuarioRepository;
    }

    // Salvar
    @Transactional
    public PerfilVendedorDto salvarPerfil(PerfilVendedorDto perfilDto) {
        if (perfilDto.getUsuario() == null) {
            throw new IllegalArgumentException("Os dados do usuário são obrigatórios no DTO.");
        }
        UsuarioDto usuarioSalvoDto = usuarioServices.salvarUsuario(perfilDto.getUsuario());
        UsuarioModel usuarioEntidade = usuarioRepository.findById(usuarioSalvoDto.getId())
                .orElseThrow(() -> new RuntimeException("Erro fatal: não foi possível encontrar usuário recém-criado. ID: " + usuarioSalvoDto.getId()));
        PerfilVendedorModel perfilModel = perfilVendedorDtoMapper.map(perfilDto);
        if (perfilDto.getCnpj() == null) {
            perfilModel.setCnpj("CNPJ_PADRAO_PF");
        }
        perfilModel.setNotaMedia(BigDecimal.ZERO);
        perfilModel.setTotalAvaliacoes(0);
        perfilModel.setUsuario(usuarioEntidade);
        PerfilVendedorModel perfilSalvo = perfilVendedorRepository.save(perfilModel);
        return perfilVendedorDtoMapper.map(perfilSalvo);
    }

    //Buscar por id mesmo não sendo ativo
    public Optional<PerfilVendedorDto> listarPerfilDeVendedorPorId(Long id){
        var buscadorDePerfilPorID = perfilVendedorRepository.findById(id)
                .map(perfilVendedorDtoMapper::map);
        if (buscadorDePerfilPorID.isEmpty()) {
            throw new UsuarioDesativoOuNaoEncontrado("Não foi possivel encontrar o usuario do ID: (" + id + "), pode não existir no sistema ou não foi encontrado. Fale com o suporte para mais inforamçẽos sobre o usuarios especifico.");
        }
        return buscadorDePerfilPorID;
    }

    //Buscar por id estando ativo
    public Optional<PerfilVendedorDto> listarPerfilDeVendedorPorIdAtivo(Long id) {
        var buscadorDePerfilPorID = perfilVendedorRepository.findByIdAndUsuarioAtivo(id, true)
                .map(perfilVendedorDtoMapper::map);
        if (buscadorDePerfilPorID.isEmpty()) {
            throw new UsuarioDesativoOuNaoEncontrado("Não foi possivel encontrar o usuario do ID: (" + id + "), pode não existir no sistema ou não foi encontrado. Fale com o suporte para mais inforamçẽos sobre o usuarios especifico.");
        }
        return buscadorDePerfilPorID;
    }

    //Listar todos
    public List<PerfilVendedorDto> listarTodosPerfisDeVendedores() {
        return perfilVendedorRepository.findAll()
                .stream()
                .map(perfilVendedorDtoMapper::map)
                .toList();
    }

    //Listar todos ativos
    public List<PerfilVendedorDto> listarTodosPerfisDeVendedoresAtivos() {
        return perfilVendedorRepository.findAllByUsuarioAtivo(true)
                .stream()
                .map(perfilVendedorDtoMapper::map)
                .toList();
    }

    //Atualizar
    public PerfilVendedorDto atualizarPerfilDeVendedor(Long id, PerfilVendedorDto perfilDto) {
        Optional<PerfilVendedorModel> perfilOptional = perfilVendedorRepository.findById(id);
        if (perfilOptional.isEmpty()) {
            throw new UsuarioDesativoOuNaoEncontrado("Não foi possivel encontrar o usuario do ID: (" + id + "), pode não existir no sistema ou não foi encontrado. Fale com o suporte para mais inforamçẽos sobre o usuarios especifico.");
        }
        PerfilVendedorModel perfilModel = perfilOptional.get();
        perfilModel.atualizarPerfilVendedorComDto(perfilDto);
        System.out.println(perfilModel);
        return perfilVendedorDtoMapper.map(perfilVendedorRepository.save(perfilModel));
    }

    //Delete logico
    @Transactional
    public PerfilVendedorDto deletePerfilDeVendedor(Long id) {
        PerfilVendedorModel vendedor = perfilVendedorRepository.findById(id)
                .orElseThrow(() -> new UsuarioDesativoOuNaoEncontrado("Perfil de Vendedor com ID: " + id + " não encontrado"));
        if (vendedor.getUsuario() == null) {
            throw new IllegalStateException("Perfil de vendedor com ID: " + id + " não possui um usuário associado.");
        }
        usuarioServices.deletLogicoUsuario(vendedor.getUsuario().getId());
        return perfilVendedorDtoMapper.map(vendedor);
    }

}
