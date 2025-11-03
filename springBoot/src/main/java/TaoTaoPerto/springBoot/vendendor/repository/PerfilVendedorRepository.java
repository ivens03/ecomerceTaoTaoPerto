package TaoTaoPerto.springBoot.vendendor.repository;

import TaoTaoPerto.springBoot.vendendor.model.PerfilVendedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilVendedorRepository extends JpaRepository<PerfilVendedorModel, Long> {
}
