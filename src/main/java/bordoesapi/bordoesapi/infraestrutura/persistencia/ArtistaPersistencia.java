package bordoesapi.bordoesapi.infraestrutura.persistencia;

import bordoesapi.bordoesapi.dominio.entidade.Artista;
import bordoesapi.bordoesapi.dominio.interfaces.InterfaceArtistaService;
import bordoesapi.bordoesapi.infraestrutura.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ArtistaPersistencia implements InterfaceArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;


    @Override
    public List<Artista> getAll() {
        return   artistaRepository.findAll();
    }

    @Override
    public Artista getById(int id) {
        return artistaRepository.getReferenceById(id);
    }

    @Override
    public Artista save(Artista artista) {
        return artistaRepository.save(artista);
    }

    @Override
    public Artista update(Artista artista) {
        return artistaRepository.save(artista);
    }

    @Override
    public void delete(int id) {
        Artista artista = artistaRepository.getReferenceById(id);
        artistaRepository.delete(artista);
    }
}
