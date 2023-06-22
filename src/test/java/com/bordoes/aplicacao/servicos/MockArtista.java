package com.bordoes.aplicacao.servicos;

import com.bordoes.aplicacao.dto.ArtistaDto;

import java.util.ArrayList;
import java.util.List;

public class MockArtista {
//    public List<ArtistaDto> mockListaArtistaDto(int id){
//       List<ArtistaDto> artistas = ArrayList<>();
//
//               new ArtistaDto(id, "Igor Guimarães",
//               "Comediante Paulista...",
//               "@iginho",
//               "urlFotoHome.jpg",
//               "urlFotoDetalhe.jpg",
//               true);
//
//    }

    public ArtistaDto mockArtistaDto(int id){
        return new ArtistaDto(
                id,
                "Igor Guimarães",
               "Comediante Paulista...",
               "@iginho",
               "urlFotoHome.jpg",
               "urlFotoDetalhe.jpg",
               true);
    }
}
