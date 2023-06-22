package com.bordoes.dominio.interfaces;

import com.bordoes.dominio.entidade.Carteira;

import java.util.List;

public interface InterfaceCarteiraService {
    public List<Carteira> getAll();
    public Carteira getById(int id);
    public Carteira save(Carteira carteira);
    public Carteira update(Carteira carteira);
    public void delete(int id);
}
