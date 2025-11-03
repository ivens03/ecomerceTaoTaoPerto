package TaoTaoPerto.springBoot.vendendor.dtos;

import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDtoMapper; // <-- Importar o mapper de usuário
import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import TaoTaoPerto.springBoot.vendendor.model.PerfilVendedorModel;
import org.springframework.stereotype.Component;

@Component
public class PerfilVendedorDtoMapper {

    // 1. Injetar o mapper de Usuário
    private final UsuarioDtoMapper usuarioDtoMapper;

    public PerfilVendedorDtoMapper(UsuarioDtoMapper usuarioDtoMapper) {
        this.usuarioDtoMapper = usuarioDtoMapper;
    }

    public PerfilVendedorModel map(PerfilVendedorDto perfilVendedorDto){

        if (perfilVendedorDto == null) {
            return null;
        }

        PerfilVendedorModel perfilVendedorModel = new PerfilVendedorModel();

        perfilVendedorModel.setId(perfilVendedorDto.getId());

        // 2. Mapear o objeto UsuarioDto para UsuarioModel
        if (perfilVendedorDto.getUsuario() != null) {
            // Delega a conversão para o mapper de usuário
            perfilVendedorModel.setUsuario(usuarioDtoMapper.map(perfilVendedorDto.getUsuario()));
        }

        perfilVendedorModel.setNomeLoja(perfilVendedorDto.getNomeLoja());
        perfilVendedorModel.setDescricao(perfilVendedorDto.getDescricao());
        perfilVendedorModel.setLogoUrl(perfilVendedorDto.getLogoUrl());
        perfilVendedorModel.setBannerUrl(perfilVendedorDto.getBannerUrl());
        perfilVendedorModel.setNotaMedia(perfilVendedorDto.getNotaMedia());
        perfilVendedorModel.setTotalAvaliacoes(perfilVendedorDto.getTotalAvaliacoes());
        perfilVendedorModel.setCriadoEm(perfilVendedorDto.getCriadoEm());
        perfilVendedorModel.setAtualizadoEm(perfilVendedorDto.getAtualizadoEm());

        return perfilVendedorModel;
    }

    /**
     * Converte a Entidade (com UsuarioModel) para o DTO (com UsuarioDto)
     * Usado para retornar dados nas respostas da API (GET, POST, PUT)
     */
    public PerfilVendedorDto map(PerfilVendedorModel perfilVendedorModel){

        if (perfilVendedorModel == null) {
            return null;
        }

        PerfilVendedorDto perfilVendedorDto = new PerfilVendedorDto();

        perfilVendedorDto.setId(perfilVendedorModel.getId());

        // 3. Mapear a entidade UsuarioModel para UsuarioDto
        if (perfilVendedorModel.getUsuario() != null) {
            // Delega a conversão para o mapper de usuário
            perfilVendedorDto.setUsuario(usuarioDtoMapper.map(perfilVendedorModel.getUsuario()));
        }
        else {
            perfilVendedorDto.setUsuario(null);
        }

        perfilVendedorDto.setNomeLoja(perfilVendedorModel.getNomeLoja());
        perfilVendedorDto.setDescricao(perfilVendedorModel.getDescricao());
        perfilVendedorDto.setLogoUrl(perfilVendedorModel.getLogoUrl());
        perfilVendedorDto.setBannerUrl(perfilVendedorModel.getBannerUrl());
        perfilVendedorDto.setNotaMedia(perfilVendedorModel.getNotaMedia());
        perfilVendedorDto.setTotalAvaliacoes(perfilVendedorModel.getTotalAvaliacoes());
        perfilVendedorDto.setCriadoEm(perfilVendedorModel.getCriadoEm());
        perfilVendedorDto.setAtualizadoEm(perfilVendedorModel.getAtualizadoEm());

        return perfilVendedorDto;
    }
}