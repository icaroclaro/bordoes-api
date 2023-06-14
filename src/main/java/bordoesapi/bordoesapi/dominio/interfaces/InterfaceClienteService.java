package bordoesapi.bordoesapi.dominio.interfaces;

import bordoesapi.bordoesapi.dominio.entidade.Carteira;
import bordoesapi.bordoesapi.dominio.entidade.Cliente;

import java.util.List;

public interface InterfaceClienteService {
    public List<Cliente> getAll();
    public Cliente getById(int id);
    public Cliente save(Cliente cliente);
    public Cliente update(Cliente cliente);
    public void delete(int id);
}
