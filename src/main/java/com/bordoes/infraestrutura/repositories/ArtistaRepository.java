package com.bordoes.infraestrutura.repositories;

import com.bordoes.dominio.entidade.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

}
