package com.bordoes.infraestrutura.persistencia;

import com.bordoes.dominio.entidade.Cliente;
import com.bordoes.dominio.interfaces.InterfaceClienteService;
import com.bordoes.infraestrutura.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ClientePersistencia implements InterfaceClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getById(int id) {
        return clienteRepository.getReferenceById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(int id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        clienteRepository.delete(cliente);
    }
}
