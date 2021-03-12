package com.marttech.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.marttech.project.model.Produto;
import com.marttech.project.repository.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository pr;
	

	@RequestMapping("listarProdutos")
	public String form() {
		return "produto/formProduto";
	}

	@RequestMapping("/produtos")
	public ModelAndView listarProduto() {
		ModelAndView mv = new ModelAndView("produto/formProduto");
		Iterable<Produto> listaProduto = pr.findAll();
		mv.addObject("produtos", listaProduto);
		return mv;
	}
	
	
	
}
