package bordoesapi.bordoesapi.dominio.entidade;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "carteiras")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;
    @Column(name = "total_moedas")
    private int totalMoedas;

    public Carteira(){}

    public Carteira(Cliente cliente) {
        this.cliente = cliente;
        this.totalMoedas = 0;
    }

    public Carteira(Cliente cliente, int totalMoedas) {
        this.cliente = cliente;
        this.totalMoedas = totalMoedas;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getTotalMoedas() {
        return totalMoedas;
    }

    public void setTotalMoedas(int totalMoedas) {
        this.totalMoedas = totalMoedas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira = (Carteira) o;
        return id == carteira.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
