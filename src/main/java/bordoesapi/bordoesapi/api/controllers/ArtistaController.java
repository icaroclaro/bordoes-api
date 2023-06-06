package bordoesapi.bordoesapi.api.controllers;

import bordoesapi.bordoesapi.aplicacao.dto.ArtistaDto;
import bordoesapi.bordoesapi.aplicacao.servicos.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<List<ArtistaDto>> getAll(){
        List<ArtistaDto> artistas = artistaService.getAll();
        return ResponseEntity.ok(artistas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDto> getById(@PathVariable int id){
        ArtistaDto artistasDt0 = artistaService.getById(id);
        return ResponseEntity.ok(artistasDt0);
    }

    @PostMapping
    public ResponseEntity<ArtistaDto> save(@RequestBody ArtistaDto artistaDto, UriComponentsBuilder uriBuilder){
        ArtistaDto artista = artistaService.save(artistaDto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(artista.id()).toUri();
        return ResponseEntity.created(uri).body(artista);
    }

    @PutMapping
    public ResponseEntity<ArtistaDto> update(@RequestBody ArtistaDto artistaDto){
        ArtistaDto artista = artistaService.update(artistaDto);
        return ResponseEntity.ok(artista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        artistaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
