package bordoesapi.bordoesapi.infraestrutura.repositories;

import bordoesapi.bordoesapi.dominio.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
