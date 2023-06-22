package com.bordoes.dominio.interfaces;


import com.bordoes.dominio.entidade.Cliente;

import java.util.List;

public interface InterfaceClienteService {
    public List<Cliente> getAll();
    public Cliente getById(int id);
    public Cliente save(Cliente cliente);
    public Cliente update(Cliente cliente);
    public void delete(int id);
}
