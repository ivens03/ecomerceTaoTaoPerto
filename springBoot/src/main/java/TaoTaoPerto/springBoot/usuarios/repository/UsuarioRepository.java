package TaoTaoPerto.springBoot.usuarios.repository;

import TaoTaoPerto.springBoot.usuarios.dtos.UsuarioDtoMapper;
import TaoTaoPerto.springBoot.usuarios.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel,Integer> {
    Optional<UsuarioModel> findByIdAndAtivo(Integer id, boolean ativo);
}
