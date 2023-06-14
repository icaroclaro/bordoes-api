package bordoesapi.bordoesapi.aplicacao.servicos;

import bordoesapi.bordoesapi.aplicacao.dto.ClienteDto;
import bordoesapi.bordoesapi.aplicacao.dto.ProdutoDto;
import bordoesapi.bordoesapi.dominio.entidade.Cliente;
import bordoesapi.bordoesapi.dominio.entidade.Produto;
import bordoesapi.bordoesapi.infraestrutura.persistencia.ClientePersistencia;
import bordoesapi.bordoesapi.infraestrutura.persistencia.ProdutoPersistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ClienteService {
    @Autowired
    private ClientePersistencia clientePersistencia;

    public List<ClienteDto> getAll(){
        try {
            List<Cliente> clientes = clientePersistencia.getAll();
            if(clientes == null){
                throw new RuntimeException("Clientes não encontrados!");
            }
            List<ClienteDto> dtos =  clientes.stream()
                    .map(ClienteDto::new)
                    .collect(Collectors.toList());
            return dtos;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ClienteDto getById(int id) {
        try {
            Cliente cliente = clientePersistencia.getById(id);
            if(cliente == null){
                throw new RuntimeException("Nenhum Cliente encontrado para este ID!");
            }
            ClienteDto dto = new ClienteDto(cliente);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ClienteDto save(ClienteDto clienteDto) {
        try {
            if(clienteDto == null){
                throw  new RuntimeException("Não é permitido salvar um Cliente nulo!");
            }

            Cliente cliente = new Cliente(clienteDto);
            clientePersistencia.save(cliente);
            ClienteDto dto = new ClienteDto(cliente);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ClienteDto update(ClienteDto clienteDto) {
        try {
            if(clienteDto == null){
                throw  new RuntimeException("Não é permitido atualizar um Cliente nulo!");
            }

            Cliente cliente = clientePersistencia.getById(clienteDto.id());

            if(cliente == null){
                throw  new RuntimeException("Nenhum Cliente encontrado para este ID!");
            }

            cliente.setName(clienteDto.name());

            clientePersistencia.update(cliente);
            ClienteDto dto = new ClienteDto(cliente);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Cliente cliente = clientePersistencia.getById(id);

            if(cliente == null){
                throw new RuntimeException("Nenhum Cliente encontrado para este ID!");
            }
            clientePersistencia.delete(id);
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
