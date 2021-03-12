package com.marttech.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.marttech.project.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	Cliente findByCodigo(Long codigo);
	
	@Query("select c from Cliente c where c.nome like %?1%")
	List<Cliente> findByNome(String nome);
}
