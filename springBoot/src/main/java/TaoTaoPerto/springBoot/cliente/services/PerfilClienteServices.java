package TaoTaoPerto.springBoot.cliente.services;

import TaoTaoPerto.springBoot.cliente.dtos.PerfilClienteDto;
import TaoTaoPerto.springBoot.cliente.dtos.PerfilClienteDtoMapper;
import TaoTaoPerto.springBoot.cliente.model.PerfilClienteModel;
import TaoTaoPerto.springBoot.cliente.repository.PerfilClienteRepository;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDto;
import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDtoMapper;
import TaoTaoPerto.springBoot.usuarios.enums.TiposUsuariosEnum;
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
    // Em PerfilClienteServices.java

    @Transactional
    public PerfilClienteDto salvarPerfilCliente(PerfilClienteDto perfilClienteDto){

        UsuarioDto usuarioDto = perfilClienteDto.getUsuario();
        usuarioDto.setTipoUsuario(TiposUsuariosEnum.CLIENTE);

        // 1. Salva o Usuário (retorna DTO)
        UsuarioDto usuarioSalvoDto = usuarioServices.salvarUsuario(usuarioDto);

        // 2. Busca o Model salvo usando o novo método (retorna Model)
        // (Agora usuarioSalvoDto.getId() retorna Long, e buscarModelPorId aceita Long)
        UsuarioModel usuarioSalvoModel = usuarioDtoMapper.map(
                usuarioServices.listarUsuarioPorId(usuarioSalvoDto.getId())
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"))
        );
        // 3. Cria e associa o Perfil
        PerfilClienteModel novoPerfil = new PerfilClienteModel();
        novoPerfil.setUsuario(usuarioSalvoModel);

        // ... (Lógica de Endereço Padrão aqui) ...

        // 4. Salva o Perfil
        PerfilClienteModel perfilSalvo = perfilClienteRepository.save(novoPerfil);

        // 5. Retorna o DTO mapeado
        return perfilClienteDtoMapper.map(perfilSalvo);
    }
}
