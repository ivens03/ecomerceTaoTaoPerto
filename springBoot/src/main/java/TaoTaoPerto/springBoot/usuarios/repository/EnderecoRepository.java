package TaoTaoPerto.springBoot.usuarios.repository;

import TaoTaoPerto.springBoot.usuarios.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer> {
}
