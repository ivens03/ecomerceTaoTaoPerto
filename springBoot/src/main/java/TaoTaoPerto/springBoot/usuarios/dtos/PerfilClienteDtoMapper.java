package TaoTaoPerto.springBoot.usuarios.dtos;

import TaoTaoPerto.springBoot.usuarios.model.PerfilClienteModel;
import org.springframework.stereotype.Component;

@Component
public class PerfilClienteDtoMapper {

    // --- Injete os outros mappers ---
    private final UsuarioDtoMapper usuarioDtoMapper;
    private final EnderecoDtoMapper enderecoDtoMapper; // <-- 2. INJETE O MAPPER DE ENDEREÇO

    public PerfilClienteDtoMapper(UsuarioDtoMapper usuarioDtoMapper, EnderecoDtoMapper enderecoDtoMapper) {
        this.usuarioDtoMapper = usuarioDtoMapper;
        this.enderecoDtoMapper = enderecoDtoMapper;
    }

    /**
     * Converte o DTO (com DTOs aninhados) para a Entidade Model
     * (Esta lógica é complexa e pertence ao Service, mas vamos mantê-la aqui)
     */
    public PerfilClienteModel map(PerfilClienteDto dto) {
        if (dto == null) return null;

        PerfilClienteModel model = new PerfilClienteModel();
        model.setId(dto.getId());
        model.setPontosFidelidade(dto.getPontosFidelidade());
        model.setCodigoIndicacao(dto.getCodigoIndicacao());
        model.setCriadoEm(dto.getCriadoEm());
        model.setAtualizadoEm(dto.getAtualizadoEm());

        // Mapeia o DTO aninhado de Usuário
        if (dto.getUsuario() != null) {
            model.setUsuario(usuarioDtoMapper.map(dto.getUsuario()));
        }

        // Mapeia o DTO aninhado de Endereço
        if (dto.getEnderecoPadrao() != null) {
            model.setEnderecoPadrao(enderecoDtoMapper.map(dto.getEnderecoPadrao()));
        }

        return model;
    }

    /**
     * Converte a Entidade Model (com Models aninhados) para o DTO
     */
    public PerfilClienteDto map(PerfilClienteModel model) {
        if (model == null) return null;

        PerfilClienteDto dto = new PerfilClienteDto();
        dto.setId(model.getId());
        dto.setPontosFidelidade(model.getPontosFidelidade());
        dto.setCodigoIndicacao(model.getCodigoIndicacao());
        dto.setCriadoEm(model.getCriadoEm());
        dto.setAtualizadoEm(model.getAtualizadoEm());

        // Mapeia o Model aninhado de Usuário
        if (model.getUsuario() != null) {
            dto.setUsuario(usuarioDtoMapper.map(model.getUsuario()));
        }

        // Mapeia o Model aninhado de Endereço
        if (model.getEnderecoPadrao() != null) {
            dto.setEnderecoPadrao(enderecoDtoMapper.map(model.getEnderecoPadrao()));
        }

        return dto;
    }
}