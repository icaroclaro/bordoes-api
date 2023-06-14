package bordoesapi.bordoesapi.infraestrutura.repositories;

import bordoesapi.bordoesapi.dominio.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
