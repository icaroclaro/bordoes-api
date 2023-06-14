package bordoesapi.bordoesapi.infraestrutura.persistencia;

import bordoesapi.bordoesapi.dominio.entidade.Produto;
import bordoesapi.bordoesapi.dominio.interfaces.InterfaceProdutoService;
import bordoesapi.bordoesapi.infraestrutura.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProdutoPersistencia implements InterfaceProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Override
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto getById(int id) {
        return produtoRepository.getReferenceById(id);
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto update(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public void delete(int id) {
        Produto produto = produtoRepository.getReferenceById(id);
        produtoRepository.delete(produto);
    }
}
