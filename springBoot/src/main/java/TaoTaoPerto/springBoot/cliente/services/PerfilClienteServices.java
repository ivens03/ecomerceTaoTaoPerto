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
        //usuarioDto.setTipoUsuario(TiposUsuariosEnum.CLIENTE); // O DTO já faz isso

        // 1. Salva o Usuário (retorna DTO)
        UsuarioDto usuarioSalvoDto = usuarioServices.salvarUsuario(usuarioDto);

        // 2. Busca o Model do usuário salvo
        UsuarioModel usuarioSalvoModel = usuarioDtoMapper.map(
                usuarioServices.listarUsuarioPorId(usuarioSalvoDto.getId())
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"))
        );

        // 3. (MUDANÇA AQUI) Busca o Perfil que o TRIGGER V9 acabou de criar
        PerfilClienteModel perfilSalvo = perfilClienteRepository.findByUsuarioId(usuarioSalvoModel.getId())
                .orElseThrow(() -> new RuntimeException("ERRO CRÍTICO: Trigger V9 falhou em criar o perfil do cliente."));

        // 4. (Lógica de Endereço Padrão aqui)
        // if (perfilClienteDto.getEnderecoPadrao() != null) {
        //    EnderecoModel enderecoSalvo = enderecoService.salvar(perfilClienteDto.getEnderecoPadrao(), usuarioSalvoModel);
        //    perfilSalvo.setEnderecoPadrao(enderecoSalvo);
        //    perfilSalvo = perfilClienteRepository.save(perfilSalvo); // Salva a atualização
        // }

        // 5. Retorna o DTO mapeado (agora com o perfil que o trigger criou)
        return perfilClienteDtoMapper.map(perfilSalvo);
    }
}
