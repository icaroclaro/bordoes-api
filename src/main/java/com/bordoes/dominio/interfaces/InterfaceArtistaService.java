package com.bordoes.dominio.interfaces;

import com.bordoes.dominio.entidade.Artista;

import java.util.List;

public interface InterfaceArtistaService {
    //public List<ArtistaDto> getAll();
    public List<Artista> getAll();
    public Artista getById(int id);
    public Artista save(Artista artista);
    public Artista update(Artista artista);
    public void delete(int id);
}
