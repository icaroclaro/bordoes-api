package com.bordoes.infraestrutura.repositories;

import com.bordoes.dominio.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
