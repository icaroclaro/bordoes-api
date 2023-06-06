package bordoesapi.bordoesapi.aplicacao.dto;

import bordoesapi.bordoesapi.dominio.entidade.Artista;
import bordoesapi.bordoesapi.dominio.entidade.Audio;

public record AudioDto(
        int id,
        int idArtista,
        String descricao,
        String urlAudio,
        double preco) {
    public AudioDto(Audio audio){
        this(audio.getId(),audio.getArtista().getId(), audio.getDescricao(), audio.getUrlAudio(), audio.getPreco());
    }
}
