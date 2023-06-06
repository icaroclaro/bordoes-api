package bordoesapi.bordoesapi.aplicacao.dto;

import bordoesapi.bordoesapi.dominio.entidade.Artista;

public record ArtistaDto(
        int id,
        String nome,
        String detalhe,
        String instagram,
        boolean habilitao) {
    public ArtistaDto(Artista artista){
        this(artista.getId(), artista.getNome(), artista.getDetalhe(), artista.getInstagram(),artista.getHabilitado());
    }
}
