package bordoesapi.bordoesapi.api.controllers;

import bordoesapi.bordoesapi.aplicacao.dto.CarteiraDto;
import bordoesapi.bordoesapi.aplicacao.dto.ClienteDto;
import bordoesapi.bordoesapi.aplicacao.servicos.CarteiraService;
import bordoesapi.bordoesapi.aplicacao.servicos.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAll(){
        List<ClienteDto> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getById(@PathVariable int id){
        ClienteDto cliente = clienteService.getById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto clienteDto, UriComponentsBuilder uriBuilder){
        ClienteDto cliente = clienteService.save(clienteDto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping
    public ResponseEntity<ClienteDto> update(@RequestBody ClienteDto clienteDto){
        ClienteDto cliente = clienteService.update(clienteDto);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
