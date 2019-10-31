package com.paulo.restaurantes.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paulo.restaurantes.models.Cliente;
import com.paulo.restaurantes.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar(){
		return this.clienteService.listar();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrar(@Valid @RequestBody Cliente cliente) {
		return clienteService.cadastrar(cliente);
	}
}
