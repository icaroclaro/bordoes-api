package bordoesapi.bordoesapi.aplicacao.dto;

import bordoesapi.bordoesapi.dominio.entidade.Cliente;
import bordoesapi.bordoesapi.dominio.entidade.Produto;

public record ClienteDto(
        int id,
        String name) {
    public ClienteDto(Cliente cliente){
        this(cliente.getId(), cliente.getName());
    }
}
