package com.bordoes.aplicacao.servicos;

import com.bordoes.dominio.entidade.Audio;
import com.bordoes.infraestrutura.persistencia.ArtistaPersistencia;
import com.bordoes.infraestrutura.persistencia.AudioPersistencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class AudioServiceTest {
    @InjectMocks
    private AudioService audioService;

    @Mock
    private AudioPersistencia artistaPersistencia;

}