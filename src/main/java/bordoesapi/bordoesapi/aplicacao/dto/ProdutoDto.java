package bordoesapi.bordoesapi.aplicacao.dto;

import bordoesapi.bordoesapi.dominio.entidade.Produto;

public record ProdutoDto(
        int id,
        String descriao,
        double preco,
        int qtdMoedas) {
    public ProdutoDto(Produto produto){
        this(produto.getId(), produto.getDescricao(), produto.getPreco(), produto.getQtdMoedas());
    }
}
