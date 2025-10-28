package TaoTaoPerto.springBoot.usuarios.dtos;

import TaoTaoPerto.springBoot.usuarios.model.EnderecoModel;
import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import org.springframework.stereotype.Component;

@Component
public class EnderecoDtoMapper {

    public EnderecoModel map(EnderecoDto enderecoDto){

        if (enderecoDto == null){
            return null;
        }

        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModel.setId(enderecoDto.getId());
        
        if (enderecoDto.getUsuarioId() != null) {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setId(enderecoDto.getUsuarioId());
            enderecoModel.setUsuario(usuario);
        }

        enderecoModel.setRotulo(enderecoDto.getRotulo());
        enderecoModel.setCep(enderecoDto.getCep());
        enderecoModel.setLogradouro(enderecoDto.getLogradouro());
        enderecoModel.setNumero(enderecoDto.getNumero());
        enderecoModel.setComplemento(enderecoDto.getComplemento());
        enderecoModel.setBairro(enderecoDto.getBairro());
        enderecoModel.setCidade(enderecoDto.getCidade());
        enderecoModel.setEstado(enderecoDto.getEstado());

        return enderecoModel;
    }

    public EnderecoDto map(EnderecoModel enderecoModel){

        if (enderecoModel == null){
            return null;
        }

        EnderecoDto enderecoDto = new EnderecoDto();

        enderecoDto.setId(enderecoModel.getId());
        enderecoDto.setUsuarioId(enderecoModel.getUsuario().getId());
        enderecoDto.setRotulo(enderecoModel.getRotulo());
        enderecoDto.setCep(enderecoModel.getCep());
        enderecoDto.setLogradouro(enderecoModel.getLogradouro());
        enderecoDto.setNumero(enderecoModel.getNumero());
        enderecoDto.setComplemento(enderecoModel.getComplemento());
        enderecoDto.setBairro(enderecoModel.getBairro());
        enderecoDto.setCidade(enderecoModel.getCidade());
        enderecoDto.setEstado(enderecoModel.getEstado());

        return enderecoDto;
    }
}
