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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AudioService {

    @Autowired
    private AudioPersistencia audioPersistencia;
    @Autowired
    private ArtistaPersistencia artistaPersistencia;

    @Autowired
    private AWSService awsService;

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

    public AudioDto saveWithFile(AudioUploadDto audioUploadDto) {
        try {
            String nomeDoAudio = StringUtils.cleanPath(audioUploadDto.audio().getOriginalFilename());
            AudioDto audioDto = new AudioDto(audioUploadDto, nomeDoAudio);

            if(nomeDoAudio.contains("..")){
                throw new RuntimeException("Desculpa nome do arquivo contem sequencia de caminho invalido " +  nomeDoAudio);
            }
            var caminho = localDeArmazenamento + UUID.randomUUID() + "." + extrarirExtensao(audioUploadDto.audio().getOriginalFilename());

            //Files.copy(audioUploadDto.audio().getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            /*
            File file = new File(audioUploadDto.audio().getOriginalFilename());
            try (OutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(audioUploadDto.audio().getBytes());
            }
            */

            //awsService.uploadFileBucketS3(audioUploadDto.audio(), "audio\\" + nomeDoAudio);
            awsService.uploadFileBucketS3(audioUploadDto.audio(), nomeDoAudio);

            if(audioDto == null){
                throw  new RuntimeException("Não é permitido salvar um Audio nulo!");
            }
            Artista artista = artistaPersistencia.getById(audioDto.idArtista());

            if (artista == null) {
                throw new RuntimeException("Nenhum Artista encontrado para este ID!");
            }

            Audio audio = new Audio(artista,audioDto.descricao(), caminho, audioDto.preco());
            //audio.setUrlAudio(caminho);
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
