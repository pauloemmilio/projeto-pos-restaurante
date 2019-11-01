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

import com.paulo.restaurantes.models.Produto;
import com.paulo.restaurantes.models.Restaurante;
import com.paulo.restaurantes.services.ProdutoService;
import com.paulo.restaurantes.services.RestauranteService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired private ProdutoService produtoService;
	@Autowired private RestauranteService restauranteService;

	private static final String ERRO_ID_INVALIDO = "Id inválido";
	private static final String ERRO_RESTAURANTE_INVALIDO = "Restaurante inválido";
	private static final String ERRO_RESTAURANTE_NAO_ENCONTRADO = "Restaurante não encontrado";
	private static final String ERRO_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
	
	@GetMapping
	public List<Produto> listar() {
		return produtoService.listar();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto cadastrar(@Valid @RequestBody Produto produto) {
		if(produto.getRestaurante().getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_RESTAURANTE_INVALIDO);			
		}
		Optional<Restaurante> restauranteOpt = restauranteService.buscarPorId(produto.getRestaurante().getId());
		if (!restauranteOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_RESTAURANTE_NAO_ENCONTRADO);			
		}
		return produtoService.salvar(produto);
	}
	
	@PutMapping("/{id}")
	public Produto atualizar(@Valid @RequestBody Produto produto, @PathVariable Long id) {
		if(produto.getId() != id) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_ID_INVALIDO);
		}
		Optional<Produto> produtoOpt = produtoService.buscarPorId(id);
		if(!produtoOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_PRODUTO_NAO_ENCONTRADO);
		}
		if(produto.getRestaurante().getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_RESTAURANTE_INVALIDO);			
		}
		return produtoService.salvar(produto);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		Optional<Produto> produtoOpt = produtoService.buscarPorId(id);
		if(!produtoOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_PRODUTO_NAO_ENCONTRADO);
		}
		produtoService.deletar(id);
	}
}
