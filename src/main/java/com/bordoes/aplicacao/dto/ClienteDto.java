package com.bordoes.aplicacao.dto;

import com.bordoes.dominio.entidade.Cliente;

public record ClienteDto(
        int id,
        String name) {
    public ClienteDto(Cliente cliente){
        this(cliente.getId(), cliente.getName());
    }
}
