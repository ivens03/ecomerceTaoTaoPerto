package TaoTaoPerto.springBoot.usuarios.repository;

import TaoTaoPerto.springBoot.usuarios.model.PerfilClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilClienteRepository extends JpaRepository<PerfilClienteModel, Long> {
    Optional<PerfilClienteModel> findByUsuarioId(Long usuarioId);
}
