package bordoesapi.bordoesapi.infraestrutura.repositories;

import bordoesapi.bordoesapi.dominio.entidade.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

}
