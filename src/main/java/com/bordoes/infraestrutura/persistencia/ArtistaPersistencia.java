package com.bordoes.infraestrutura.persistencia;

import com.bordoes.aplicacao.exceptions.MyNotFoundException;
import com.bordoes.dominio.entidade.Artista;
import com.bordoes.dominio.interfaces.InterfaceArtistaService;
import com.bordoes.infraestrutura.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ArtistaPersistencia implements InterfaceArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;


    @Override
    public List<Artista> getAll() {
        return   artistaRepository.findAll();
    }

    @Override
   // public Artista getById(int id) {return artistaRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Nenhum artista encontrado para este ID!") );}
    public Artista getById(int id) {
        try {
            return artistaRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            return null;
        }
    }

    @Override
    public Artista save(Artista artista) {
        return artistaRepository.save(artista);
    }

    @Override
    public Artista update(Artista artista) {
        return artistaRepository.save(artista);
    }

    @Override
    public void delete(int id) {
        Artista artista = artistaRepository.getReferenceById(id);
        artistaRepository.delete(artista);
    }
}
