package com.bordoes.dominio.interfaces;

import com.bordoes.dominio.entidade.Produto;

import java.util.List;

public interface InterfaceProdutoService {
    public List<Produto> getAll();
    public Produto getById(int id);
    public Produto save(Produto produto);
    public Produto update(Produto produto);
    public void delete(int id);
}
