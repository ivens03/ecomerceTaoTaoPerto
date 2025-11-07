package TaoTaoPerto.springBoot.produtos.repository;

import TaoTaoPerto.springBoot.produtos.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    Optional<ProdutoModel> findByIdAndAtivoTrue(Long id);
    List<ProdutoModel> findAllByAtivoTrue();
}
