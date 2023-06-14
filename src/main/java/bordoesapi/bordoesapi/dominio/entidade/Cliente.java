package bordoesapi.bordoesapi.dominio.entidade;

import bordoesapi.bordoesapi.aplicacao.dto.ClienteDto;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Cliente(){}

    public Cliente(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cliente(ClienteDto clienteDto) {
        this.name = clienteDto.name();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
