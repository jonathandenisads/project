package com.marttech.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.marttech.project.model.Carrinho;
import com.marttech.project.model.Cliente;
import com.marttech.project.model.Produto;
import com.marttech.project.model.Venda;
import com.marttech.project.repository.CarrinhoRepository;
import com.marttech.project.repository.ClienteRepository;
import com.marttech.project.repository.ProdutoRepository;
import com.marttech.project.repository.VendaRepository;

@Controller
public class CarrinhoController {

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private CarrinhoRepository cr;

	@Autowired
	private ClienteRepository cliR;

	@Autowired
	private VendaRepository vendaR;

	Venda venda = new Venda();
	List<Carrinho> listaCarrinho = new ArrayList<>();
	Cliente cli = new Cliente();
	List<Carrinho> carrinhoAuxiliar = new ArrayList<>();
	Carrinho carrinhoTotal;

	public void calcularTotal() {
		for (Carrinho c : listaCarrinho) {
			venda.setValorTotal(0);
			venda.setValorTotal(venda.getValorTotal() + c.getValorTotalEmItem());// calculando valor total de itens por
																					// linha
		}
	}

	@RequestMapping("/adicionarCarrinho")
	public ModelAndView formCarrinhoVenda() {
		ModelAndView mv = new ModelAndView("carrinho/formCarrinho");
		mv.addObject("vendas", venda);
		mv.addObject("produtos", listaCarrinho);
		return mv;
	}

	@RequestMapping("/alterarQuantidade/{codigo}/{acao}")
	public String alterarQuantidade(@PathVariable long codigo, @PathVariable Integer acao) {
		for (Carrinho c : listaCarrinho) {
			if (c.getProduto().getCodigo() == codigo) {
				if (acao == 1) {
					c.setQuantidade(c.getQuantidade() + 1);
					venda.setValorTotal(venda.getValorTotal() + c.getValorTotalEmItem());

				} else {
					c.setQuantidade(c.getQuantidade() - 1);

					venda.setValorTotal(venda.getValorTotal() - c.getValorTotalEmItem());
				}
				break;
			}
		}
		return "redirect:/adicionarCarrinho";
	}

	@RequestMapping("/removerProduto/{codigo}")
	public String removerProduto(@PathVariable long codigo) {
		for (Carrinho c : listaCarrinho) {
			if (c.getProduto().getCodigo() == codigo) {
				c.setValorTotalEmItem((c.getValorUnitario() * c.getQuantidade()));

				venda.setValorTotal(venda.getValorTotal() - c.getValorTotalEmItem());
				listaCarrinho.remove(c);
				break;
			}
		}
		return "redirect:/adicionarCarrinho ";
	}

	@RequestMapping("/adicionarCarrinho/{codigo}")
	public String listarProdutosCarrinho(@PathVariable long codigo, Carrinho car) {

		Produto prod = pr.findByCodigo(codigo);
		Carrinho carrinho = new Carrinho();

		int controle = 0;
		for (Carrinho c : listaCarrinho) {
			if (prod.getCodigo() == c.getProduto().getCodigo()) {
				controle = 1;
				c.setQuantidade(c.getQuantidade() + 1);
				venda.setValorTotal(venda.getValorTotal() + c.getValorTotalEmItem());
				carrinho.setValorTotalEmItem(
						carrinho.getValorTotalEmItem() + (carrinho.getValorUnitario() * carrinho.getQuantidade()));

				break;

			}
		}
		if (controle == 0) {
			carrinho.setProduto(prod);
			carrinho.setQuantidade(carrinho.getQuantidade() + 1);
			carrinho.setValorUnitario(prod.getValor());

			carrinho.setValorTotalEmItem(
					carrinho.getValorTotalEmItem() + (carrinho.getValorUnitario() * carrinho.getQuantidade()));
			venda.setValorTotal(venda.getValorTotal() + carrinho.getValorTotalEmItem());
			listaCarrinho.add(carrinho);
		}

		return "redirect:/adicionarCarrinho";
	}

	// FINALIZAR COMPRA

	@RequestMapping("/finalizarCompra")
	public ModelAndView finalizarCompra() {
		ModelAndView mv = new ModelAndView("finalizarCompra/finalizar");
		mv.addObject("vendas", venda);
		mv.addObject("produtos", listaCarrinho);
		return mv;
	}

	@RequestMapping("/solicitarDadosCliente")
	public ModelAndView solicitarDadosPosCompra() {
		ModelAndView mv = new ModelAndView("cliente/formCliente");
		return mv;
	}

	@RequestMapping("/exibirPedidos")
	public ModelAndView exibirPedidosRealizados() {
		ModelAndView mv = new ModelAndView("carrinho/MensagemCompraConcluida");

		Iterable<Cliente> clientes = new ArrayList<>();
		clientes = cliR.findAll();

		mv.addObject("clientes", clientes);
		return mv;
	}

	@RequestMapping(value = "/solicitarDadosCliente", method = RequestMethod.POST)
	public String salvarCliente(Cliente cliente) {

		cliR.save(cliente);
		venda.setCliente(cliente);

		vendaR.save(venda);

		for (Carrinho car : listaCarrinho) {
			car.setValorTotalEmItem(car.getValorUnitario() * car.getQuantidade());
			car.setVenda(venda);
			cr.save(car);

		}
		return "redirect:/exibirPedidos ";
	}
	
	@RequestMapping(value="/exibirPedidos", method=RequestMethod.POST)
	public ModelAndView buscarCliente(@RequestParam("pesquisarNome") String nome) {
		ModelAndView mv= new ModelAndView("carrinho/MensagemCompraConcluida");
		mv.addObject("clientes",cliR.findByNome(nome));
		return mv;
	}
	
	@RequestMapping("/exibirPedidoDetalhado")
	public ModelAndView exibirDetalhesPedido() {
		ModelAndView mv = new ModelAndView("pedidos/detalhesPedido");
		mv.addObject("clientes", cli);
		mv.addObject("dadosCarrinho", carrinhoAuxiliar);
		mv.addObject("dadosCarrinhoTotal", carrinhoTotal);
		return mv;
	}

	@RequestMapping("/exibirPedidoDetalhado/{id}")
	public String exibirDetalhesPedidos(@PathVariable long id) {
		cli=cliR.findByCodigo(id);
		carrinhoTotal=new Carrinho();
		Iterable<Carrinho> carrinhos = cr.findAll();
		List<Carrinho> listAuxiliar= new ArrayList<>();
		
					for (Carrinho c : carrinhos) {
						if(c.getVenda().getCliente().getCodigo()== id) {
							listAuxiliar.add(c);
							carrinhoTotal=c;
						}
						
					}
					carrinhoAuxiliar=listAuxiliar;
		
		return "redirect:/exibirPedidoDetalhado";
	}

}
