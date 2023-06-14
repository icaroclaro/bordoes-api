package bordoesapi.bordoesapi.api.controllers;

import bordoesapi.bordoesapi.aplicacao.dto.AudioDto;
import bordoesapi.bordoesapi.aplicacao.dto.CarteiraDto;
import bordoesapi.bordoesapi.aplicacao.servicos.AudioService;
import bordoesapi.bordoesapi.aplicacao.servicos.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping
    public ResponseEntity<List<CarteiraDto>> getAll(){
        List<CarteiraDto> carteiras = carteiraService.getAll();
        return ResponseEntity.ok(carteiras);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarteiraDto> getById(@PathVariable int id){
        CarteiraDto carteira = carteiraService.getById(id);
        return ResponseEntity.ok(carteira);
    }

    @PostMapping
    public ResponseEntity<CarteiraDto> save(@RequestBody CarteiraDto carteiraDto, UriComponentsBuilder uriBuilder){
        CarteiraDto carteira = carteiraService.save(carteiraDto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(carteira.id()).toUri();
        return ResponseEntity.created(uri).body(carteira);
    }

    @PutMapping
    public ResponseEntity<CarteiraDto> update(@RequestBody CarteiraDto carteiraDto){
        CarteiraDto carteira = carteiraService.update(carteiraDto);
        return ResponseEntity.ok(carteira);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        carteiraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
