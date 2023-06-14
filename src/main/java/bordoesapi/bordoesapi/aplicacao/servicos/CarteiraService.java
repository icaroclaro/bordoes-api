package bordoesapi.bordoesapi.aplicacao.servicos;

import bordoesapi.bordoesapi.aplicacao.dto.AudioDto;
import bordoesapi.bordoesapi.aplicacao.dto.CarteiraDto;
import bordoesapi.bordoesapi.dominio.entidade.Artista;
import bordoesapi.bordoesapi.dominio.entidade.Audio;
import bordoesapi.bordoesapi.dominio.entidade.Carteira;
import bordoesapi.bordoesapi.dominio.entidade.Cliente;
import bordoesapi.bordoesapi.infraestrutura.persistencia.ArtistaPersistencia;
import bordoesapi.bordoesapi.infraestrutura.persistencia.AudioPersistencia;
import bordoesapi.bordoesapi.infraestrutura.persistencia.CarteiraPersistencia;
import bordoesapi.bordoesapi.infraestrutura.persistencia.ClientePersistencia;
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
