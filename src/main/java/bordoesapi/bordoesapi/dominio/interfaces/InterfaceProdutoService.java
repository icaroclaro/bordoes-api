package bordoesapi.bordoesapi.dominio.interfaces;

import bordoesapi.bordoesapi.dominio.entidade.Audio;
import bordoesapi.bordoesapi.dominio.entidade.Produto;

import java.util.List;

public interface InterfaceProdutoService {
    public List<Produto> getAll();
    public Produto getById(int id);
    public Produto save(Produto produto);
    public Produto update(Produto produto);
    public void delete(int id);
}
