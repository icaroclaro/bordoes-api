package com.bordoes.infraestrutura.repositories;

import com.bordoes.dominio.entidade.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Integer> {

}
