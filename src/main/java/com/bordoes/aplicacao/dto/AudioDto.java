package com.bordoes.aplicacao.dto;

import com.bordoes.dominio.entidade.Audio;
import org.springframework.web.multipart.MultipartFile;

public record AudioDto(
        int id,
        int idArtista,
        String descricao,
        String urlAudio,
        //MultipartFile audio,
        double preco) {
    public AudioDto(Audio audio){
    //public AudioDto(Audio audio, MultipartFile arquivoAudio){
        this(audio.getId(),audio.getArtista().getId(), audio.getDescricao(), audio.getUrlAudio(), audio.getPreco());
    }

    public AudioDto(AudioUploadDto audioUploadDto, String nomeDoAudio) {
        this(0,audioUploadDto.idArtista(), audioUploadDto.descricao(), nomeDoAudio, audioUploadDto.preco());
    }
}
