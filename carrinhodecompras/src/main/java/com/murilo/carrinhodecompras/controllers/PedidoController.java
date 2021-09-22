package com.murilo.carrinhodecompras.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.murilo.carrinhodecompras.models.Cliente;
import com.murilo.carrinhodecompras.models.ItemPedido;
import com.murilo.carrinhodecompras.models.Pedido;
import com.murilo.carrinhodecompras.models.Produto;
import com.murilo.carrinhodecompras.models.Relatorio;
import com.murilo.carrinhodecompras.repositories.ClienteRepository;
import com.murilo.carrinhodecompras.repositories.ProdutoRepository;
import com.murilo.carrinhodecompras.services.PedidoService;

import lombok.Getter;
import lombok.Setter;

@Controller
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Getter
	@Setter
	@Autowired
	private Pedido pedido;

	@Getter
	@Setter
	private List<Produto> produtos;
	
	@Getter
	@Setter
	private List<ItemPedido> itensPedidos;

	@Getter
	@Setter
	private List<Cliente> clientes;
	
	@Getter
	@Setter
	private List<Relatorio> vendasTotais;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	@PostConstruct
	public void novo() {
		try {
			pedido = new Pedido();
			pedido.setPrecoTotal(0.00);
			pedido.setDataVenda(new Date());

			produtos = produtoRepository.findAll();
			vendasTotais = pedidoService.listarTodasVendas();
			itensPedidos = new ArrayList<ItemPedido>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionar(Produto p) {
		int achou = -1;

		for (int posicao = 0; posicao < itensPedidos.size(); posicao++) {
			if (itensPedidos.get(posicao).getProduto().equals(p)) {
				achou = posicao;
			}
		}
		if (achou < 0) {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setPrecoParcial(p.getValor());
			itemPedido.setProduto(p);
			itemPedido.setQuantidade(1);

			itensPedidos.add(itemPedido);
		} else {
			ItemPedido itemPedido = itensPedidos.get(achou);
			
				itemPedido.setQuantidade(itemPedido.getQuantidade() + 1);
				double valorParcial = p.getValor() * itemPedido.getQuantidade();
				itemPedido.setPrecoParcial(valorParcial);
			
		}
		calcular();
	}

	public void deletar(ItemPedido item) {
		int achou = -1;

		for (int posicao = 0; posicao < itensPedidos.size(); posicao++) {
			if (itensPedidos.get(posicao).getProduto().equals(item.getProduto())) {
				achou = posicao;
			}
		}

		if (achou >= 0) {
			itensPedidos.remove(achou);
		}
		calcular();
	}

	public void calcular() {
		try {
			pedido.setPrecoTotal(0.00);

			for (int posicao = 0; posicao < itensPedidos.size(); posicao++) {
				ItemPedido itemPedido = itensPedidos.get(posicao);
				pedido.setPrecoTotal(pedido.getPrecoTotal() + itemPedido.getPrecoParcial());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void finalizar() {
		try {
			
			pedido.setDataVenda(new Date());
			clientes = clienteRepository.findAll();
		} catch (RuntimeException erro) {
			// Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (pedido.getPrecoTotal() <= 0) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Falta de produtos", "Informe pelo menos um item para a venda");
				return;
			}
			if (pedido.getCliente().getNome().isEmpty()) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Adicione um cliente",
						"Selecione um cliente para finalizar a venda");
				return;
			}

			pedidoService.save(pedido, itensPedidos);
			
			addMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Venda realizada com sucesso.");
			novo();
		} catch (Exception e) {
			// Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
			e.printStackTrace();
		}
	}
}
