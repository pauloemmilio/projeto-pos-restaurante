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
import com.paulo.restaurantes.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired private ClienteService clienteService;
	
	private static final String ERRO_ID_INVALIDO = "Id inválido";
	private static final String ERRO_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
	
	@GetMapping
	public List<Cliente> listar(){
		return this.clienteService.listar();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}
	
	@PutMapping("/{id}")
	public Cliente atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
		if(cliente.getId() != id) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_ID_INVALIDO);
		}
		Optional<Cliente> clienteOpt = clienteService.buscarPorId(id);
		if(!clienteOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_CLIENTE_NAO_ENCONTRADO);
		}
		return clienteService.salvar(cliente);
	}
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
		Optional<Cliente> clienteOpt = clienteService.buscarPorId(id);
		if(!clienteOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_CLIENTE_NAO_ENCONTRADO);
		}
		clienteService.deletar(clienteOpt.get());
    }
}
