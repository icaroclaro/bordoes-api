package bordoesapi.bordoesapi.api.controllers;

import bordoesapi.bordoesapi.aplicacao.dto.ArtistaDto;
import bordoesapi.bordoesapi.aplicacao.dto.AudioDto;
import bordoesapi.bordoesapi.aplicacao.servicos.ArtistaService;
import bordoesapi.bordoesapi.aplicacao.servicos.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/audios")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @GetMapping
    public ResponseEntity<List<AudioDto>> getAll(){
        List<AudioDto> audios = audioService.getAll();
        return ResponseEntity.ok(audios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AudioDto> getById(@PathVariable int id){
        AudioDto audio = audioService.getById(id);
        return ResponseEntity.ok(audio);
    }

    @PostMapping
    public ResponseEntity<AudioDto> save(@RequestBody AudioDto audioDto, UriComponentsBuilder uriBuilder){
        AudioDto audio = audioService.save(audioDto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(audio.id()).toUri();
        return ResponseEntity.created(uri).body(audio);
    }

    @PutMapping
    public ResponseEntity<AudioDto> update(@RequestBody AudioDto audioDto){
        AudioDto audio = audioService.update(audioDto);
        return ResponseEntity.ok(audio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        audioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
