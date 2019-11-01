package com.paulo.restaurantes.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
	private Produto produto;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	public Pedido () {}
	
	public Pedido(Produto produto, Cliente cliente) {
		this.produto = produto;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}