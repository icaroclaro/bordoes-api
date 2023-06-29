package com.bordoes.api.controllers;

import com.bordoes.aplicacao.dto.AudioDto;
import com.bordoes.aplicacao.dto.AudioUploadDto;
import com.bordoes.aplicacao.servicos.AudioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/audios", produces = {"application/json"})
@Slf4j
public class AudioController {

    @Value("${file.upload-dir}")
    private String localDeArmazenamento;

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

    @PostMapping("/arquivoDeAudio")
    public ResponseEntity<AudioDto> salvarArquivo(@ModelAttribute AudioUploadDto audioUploadDto, UriComponentsBuilder uriBuilder){
   // public ResponseEntity<AudioDto> salvarArquivo(@ModelAttribute AudioUploadDto audioUploadDto, @RequestParam("arquivo") MultipartFile arquivoDeAudio){
        AudioDto audio = audioService.saveWithFile(audioUploadDto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(audio.id()).toUri();
        return ResponseEntity.created(uri).body(audio);
 //       return ResponseEntity.ok(new AudioDto(1,1,"","",10.0));
    }

    private String extrarirExtensao(String nomeArquivo) { //metodo que estrai a extensão
        int i = nomeArquivo.lastIndexOf(".");
        return nomeArquivo.substring(i + 1);
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
