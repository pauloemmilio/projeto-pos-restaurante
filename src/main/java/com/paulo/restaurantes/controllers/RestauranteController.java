package com.paulo.restaurantes.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.paulo.restaurantes.models.Restaurante;
import com.paulo.restaurantes.services.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired private RestauranteService restauranteService;
	
	private static final String ERRO_ID_INVALIDO = "Id inválido";
	private static final String ERRO_RESTAURANTE_NAO_ENCONTRADO = "Restaurante não encontrado";
	
	@GetMapping
	public List<Restaurante> listar(){
		return this.restauranteService.listar();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante cadastrar(@Valid @RequestBody Restaurante restaurante) {
		return restauranteService.salvar(restaurante);
	}
	
	@PutMapping("/{id}")
	public Restaurante atualizar(@Valid @RequestBody Restaurante restaurante, @PathVariable Long id) {
		if(restaurante.getId() != id) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_ID_INVALIDO);
		}
		Optional<Restaurante> restauranteOpt = restauranteService.buscarPorId(id);
		if(!restauranteOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_RESTAURANTE_NAO_ENCONTRADO);
		}
		return restauranteService.salvar(restaurante);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Restaurante> restauranteOpt = restauranteService.buscarPorId(id);
		if(!restauranteOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_RESTAURANTE_NAO_ENCONTRADO);
		}
		restauranteService.deletar(restauranteOpt.get());
	}
}
