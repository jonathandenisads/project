package com.marttech.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marttech.project.dto.ProdutoSummaryModel;
import com.marttech.project.model.Produto;
import com.marttech.project.service.ProdutoService;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@RequestMapping("listarProdutos")
	public String form() {
		return "produto/formProduto";
	}

	@RequestMapping("/produtos")
	public ModelAndView listarProduto() {
		ModelAndView mv = new ModelAndView("produto/formProduto");
		
		List<ProdutoSummaryModel> listaProduto = produtoService.buscarTodososProdutos()
				.stream().map(this:: toProdutoSummaryModel).collect(Collectors.toList());
		
		mv.addObject("produtos", listaProduto);
		return mv;
	}
	
	private ProdutoSummaryModel toProdutoSummaryModel(Produto produto) {
		return modelMapper.map(produto, ProdutoSummaryModel.class);
	}
	
	
	
}
