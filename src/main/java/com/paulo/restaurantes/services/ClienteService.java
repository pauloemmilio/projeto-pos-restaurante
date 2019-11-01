package com.paulo.restaurantes.services;

import java.util.List;
import java.util.Optional;

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

	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}
}
