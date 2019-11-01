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

import com.paulo.restaurantes.models.Cliente;
import com.paulo.restaurantes.models.Pedido;
import com.paulo.restaurantes.models.Produto;
import com.paulo.restaurantes.services.ClienteService;
import com.paulo.restaurantes.services.PedidoService;
import com.paulo.restaurantes.services.ProdutoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired private PedidoService pedidoService;
	@Autowired private ClienteService clienteService;
	@Autowired private ProdutoService produtoService;
	
	private static final String ERRO_ID_INVALIDO = "Id inválido";
	private static final String ERRO_CLIENTE_INVALIDO = "Cliente inválido";
	private static final String ERRO_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
	private static final String ERRO_PRODUTO_INVALIDO = "Produto inválido";
	private static final String ERRO_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
	
	@GetMapping
	public List<Pedido> listar(){
		return pedidoService.listar();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido cadastrar (@Valid @RequestBody Pedido pedido) {
		if(pedido.getCliente().getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_CLIENTE_INVALIDO);
		}
		this.validarPedido(pedido);
		return pedidoService.salvar(pedido);
	}
	
	@PutMapping("/{id}")
	public Pedido atualizar(@Valid @RequestBody Pedido pedido, @PathVariable Long id) {
		if(pedido.getId() != id) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_ID_INVALIDO);
		}
		this.validarPedido(pedido);
		return pedidoService.salvar(pedido);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		Optional<Pedido> pedidoOpt = pedidoService.buscarPorId(id);
		if(!pedidoOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_PRODUTO_NAO_ENCONTRADO);
		}
		pedidoService.deletar(id);
	}
	
	private void validarPedido(Pedido pedido) {
		if(pedido.getCliente().getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_CLIENTE_INVALIDO);
		}
		if(pedido.getProduto().getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_PRODUTO_INVALIDO);
		}
		Optional<Cliente> clienteOpt = clienteService.buscarPorId(pedido.getCliente().getId());
		if(!clienteOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_CLIENTE_NAO_ENCONTRADO);
		}
		Optional<Produto> produtoOpt = produtoService.buscarPorId(pedido.getProduto().getId());
		if(!produtoOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_PRODUTO_NAO_ENCONTRADO);
		}
	}
}
