package com.bordoes.aplicacao.servicos;

import com.bordoes.aplicacao.dto.CarteiraDto;
import com.bordoes.dominio.entidade.Carteira;
import com.bordoes.dominio.entidade.Cliente;
import com.bordoes.infraestrutura.persistencia.CarteiraPersistencia;
import com.bordoes.infraestrutura.persistencia.ClientePersistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraPersistencia carteiraPersistencia;
    @Autowired
    private ClientePersistencia clientePersistencia;

    public List<CarteiraDto> getAll(){
        try {
            List<Carteira> carteiras =  carteiraPersistencia.getAll();
            if(carteiras == null){
                throw new RuntimeException("Carteiras não encontrados!");
            }
            List<CarteiraDto> dtos =  carteiras.stream()
                    .map(CarteiraDto::new)
                    .collect(Collectors.toList());
            return dtos;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public CarteiraDto getById(int id) {
        try {
            Carteira carteira = carteiraPersistencia.getById(id);
            if(carteira == null){
                throw new RuntimeException("Nenhuma Carteira encontrado para este ID!");
            }
            CarteiraDto dto = new CarteiraDto(carteira);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public CarteiraDto save(CarteiraDto carteiraDto) {
        try {
            if(carteiraDto == null){
                throw  new RuntimeException("Não é permitido salvar uma Carteira nulo!");
            }

            Cliente cliente = clientePersistencia.getById(carteiraDto.idCliente());

            if (cliente == null) {
                throw new RuntimeException("Nenhum Cliente encontrado para este ID!");
            }

            Carteira carteira = new Carteira(cliente,carteiraDto.totalMoedas());
            carteiraPersistencia.save(carteira);
            CarteiraDto dto = new CarteiraDto(carteira);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public CarteiraDto update(CarteiraDto carteiraDto) {
        try {
            if(carteiraDto == null){
                throw  new RuntimeException("Não é permitido atualizar uma Carteira nulo!");
            }

            Carteira carteira = carteiraPersistencia.getById(carteiraDto.id());

            if(carteira == null){
                throw  new RuntimeException("Nenhuma Carteira encontrado para este ID!");
            }

            Cliente cliente = clientePersistencia.getById(carteiraDto.idCliente());

            if(cliente == null){
                throw  new RuntimeException("Nenhum Cliente encontrado para este ID!");
            }

            carteira.setCliente(cliente);
            carteira.setTotalMoedas(carteiraDto.totalMoedas());

            carteiraPersistencia.update(carteira);
            CarteiraDto dto = new CarteiraDto(carteira);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Carteira carteira = carteiraPersistencia.getById(id);

            if(carteira == null){
                throw new RuntimeException("Nenhuma Carteira encontrado para este ID!");
            }
            carteiraPersistencia.delete(id);
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
