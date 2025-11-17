package TaoTaoPerto.springBoot.usuarios.dtos;

import TaoTaoPerto.springBoot.usuarios.model.PerfilVendedorModel;
import org.springframework.stereotype.Component;

@Component
public class PerfilVendedorDtoMapper {

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

        if (perfilVendedorDto.getUsuario() != null) {
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
        perfilVendedorModel.setTipoPessoa(perfilVendedorDto.getTipoPessoa());
        perfilVendedorModel.setRazaoSocial(perfilVendedorDto.getRazaoSocial());
        perfilVendedorModel.setCnpj(perfilVendedorDto.getCnpj());
        perfilVendedorModel.setInscricaoEstadual(perfilVendedorDto.getInscricaoEstadual());
        perfilVendedorModel.setNotaMedia(perfilVendedorDto.getNotaMedia());


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

        if (perfilVendedorModel.getUsuario() != null) {
            perfilVendedorDto.setUsuario(usuarioDtoMapper.map(perfilVendedorModel.getUsuario()));
        } else {
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
        perfilVendedorDto.setTipoPessoa(perfilVendedorModel.getTipoPessoa());
        perfilVendedorDto.setRazaoSocial(perfilVendedorModel.getRazaoSocial());
        perfilVendedorDto.setCnpj(perfilVendedorModel.getCnpj());
        perfilVendedorDto.setInscricaoEstadual(perfilVendedorModel.getInscricaoEstadual());
        perfilVendedorDto.setNotaMedia(perfilVendedorModel.getNotaMedia());

        return perfilVendedorDto;
    }
}