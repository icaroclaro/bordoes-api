package com.bordoes.aplicacao.dto;

import com.bordoes.dominio.entidade.Carteira;

public record CarteiraDto(
        int id,
        int idCliente,
        int totalMoedas) {
    public CarteiraDto(Carteira carteira){
        this(carteira.getId(), carteira.getCliente().getId(), carteira.getTotalMoedas());
    }
}
