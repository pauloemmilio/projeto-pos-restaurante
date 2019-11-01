package com.paulo.restaurantes.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.restaurantes.models.Produto;
import com.paulo.restaurantes.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired private ProdutoRepository produtoRepository;
	
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
}
