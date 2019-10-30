package com.paulo.restaurantes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.restaurantes.models.Restaurante;
import com.paulo.restaurantes.repositories.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}

	public Restaurante cadastrar(Restaurante restaurante) {
		return restauranteRepository.save(restaurante);
	}
}
