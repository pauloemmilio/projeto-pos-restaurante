package com.paulo.restaurantes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulo.restaurantes.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
