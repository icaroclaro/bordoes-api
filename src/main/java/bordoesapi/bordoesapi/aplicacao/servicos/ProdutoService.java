package bordoesapi.bordoesapi.aplicacao.servicos;

import bordoesapi.bordoesapi.aplicacao.dto.AudioDto;
import bordoesapi.bordoesapi.aplicacao.dto.ProdutoDto;
import bordoesapi.bordoesapi.dominio.entidade.Artista;
import bordoesapi.bordoesapi.dominio.entidade.Audio;
import bordoesapi.bordoesapi.dominio.entidade.Produto;
import bordoesapi.bordoesapi.infraestrutura.persistencia.ProdutoPersistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProdutoService {
    @Autowired
    private ProdutoPersistencia produtoPersistencia;

    public List<ProdutoDto> getAll(){
        try {
            List<Produto> produtos = produtoPersistencia.getAll();
            if(produtos == null){
                throw new RuntimeException("Produtos não encontrados!");
            }
            List<ProdutoDto> dtos =  produtos.stream()
                    .map(ProdutoDto::new)
                    .collect(Collectors.toList());
            return dtos;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ProdutoDto getById(int id) {
        try {
            Produto produto = produtoPersistencia.getById(id);
            if(produto == null){
                throw new RuntimeException("Nenhum Produto encontrado para este ID!");
            }
            ProdutoDto dto = new ProdutoDto(produto);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ProdutoDto save(ProdutoDto produtoDto) {
        try {
            if(produtoDto == null){
                throw  new RuntimeException("Não é permitido salvar um Produto nulo!");
            }

            Produto produto = new Produto(produtoDto);
            produtoPersistencia.save(produto);
            ProdutoDto dto = new ProdutoDto(produto);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ProdutoDto update(ProdutoDto produtoDto) {
        try {
            if(produtoDto == null){
                throw  new RuntimeException("Não é permitido atualizar um Produto nulo!");
            }

            Produto produto = produtoPersistencia.getById(produtoDto.id());

            if(produto == null){
                throw  new RuntimeException("Nenhum Produto encontrado para este ID!");
            }

            produto.setDescricao(produtoDto.descriao());
            produto.setPreco(produtoDto.preco());
            produto.setQtdMoedas(produtoDto.qtdMoedas());

            produtoPersistencia.update(produto);
            ProdutoDto dto = new ProdutoDto(produto);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Produto produto = produtoPersistencia.getById(id);

            if(produto == null){
                throw new RuntimeException("Nenhum Produto encontrado para este ID!");
            }
            produtoPersistencia.delete(id);
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
