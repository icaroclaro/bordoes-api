package com.bordoes.aplicacao.dto;

import com.bordoes.dominio.entidade.Audio;

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
