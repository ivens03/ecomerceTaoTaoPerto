package TaoTaoPerto.springBoot.cliente.repository;

import TaoTaoPerto.springBoot.cliente.model.PerfilClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilClienteRepository extends JpaRepository<PerfilClienteModel, Long> {
}
