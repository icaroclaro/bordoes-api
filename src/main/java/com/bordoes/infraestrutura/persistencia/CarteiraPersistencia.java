package com.bordoes.infraestrutura.persistencia;

import com.bordoes.dominio.entidade.Carteira;
import com.bordoes.dominio.interfaces.InterfaceCarteiraService;
import com.bordoes.infraestrutura.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CarteiraPersistencia implements InterfaceCarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;
    @Override
    public List<Carteira> getAll() {
        return carteiraRepository.findAll();
    }

    @Override
    public Carteira getById(int id) {
        return carteiraRepository.getReferenceById(id);
    }

    @Override
    public Carteira save(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    @Override
    public Carteira update(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    @Override
    public void delete(int id) {
        Carteira carteira = carteiraRepository.getReferenceById(id);
        carteiraRepository.delete(carteira);
    }
}
