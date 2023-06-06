package bordoesapi.bordoesapi.dominio.entidade;

import bordoesapi.bordoesapi.aplicacao.dto.ArtistaDto;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String detalhe;
    private String instagram;
    private boolean habilitado;

    public Artista(){}

    public Artista(String nome, String detalhe, String instagram, boolean habilitado) {
        this.nome = nome;
        this.detalhe = detalhe;
        this.instagram = instagram;
        this.habilitado = true;
    }

    public Artista(ArtistaDto artistaDto) {
        this.nome = artistaDto.nome();
        this.detalhe = artistaDto.detalhe();
        this.instagram = artistaDto.instagram();
        this.habilitado = true;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return id == artista.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
