package bordoesapi.bordoesapi.infraestrutura.persistencia;

import bordoesapi.bordoesapi.dominio.entidade.Carteira;
import bordoesapi.bordoesapi.dominio.interfaces.InterfaceCarteiraService;
import bordoesapi.bordoesapi.infraestrutura.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CarteiraPersistencia implements InterfaceCarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;
    @Override
    public List<Carteira> getAll() {
        return carteiraRepository.findAll();
    }

    @Override
    public Carteira getById(int id) {
        return carteiraRepository.getReferenceById(id);
    }

    @Override
    public Carteira save(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    @Override
    public Carteira update(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    @Override
    public void delete(int id) {
        Carteira carteira = carteiraRepository.getReferenceById(id);
        carteiraRepository.delete(carteira);
    }
}
