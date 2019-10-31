package com.paulo.restaurantes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.restaurantes.models.Cliente;
import com.paulo.restaurantes.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired private ClienteRepository clienteRepository;
	
	public List<Cliente> listar () {
		return clienteRepository.findAll();
	}

	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
}
