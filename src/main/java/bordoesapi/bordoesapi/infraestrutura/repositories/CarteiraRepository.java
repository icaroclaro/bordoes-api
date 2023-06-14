package bordoesapi.bordoesapi.infraestrutura.repositories;

import bordoesapi.bordoesapi.dominio.entidade.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {
}
