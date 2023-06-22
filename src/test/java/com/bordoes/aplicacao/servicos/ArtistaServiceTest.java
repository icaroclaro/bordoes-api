package com.bordoes.aplicacao.servicos;

import com.bordoes.aplicacao.dto.ArtistaDto;
import com.bordoes.aplicacao.exceptions.MyNotFoundException;
import com.bordoes.dominio.entidade.Artista;
import com.bordoes.infraestrutura.persistencia.ArtistaPersistencia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ArtistaServiceTest {
    @InjectMocks
    private ArtistaService1 artistaService;

    @Mock
    private ArtistaPersistencia artistaPersistencia;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeGetById_DeveRetornarArtistaDtoAoObterIdExistente() {
        // Arrange
        int id = 1;
        Artista artista = new Artista();
        artista.setId(id);
        when(artistaPersistencia.getById(id)).thenReturn(artista);

        // Act
        ArtistaDto result = artistaService.getById(id);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.id());
        verify(artistaPersistencia, times(1)).getById(id);
        Assertions.assertEquals(result.nome(), artista.getNome());
        Assertions.assertEquals(result.detalhe(), artista.getDetalhe());
        Assertions.assertEquals(result.instagram(), artista.getUrlFotoDetalhe());
        Assertions.assertEquals(result.urlFotoHome(), artista.getUrlFotoHome());
        Assertions.assertEquals(result.urlFotoDetalhe(), artista.getUrlFotoDetalhe());
        Assertions.assertEquals(result.habilitado(), artista.getHabilitado());
    }

    @Test
    public void testeGetById_DeveLancarExcecaoAoObterIdInexistente() {
        // Arrange
        int id = 1;
        when(artistaPersistencia.getById(id)).thenReturn(null);

        // Act & Assert
        Assertions.assertThrows(MyNotFoundException.class, () -> {
            artistaService.getById(id);
        });

        verify(artistaPersistencia, times(1)).getById(id);
    }



    @Test
    public void testeSalvar_ArtistaDtoValido_RetornaArtistaDto() {
        // Arrange
        ArtistaDto artistaDto = new ArtistaDto(1,"Igor Guimarães","Comediante Paulista...","@iginho","urlFotoHome.jpg", "urlFotoDetalhe.jpg",true);
        Artista artista = new Artista(1,"Igor Guimarães","Comediante Paulista...","@iginho","urlFotoHome.jpg", "urlFotoDetalhe.jpg",true);
        when(artistaPersistencia.save(any(Artista.class))).thenReturn(artista);

        // Act
        ArtistaDto result = artistaService.save(artistaDto);

        // Assert
        Assertions.assertNotNull(result);
        verify(artistaPersistencia, times(1)).save(any(Artista.class));
    }
    @Test
    public void testeSalvar_ArtistaDtoNulo_LancaExcecao() {
        // Arrange
        ArtistaDto artistaDto = null;

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            artistaService.save(artistaDto);
        });

        verify(artistaPersistencia, never()).save(any(Artista.class));
    }

    @Test
    public void testeAtualizacao_ArtistaDtoValido_RetornaArtistaDtoAtualizado() {
        // Arrange
        int id = 1;
        ArtistaDto artistaDto = new ArtistaDto(1,"Igor Guimarães","Comediante Paulista...","@iginho","urlFotoHome.jpg", "urlFotoDetalhe.jpg",true);
        Artista artista = new Artista();
        when(artistaPersistencia.getById(id)).thenReturn(artista);

        // Act
        ArtistaDto result = artistaService.update(artistaDto);

        // Assert
        Assertions.assertNotNull(result);
        verify(artistaPersistencia, times(1)).update(artista);
    }

    @Test
    public void testeAtualizacao_ArtistaDtoNulo_LancaExcecao() {
        // Arrange
        ArtistaDto artistaDto = null;

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            artistaService.update(artistaDto);
        });

        verify(artistaPersistencia, never()).getById(anyInt());
        verify(artistaPersistencia, never()).update(any(Artista.class));
    }

    @Test
    public void testeAtualizacao_IdArtistaDtoInexistente_LancaExcecao() {
        // Arrange
        int id = 1;
        ArtistaDto artistaDto = new ArtistaDto(1,"Igor Guimarães","Comediante Paulista...","@iginho","urlFotoHome.jpg", "urlFotoDetalhe.jpg",true);
        when(artistaPersistencia.getById(id)).thenReturn(null);

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            artistaService.update(artistaDto);
        });

        verify(artistaPersistencia, times(1)).getById(id);
        verify(artistaPersistencia, never()).update(any(Artista.class));
    }

    @Test
    public void testeExclusao_IdArtistaExistente_ExcluiArtista() {
        // Arrange
        int id = 1;
        Artista artista = new Artista();
        when(artistaPersistencia.getById(id)).thenReturn(artista);

        // Act
        artistaService.delete(id);

        // Assert
        verify(artistaPersistencia, times(1)).delete(id);
    }

    @Test
    public void testeExclusao_IdArtistaInexistente_LancaExcecao() {
        // Arrange
        int id = 1;
        when(artistaPersistencia.getById(id)).thenReturn(null);

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            artistaService.delete(id);
        });

        verify(artistaPersistencia, never()).delete(anyInt());
    }
}