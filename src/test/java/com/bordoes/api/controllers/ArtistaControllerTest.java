package com.bordoes.api.controllers;

import com.bordoes.aplicacao.dto.ArtistaDto;
import com.bordoes.aplicacao.servicos.ArtistaService1;
import com.bordoes.infraestrutura.repositories.ArtistaRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ArtistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ArtistaRepository repository;

    @Autowired
    //private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJacksonTester;
    private JacksonTester<ArtistaDto> artistaDtoJacksonTester;

    @MockBean //para diser que quando for utilizar esse serivce fazer um mock e nao ir no banco de dados
    //private AgendaDeConsultasService agendaDeConsultasService;
    private ArtistaService1 artistaService;

    /*
    @Test
    @DisplayName("Deveria Devolver codigo http 400 quando informações estão invalidas")
    @WithMockUser//para criar um usuario mocado apensa para testar
    void cadastrarArtista_status400() throws Exception {
        var resposta = mockMvc.perform(post("/artistas")) //dispara a requisição para o controller
                .andReturn().getResponse();

        assertThat(resposta.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());//para pegar e testar se o codigo http veio 400

    }

    @Test
    @DisplayName("Deveria Devolver codigo http 200 quando informações estão validas")
    @WithMockUser //para criar um usuario mocado apensa para testar
    void cadastrarArtista_status200() throws Exception {

        var artistaDto = new ArtistaDto(1, "Igor Guimaraes", "Comediante", "@igorgzismo", "fotoHome.jpa", "fotoDetalhe.jpg", true);
        when(artistaService.save(any())).thenReturn(artistaDto);

        var resposta = mockMvc
                .perform(
                        post("/consultas") //dispara a requisição para o controller
                                .contentType(MediaType.APPLICATION_JSON)//Para levar o cabeçalho
                                .content(artistaDtoJacksonTester.write(
                                        new ArtistaDto(1, "Igor Guimaraes", "Comediante", "@igorgzismo", "fotoHome.jpa", "fotoDetalhe.jpg", true)
                                ).getJson()) //criar um Json
                )
                .andReturn().getResponse();

        assertThat(resposta.getStatus()).isEqualTo(HttpStatus.OK.value());//para pegar e testar se o codigo http veio 200

        var jsonEsperado = artistaDtoJacksonTester.write(artistaDto).getJson();

        assertThat(resposta.getContentAsString()).isEqualTo(jsonEsperado);
    }
    @Test
    public void getByIdArtistas() throws Exception {
        var entidade = new Artista(1, "Igor Guimaraes", "Iginho bariloche ", "@iginho_bariloche ", "fotoHome.jpa", "fotoDetalhe.jpg", true);

        when(repository.findById(1)).thenReturn(Optional.of(entidade));

        var resultado = artistaService.getById(1);

        Assertions.assertNotNull(resultado);
        Assertions.assertNotNull(resultado.id());
        Assertions.assertNotNull(resultado.nome());
        Assertions.assertNotNull(resultado.detalhe());
        Assertions.assertNotNull(resultado.instagram());
        Assertions.assertNotNull(resultado.urlFotoHome());
        Assertions.assertNotNull(resultado.urlFotoDetalhe());
        Assertions.assertNotNull(resultado.habilitao());
        Assertions.assertEquals("Igor Guimaraes", resultado.nome());
        Assertions.assertEquals("Igor bariloche", resultado.detalhe());
        Assertions.assertEquals("@iginho_bariloche", resultado.instagram());


    }
   */

   /* @Test
    public void getAllArtistas() throws Exception {
        List<Artista> lista = new ArrayList<>();

        for (var i = 1; i <= 15; i++){
            lista.add(new Artista(i, "Artista " + i , "Comediante " + i , "@instaArtista " + i , "fotoHome" + i +".jpa", "fotoDetalhe"+ i +".jpg", true));
        }

        when(artistaService.getAll()).thenReturn(lista);

        var artistas = artistaService.getAll();

        Assertions.assertNotNull(artistas);
        Assertions.assertEquals(15, artistas.size());

        var artista1 = artistas.get(1);

        Assertions.assertNotNull(artista1);
        Assertions.assertNotNull(artista1.id());
        Assertions.assertNotNull(artista1.nome());
        Assertions.assertNotNull(artista1.detalhe());
        Assertions.assertNotNull(artista1.instagram());
        Assertions.assertNotNull(artista1.urlFotoHome());
        Assertions.assertNotNull(artista1.urlFotoDetalhe());
        Assertions.assertNotNull(artista1.habilitao());

        Assertions.assertEquals("1", artista1.id());
        Assertions.assertEquals("Artista 1", artista1.nome());
    }
    */
}