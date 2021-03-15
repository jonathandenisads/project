package com.marttech.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marttech.project.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	Produto findByCodigo(long codigo);
	
}
