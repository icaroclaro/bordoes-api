package bordoesapi.bordoesapi.aplicacao.dto;

import bordoesapi.bordoesapi.dominio.entidade.Carteira;
import bordoesapi.bordoesapi.dominio.entidade.Produto;

public record CarteiraDto(
        int id,
        int idCliente,
        int totalMoedas) {
    public CarteiraDto(Carteira carteira){
        this(carteira.getId(), carteira.getCliente().getId(), carteira.getTotalMoedas());
    }
}
