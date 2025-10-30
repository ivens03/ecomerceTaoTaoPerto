package TaoTaoPerto.springBoot.cliente.repository;

import TaoTaoPerto.springBoot.cliente.model.PerfilClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilClienteRepository extends JpaRepository<PerfilClienteModel, Long> {
    Optional<PerfilClienteModel> findByUsuarioId(Long usuarioId);
}
