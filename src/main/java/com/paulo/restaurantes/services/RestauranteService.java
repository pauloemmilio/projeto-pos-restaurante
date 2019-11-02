package com.paulo.restaurantes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.restaurantes.models.Produto;
import com.paulo.restaurantes.models.Restaurante;
import com.paulo.restaurantes.repositories.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired private RestauranteRepository restauranteRepository;
	@Autowired private ProdutoService produtoService;
	
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}

	public Restaurante salvar(Restaurante restaurante) {
		return restauranteRepository.save(restaurante);
	}

	public Optional<Restaurante> buscarPorId(Long id) {
		return restauranteRepository.findById(id);
	}

	public void deletar(Long id) {
		restauranteRepository.deleteById(id);
	}
	
	public void deletar(Restaurante restaurante) {
		Long restauranteId = restaurante.getId();
		List<Produto> produtos = produtoService.buscarPorRestauranteId(restauranteId);
		for(Produto produto: produtos) {
			produtoService.deletar(produto);
		}
		this.deletar(restauranteId);
	}
}
