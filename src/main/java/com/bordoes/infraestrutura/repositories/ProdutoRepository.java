package com.bordoes.infraestrutura.repositories;

import com.bordoes.dominio.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
