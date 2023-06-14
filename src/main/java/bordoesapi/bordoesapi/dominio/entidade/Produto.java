package bordoesapi.bordoesapi.dominio.entidade;

import bordoesapi.bordoesapi.aplicacao.dto.ProdutoDto;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    private double preco;
    @Column(name = "qtd_moedas")
    private int qtdMoedas;

    public Produto (){}

    public Produto(String descricao, double preco, int qtdMoedas) {
        this.descricao = descricao;
        this.preco = preco;
        this.qtdMoedas = qtdMoedas;
    }

    public Produto(ProdutoDto produtoDto) {
        this.descricao = produtoDto.descriao();
        this.preco = produtoDto.preco();
        this.qtdMoedas = getQtdMoedas();
    }


    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdMoedas() {
        return qtdMoedas;
    }

    public void setQtdMoedas(int qtdMoedas) {
        this.qtdMoedas = qtdMoedas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
