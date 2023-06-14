package bordoesapi.bordoesapi.aplicacao.servicos;

import bordoesapi.bordoesapi.aplicacao.dto.ArtistaDto;
import bordoesapi.bordoesapi.dominio.entidade.Artista;
import bordoesapi.bordoesapi.infraestrutura.persistencia.ArtistaPersistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ArtistaService {

    @Autowired
    ArtistaPersistencia artistaPersistencia;
    public List<ArtistaDto> getAll() {
        try {
            List<Artista> artistas =  artistaPersistencia.getAll();
            if(artistas == null){
                throw new RuntimeException("Artistas não encontrados!");
            }
            List<ArtistaDto> dtos =  artistas.stream()
                    .map(ArtistaDto::new)
                    .collect(Collectors.toList());
            return dtos;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
    public ArtistaDto getById(int id) {
        try {
            Artista artista = artistaPersistencia.getById(id);
            if(artista == null){
                throw new RuntimeException("Nenhum artista encontrado para este ID!");
            }
            ArtistaDto dto = new ArtistaDto(artista);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ArtistaDto save(ArtistaDto artistaDto) {
        try {
            if(artistaDto == null){
                throw  new RuntimeException("Não é permitido salvar um artista nulo!");
            }
            Artista artista = new Artista(artistaDto);
            artistaPersistencia.save(artista);
            ArtistaDto dto = new ArtistaDto(artista);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ArtistaDto update(ArtistaDto artistaDto) {
        try {
            if(artistaDto == null){
                throw  new RuntimeException("Não é permitido atualizar um artista nulo!");
            }

            Artista artista = artistaPersistencia.getById(artistaDto.id());

            if(artista == null){
                throw  new RuntimeException("Nenhum artista encontrado para este ID!");
            }

            artista.setNome(artistaDto.nome());
            artista.setDetalhe(artistaDto.detalhe());
            artista.setInstagram(artistaDto.instagram());
            artista.setUrlFotoHome(artista.getUrlFotoHome());
            artista.setUrlFotoDetalhe(artista.getUrlFotoDetalhe());
            artista.setHabilitado(artistaDto.habilitao());

            artistaPersistencia.update(artista);
            ArtistaDto dto = new ArtistaDto(artista);
            return dto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Artista artista = artistaPersistencia.getById(id);
            if(artista == null){
                throw new RuntimeException("Nenhum artista encontrado para este ID!");
            }
            artistaPersistencia.delete(id);
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
