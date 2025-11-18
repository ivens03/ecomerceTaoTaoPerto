package TaoTaoPerto.springBoot.usuarios.services;

import TaoTaoPerto.springBoot.usuarios.dtos.PerfilClienteDto;
import TaoTaoPerto.springBoot.usuarios.dtos.PerfilClienteDtoMapper;
import TaoTaoPerto.springBoot.usuarios.enums.TiposUsuariosEnum;
import TaoTaoPerto.springBoot.usuarios.model.PerfilClienteModel;
import TaoTaoPerto.springBoot.usuarios.repository.PerfilClienteRepository;
import TaoTaoPerto.springBoot.exception.tratamentoDeErro.UsuarioDesativoOuNaoEncontrado;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDtoMapper;
import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PerfilClienteServices {

    public final UsuarioServices usuarioServices;
    public final PerfilClienteRepository perfilClienteRepository;
    public final PerfilClienteDtoMapper perfilClienteDtoMapper;
    private final UsuarioDtoMapper usuarioDtoMapper;

    public PerfilClienteServices(UsuarioServices usuarioServices, PerfilClienteRepository perfilClienteRepository, PerfilClienteDtoMapper perfilClienteDtoMapper, UsuarioDtoMapper usuarioDtoMapper) {
        this.usuarioServices = usuarioServices;
        this.perfilClienteRepository = perfilClienteRepository;
        this.perfilClienteDtoMapper = perfilClienteDtoMapper;
        this.usuarioDtoMapper = usuarioDtoMapper;
    }

    //Salvar definido como cliente
    @Transactional
    public PerfilClienteDto salvarPerfilCliente(PerfilClienteDto perfilClienteDto) {
        UsuarioDto usuarioDto = perfilClienteDto.getUsuario();
        usuarioDto.setTipoUsuario(TiposUsuariosEnum.CLIENTE);
        UsuarioDto usuarioSalvoDto = usuarioServices.salvarUsuario(usuarioDto);
        UsuarioModel usuarioSalvoModel = usuarioDtoMapper.map(
                usuarioServices.listarUsuarioPorId(usuarioSalvoDto.getId())
                        .orElseThrow(() -> new UsuarioDesativoOuNaoEncontrado("Usuário não encontrado"))
        );
        PerfilClienteModel perfilSalvo = perfilClienteRepository.findByUsuarioId(usuarioSalvoModel.getId())
                .orElseThrow(() -> new RuntimeException("ERRO CRÍTICO: Falha em criar o perfil do cliente."));
        return perfilClienteDtoMapper.map(perfilSalvo);
    }
}
