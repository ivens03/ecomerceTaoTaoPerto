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
        UsuarioDto usuarioSalvoDto = usuarioServices.salvarUsuario(perfilDto.getUsuario());

        // 2. Buscar a ENTIDADE do usuário recém-salvo
        UsuarioModel usuarioEntidade = usuarioRepository.findById(usuarioSalvoDto.getId())
                .orElseThrow(() -> new RuntimeException("Erro fatal: não foi possível encontrar usuário recém-criado. ID: " + usuarioSalvoDto.getId()));

        // 3. Criar e associar o Perfil
        //    !! AQUI ESTÁ A CORREÇÃO !!
        //    Usamos o mapper para copiar TODOS os campos do DTO
        PerfilVendedorModel perfilModel = perfilVendedorDtoMapper.map(perfilDto); //

        // 4. Definir os valores que não vêm do DTO de criação
        perfilModel.setId(null); // Garantir que é um 'create'
        perfilModel.setNotaMedia(BigDecimal.ZERO);
        perfilModel.setTotalAvaliacoes(0);
        perfilModel.setCriadoEm(null); // Deixar o @CreationTimestamp cuidar
        perfilModel.setAtualizadoEm(null); // Deixar o @UpdateTimestamp cuidar

        // 5. Associar a entidade de usuário que acabamos de salvar
        perfilModel.setUsuario(usuarioEntidade);

        PerfilVendedorModel perfilSalvo = perfilVendedorRepository.save(perfilModel);

        // 6. Mapear a resposta de volta
        System.out.println(perfilSalvo);
        return perfilVendedorDtoMapper.map(perfilSalvo); //
    }
}
