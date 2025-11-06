package TaoTaoPerto.springBoot.produtos.repository;

import TaoTaoPerto.springBoot.produtos.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
    Optional<CategoriaModel> findByIdAndAtivoTrue(Long id);
    List<CategoriaModel> findAllByAtivoTrue();
}
