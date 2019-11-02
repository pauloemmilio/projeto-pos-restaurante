package com.paulo.restaurantes.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.restaurantes.models.Pedido;
import com.paulo.restaurantes.models.Produto;
import com.paulo.restaurantes.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired private ProdutoRepository produtoRepository;
	@Autowired private PedidoService pedidoService;
	
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}

	public Produto salvar(@Valid Produto produto) {
		return produtoRepository.save(produto);
	}

	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	public void deletar(Long id) {
		produtoRepository.deleteById(id);
	}

	public List<Produto> buscarPorRestauranteId(Long restauranteId) {
		return produtoRepository.findByRestauranteId(restauranteId);
	}

	public void deletar(Produto produto) {
		Long produtoId = produto.getId();
		List<Pedido> pedidos = pedidoService.buscarPorProdutoId(produtoId);
		for(Pedido pedido: pedidos) {
			pedidoService.deletar(pedido.getId());
		}
		this.deletar(produtoId);
	}
}
