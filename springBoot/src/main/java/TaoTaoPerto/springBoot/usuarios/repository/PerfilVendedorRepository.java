package TaoTaoPerto.springBoot.usuarios.repository;

import TaoTaoPerto.springBoot.usuarios.model.PerfilVendedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilVendedorRepository extends JpaRepository<PerfilVendedorModel, Long> {
    //Optional<PerfilVendedorModel> findByIdAndAtivo(Long id, boolean ativo);
    Optional<PerfilVendedorModel> findByIdAndUsuarioAtivo(Long id, boolean ativo);
    List<PerfilVendedorModel> findAllByUsuarioAtivo(boolean ativo);
}
