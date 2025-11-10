package TaoTaoPerto.springBoot.usuarios.repository;

import TaoTaoPerto.springBoot.usuarios.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
    List<EnderecoModel> findByUsuarioId(Long usuarioId);
    List<EnderecoModel> findByUsuarioIdAndAtivoTrue(Long usuarioId);
}
