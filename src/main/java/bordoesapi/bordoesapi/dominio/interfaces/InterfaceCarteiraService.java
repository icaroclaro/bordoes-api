package bordoesapi.bordoesapi.dominio.interfaces;

import bordoesapi.bordoesapi.dominio.entidade.Carteira;
import bordoesapi.bordoesapi.dominio.entidade.Produto;

import java.util.List;

public interface InterfaceCarteiraService {
    public List<Carteira> getAll();
    public Carteira getById(int id);
    public Carteira save(Carteira carteira);
    public Carteira update(Carteira carteira);
    public void delete(int id);
}
