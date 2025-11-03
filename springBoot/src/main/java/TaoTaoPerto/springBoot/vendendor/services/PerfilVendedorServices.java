package TaoTaoPerto.springBoot.vendendor.services;

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

    // Salvar (Criar)
    @Transactional
    public PerfilVendedorDto salvarPerfil(PerfilVendedorDto perfilDto) {

        // 1. Validar e Salvar o Usuário ANTES
        if (perfilDto.getUsuario() == null) {
            throw new IllegalArgumentException("Os dados do usuário são obrigatórios no DTO.");
        }

        // Delega a criação do usuário (criptografia, etc.) para o UsuarioServices
        UsuarioDto usuarioSalvoDto = usuarioServices.salvarUsuario(perfilDto.getUsuario());

        // 2. Buscar a ENTIDADE do usuário recém-salvo
        // (Necessário para a associação @OneToOne)
        UsuarioModel usuarioEntidade = usuarioRepository.findById(usuarioSalvoDto.getId())
                .orElseThrow(() -> new RuntimeException("Erro fatal: não foi possível encontrar usuário recém-criado. ID: " + usuarioSalvoDto.getId()));

        // 3. Criar e associar o Perfil
        // (O Mapper não pode fazer isso, pois o DTO não tem o "usuarioId" agora)
        PerfilVendedorModel perfilModel = new PerfilVendedorModel();
        perfilModel.setNomeLoja(perfilDto.getNomeLoja());
        perfilModel.setDescricao(perfilDto.getDescricao());
        perfilModel.setLogoUrl(perfilDto.getLogoUrl());
        perfilModel.setBannerUrl(perfilDto.getBannerUrl());

        // Define valores padrão (pois não vêm do DTO de criação)
        perfilModel.setNotaMedia(BigDecimal.ZERO);
        perfilModel.setTotalAvaliacoes(0);

        // A MÁGICA: Associa a entidade de usuário que acabamos de salvar
        perfilModel.setUsuario(usuarioEntidade);

        PerfilVendedorModel perfilSalvo = perfilVendedorRepository.save(perfilModel);

        // 4. Mapear a resposta de volta (o mapper precisa ser ajustado)
        return perfilVendedorDtoMapper.map(perfilSalvo);
    }
}
