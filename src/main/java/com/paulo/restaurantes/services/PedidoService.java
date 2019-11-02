package com.paulo.restaurantes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.restaurantes.models.Pedido;
import com.paulo.restaurantes.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired private PedidoRepository pedidoRepository;
	
	public List<Pedido> listar(){
		return pedidoRepository.findAll();
	}

	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Optional<Pedido> buscarPorId(Long id) {
		return pedidoRepository.findById(id);
	}

	public void deletar(Long id) {
		pedidoRepository.deleteById(id);
	}

	public List<Pedido> buscarPorProdutoId(Long produtoId) {
		return pedidoRepository.findByProdutoId(produtoId);
	}

	public List<Pedido> buscarPorClienteId(Long clienteId) {
		return pedidoRepository.findByClienteId(clienteId);
	}
}
