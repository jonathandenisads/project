package com.marttech.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.marttech.project.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
	Produto findByCodigo(long codigo);
	
}
