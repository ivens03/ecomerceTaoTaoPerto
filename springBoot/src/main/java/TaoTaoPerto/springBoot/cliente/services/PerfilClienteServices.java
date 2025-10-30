package TaoTaoPerto.springBoot.cliente.services;

import TaoTaoPerto.springBoot.cliente.dtos.PerfilClienteDto;
import TaoTaoPerto.springBoot.cliente.dtos.PerfilClienteDtoMapper;
import TaoTaoPerto.springBoot.cliente.model.PerfilClienteModel;
import TaoTaoPerto.springBoot.cliente.repository.PerfilClienteRepository;
import TaoTaoPerto.springBoot.exception.UsuarioDesativoOuNaoEncontrado;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDtoMapper;
import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import TaoTaoPerto.springBoot.usuarios.services.UsuarioServices;
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
    public PerfilClienteDto salvarPerfilCliente(PerfilClienteDto perfilClienteDto){
        UsuarioDto usuarioDto = perfilClienteDto.getUsuario();
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
