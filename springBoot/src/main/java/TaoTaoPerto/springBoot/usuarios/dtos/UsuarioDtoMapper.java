package TaoTaoPerto.springBoot.usuarios.dtos;

import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoMapper {

    public UsuarioModel map(UsuarioDto usuarioDto) {

        if (usuarioDto == null) {
            return null;
        }

        UsuarioModel usuarioModel = new UsuarioModel();

        usuarioModel.setId(usuarioDto.getId());
        usuarioModel.setEmail(usuarioDto.getEmail());
        usuarioModel.setSenha(usuarioDto.getSenha());
        usuarioModel.setNomeCompleto(usuarioDto.getNomeCompleto());
        usuarioModel.setCpf(usuarioDto.getCpf());
        usuarioModel.setCelular(usuarioDto.getCelular());
        usuarioModel.setDataNascimento(usuarioDto.getDataNascimento());
        usuarioModel.setTipoUsuario(usuarioDto.getTipoUsuario());
        usuarioModel.setAtivo(usuarioDto.getAtivo());

        return  usuarioModel;
    }

    public UsuarioDto map(UsuarioModel usuarioModel) {

        if (usuarioModel == null) {
            return null;
        }

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId(usuarioModel.getId());
        usuarioDto.setEmail(usuarioModel.getEmail());
        usuarioDto.setSenha(usuarioModel.getSenha());
        usuarioDto.setNomeCompleto(usuarioModel.getNomeCompleto());
        usuarioDto.setCpf(usuarioModel.getCpf());
        usuarioDto.setCelular(usuarioModel.getCelular());
        usuarioDto.setDataNascimento(usuarioModel.getDataNascimento());
        usuarioDto.setTipoUsuario(usuarioModel.getTipoUsuario());
        usuarioDto.setAtivo(usuarioModel.getAtivo());

        return usuarioDto;
    }

}