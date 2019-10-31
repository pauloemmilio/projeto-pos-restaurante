package com.paulo.restaurantes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulo.restaurantes.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
