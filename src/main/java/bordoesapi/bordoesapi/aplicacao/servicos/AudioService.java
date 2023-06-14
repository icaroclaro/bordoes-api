package bordoesapi.bordoesapi.aplicacao.servicos;

import bordoesapi.bordoesapi.aplicacao.dto.ArtistaDto;
import bordoesapi.bordoesapi.aplicacao.dto.AudioDto;
import bordoesapi.bordoesapi.dominio.entidade.Artista;
import bordoesapi.bordoesapi.dominio.entidade.Audio;
import bordoesapi.bordoesapi.infraestrutura.persistencia.ArtistaPersistencia;
import bordoesapi.bordoesapi.infraestrutura.persistencia.AudioPersistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AudioService {

    @Autowired
    private AudioPersistencia audioPersistencia;
    @Autowired
    private ArtistaPersistencia artistaPersistencia;

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
