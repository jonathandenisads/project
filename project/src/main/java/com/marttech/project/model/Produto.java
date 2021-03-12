package com.marttech.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID= 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	private String nome;
	private String descricao;
	private long quantidade;
	private double valor;

	@OneToMany(mappedBy = "produto")
	private List<Carrinho> listaCarrinho;
	
	@Deprecated
	public Produto() {
		
	}
	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public long getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}



	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public long getCodigo() {
		return codigo;
	}


	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}


	public List<Carrinho> getListaCarrinho() {
		return listaCarrinho;
	}


	public void setListaCarrinho(List<Carrinho> listaCarrinho) {
		this.listaCarrinho = listaCarrinho;
	}


	
	
	
	
}

