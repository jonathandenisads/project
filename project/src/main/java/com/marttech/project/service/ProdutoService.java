package com.marttech.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marttech.project.model.Produto;
import com.marttech.project.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	
	public List<Produto> buscarTodososProdutos(){
		return produtoRepository.findAll();
		
			
	}
	
	
	
	
}
