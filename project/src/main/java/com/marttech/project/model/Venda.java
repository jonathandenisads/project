package com.marttech.project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	private double valorTotal=0;
	
	@OneToMany(mappedBy = "venda")
	private List<Carrinho> itensCarrinho;

	@ManyToOne 
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	
	public Venda() {
		
	}
	
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Carrinho> getItensCarrinho() {
		return itensCarrinho;
	}

	public void setItensCarrinho(List<Carrinho> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	
}
