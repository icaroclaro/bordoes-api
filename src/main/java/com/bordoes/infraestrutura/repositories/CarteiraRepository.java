package com.bordoes.infraestrutura.repositories;

import com.bordoes.dominio.entidade.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {
}
