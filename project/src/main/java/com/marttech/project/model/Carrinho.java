package com.marttech.project.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Carrinho {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	private long quantidade=0;
	private double subtotal;
	private double valorUnitario=0;
	private double valorTotalEmItem=0;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "venda_id")	
	private Venda venda;
	


	public Carrinho() {
		
	}


	public long getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}


	public double getValorUnitario() {
		return valorUnitario;
	}


	public void setValorTotal(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	public double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Venda getVenda() {
		return venda;
	}


	public void setVenda(Venda venda) {
		this.venda = venda;
	}


	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	public double getValorTotalEmItem() {
		return valorTotalEmItem;
	}


	public void setValorTotalEmItem(double valorTotalEmItem) {
		this.valorTotalEmItem = valorTotalEmItem;
	}
	

}
