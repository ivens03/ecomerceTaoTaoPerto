package TaoTaoPerto.springBoot.vendendor.repository;

import TaoTaoPerto.springBoot.vendendor.model.PerfilVendedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilVendedorRepository extends JpaRepository<PerfilVendedorModel, Long> {
    //Optional<PerfilVendedorModel> findByIdAndAtivo(Long id, boolean ativo);
    Optional<PerfilVendedorModel> findByIdAndUsuarioAtivo(Long id, boolean ativo);
}
