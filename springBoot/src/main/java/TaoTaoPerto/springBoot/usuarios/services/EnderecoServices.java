package TaoTaoPerto.springBoot.usuarios.services;

import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDto;
import TaoTaoPerto.springBoot.usuarios.dtos.EnderecoDtoMapper;
import TaoTaoPerto.springBoot.usuarios.model.EnderecoModel;
import TaoTaoPerto.springBoot.usuarios.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServices {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoDtoMapper enderecoDtoMapper;

    public EnderecoServices(EnderecoRepository enderecoRepository, EnderecoDtoMapper enderecoDtoMapper) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoDtoMapper = enderecoDtoMapper;
    }

    public EnderecoDto salvarEndereco(EnderecoDto enderecoDto) {
        EnderecoModel enderecoModel = enderecoRepository.save(enderecoDtoMapper.map(enderecoDto));
        return enderecoDtoMapper.map(enderecoModel);
    }

}
