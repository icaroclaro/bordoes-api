package com.bordoes.aplicacao.servicos;

import com.bordoes.aplicacao.dto.AudioDto;
import com.bordoes.aplicacao.dto.AudioUploadDto;
import com.bordoes.dominio.entidade.Artista;
import com.bordoes.dominio.entidade.Audio;
import com.bordoes.infraestrutura.persistencia.ArtistaPersistencia;
import com.bordoes.infraestrutura.persistencia.AudioPersistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AudioService {

    @Autowired
    private AudioPersistencia audioPersistencia;
    @Autowired
    private ArtistaPersistencia artistaPersistencia;

    @Value("${file.upload-dir}")
    private String localDeArmazenamento;

    public List<AudioDto> getAll(){
        try {
            List<Audio> audios =  audioPersistencia.getAll();
            if(audios == null){
                throw new RuntimeException("Audios não encontrados!");
            }
            List<AudioDto> dtos =  audios.stream()
                    .map(AudioDto::new)
                    .collect(Collectors.toList());
            return dtos;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public AudioDto getById(int id) {
        try {
            Audio audio = audioPersistencia.getById(id);
            if(audio == null){
                throw new RuntimeException("Nenhum Audio encontrado para este ID!");
            }
            AudioDto dto = new AudioDto(audio);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public AudioDto save(AudioDto audioDto) {
        try {
            if(audioDto == null){
                throw  new RuntimeException("Não é permitido salvar um Audio nulo!");
            }

            Artista artista = artistaPersistencia.getById(audioDto.idArtista());

            if (artista == null) {
                throw new RuntimeException("Nenhum Artista encontrado para este ID!");
            }

            Audio audio = new Audio(artista,audioDto.descricao(), audioDto.urlAudio(), audioDto.preco());
            audioPersistencia.save(audio);
            AudioDto dto = new AudioDto(audio);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public AudioDto save2 (AudioUploadDto audioUploadDto) {
    //public AudioDto save2 (AudioUploadDto audioDto) {
        try {
            String nomeDoAudio = StringUtils.cleanPath(audioUploadDto.audio().getOriginalFilename());
            AudioDto audioDto = new AudioDto(audioUploadDto, nomeDoAudio);

            if(nomeDoAudio.contains("..")){
                throw new RuntimeException("Desculpa nome do arquivo contem sequencia de caminho invalido " +  nomeDoAudio);
            }
            var caminho = localDeArmazenamento + UUID.randomUUID() + "." + extrarirExtensao(audioUploadDto.audio().getOriginalFilename());
            Files.copy(audioUploadDto.audio().getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);

            if(audioDto == null){
                throw  new RuntimeException("Não é permitido salvar um Audio nulo!");
            }
            Artista artista = artistaPersistencia.getById(audioDto.idArtista());

            if (artista == null) {
                throw new RuntimeException("Nenhum Artista encontrado para este ID!");
            }

            Audio audio = new Audio(artista,audioDto.descricao(), nomeDoAudio, audioDto.preco());
            audio.setUrlAudio(nomeDoAudio);
            audioPersistencia.save(audio);
            AudioDto dto = new AudioDto(audio);
            return dto;

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    private String extrarirExtensao(String nomeArquivo) {
        int i = nomeArquivo.lastIndexOf(".");
        return nomeArquivo.substring(i + 1);
    }

    public AudioDto update(AudioDto audioDto) {
        try {
            if(audioDto == null){
                throw  new RuntimeException("Não é permitido atualizar um Audio nulo!");
            }

            Audio audio = audioPersistencia.getById(audioDto.id());

            if(audio == null){
                throw  new RuntimeException("Nenhum Audio encontrado para este ID!");
            }

            Artista artista = artistaPersistencia.getById(audioDto.idArtista());

            if(artista == null){
                throw  new RuntimeException("Nenhum Artista encontrado para este ID!");
            }

            audio.setArtista(artista);
            audio.setDescricao(audioDto.descricao());
            audio.setUrlAudio(audioDto.urlAudio());
            audio.setPreco(audioDto.preco());

            audioPersistencia.update(audio);
            AudioDto dto = new AudioDto(audio);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Audio audio = audioPersistencia.getById(id);

            if(audio == null){
                throw new RuntimeException("Nenhum Audio encontrado para este ID!");
            }
            audioPersistencia.delete(id);
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
