package bordoesapi.bordoesapi.dominio.entidade;

import bordoesapi.bordoesapi.aplicacao.dto.AudioDto;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "audio")
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista_id")
    private Artista artista;
    private String descricao;
    private String urlAudio;
    private double preco;

    public Audio(){}

    public Audio(Artista artista, String descricao, String urlAudio, double preco) {
        this.artista = artista;
        this.descricao = descricao;
        this.urlAudio = urlAudio;
        this.preco = preco;
    }

    public Audio(AudioDto audioDto) {
        this.descricao = audioDto.descricao();
        this.urlAudio = audioDto.urlAudio();
        this.preco = audioDto.preco();
    }

    public int getId() {
        return id;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audio audio = (Audio) o;
        return id == audio.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
