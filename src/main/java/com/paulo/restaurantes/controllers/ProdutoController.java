package com.paulo.restaurantes.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.paulo.restaurantes.models.Produto;
import com.paulo.restaurantes.models.Restaurante;
import com.paulo.restaurantes.services.ProdutoService;
import com.paulo.restaurantes.services.RestauranteService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private RestauranteService restauranteService;

	@GetMapping
	public List<Produto> listar() {
		return produtoService.listar();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto cadastrar(@Valid @RequestBody Produto produto) {
		if(produto.getRestaurante().getId() == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurante inválido");
		
		Optional<Restaurante> restauranteOpt = restauranteService.buscarPorId(produto.getRestaurante().getId());
		if (!restauranteOpt.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurante não encontrado");
		
		return produtoService.salvar(produto);
	}
}
