package com.bordoes.aplicacao.dto;

import org.springframework.web.multipart.MultipartFile;

public record AudioUploadDto(
        int idArtista,
        String descricao,
        MultipartFile audio,
        double preco) {
}