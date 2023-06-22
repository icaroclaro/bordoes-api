package com.bordoes.aplicacao.dto;

import com.bordoes.dominio.entidade.Artista;

public record ArtistaDto(
        int id,
        String nome,
        String detalhe,
        String instagram,
        String urlFotoHome,
        String urlFotoDetalhe,
        boolean habilitado) {
    public ArtistaDto(Artista artista){
        this(artista.getId(), artista.getNome(), artista.getDetalhe(), artista.getInstagram(),artista.getUrlFotoHome(), artista.getUrlFotoDetalhe() ,artista.getHabilitado());
    }
}
