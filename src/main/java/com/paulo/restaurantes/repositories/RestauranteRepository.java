package com.paulo.restaurantes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulo.restaurantes.models.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
