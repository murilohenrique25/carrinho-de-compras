package com.murilo.carrinhodecompras.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murilo.carrinhodecompras.models.ItemPedido;
import com.murilo.carrinhodecompras.models.Pedido;
import com.murilo.carrinhodecompras.models.Relatorio;
import com.murilo.carrinhodecompras.repositories.ItemPedidoRepository;
import com.murilo.carrinhodecompras.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void save(Pedido p, List<ItemPedido> itensPedido) {
		try {
			pedidoRepository.save(p);

			for (int posicao = 0; posicao < itensPedido.size(); posicao++) {
				ItemPedido itemVenda = itensPedido.get(posicao);
				itemVenda.setPedido(p);

				itemPedidoRepository.save(itemVenda);
			}
			init();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		listarTodos();
		listarTodasVendas();
	}

	public List<Pedido> listarTodos() {
		List<Pedido> pedidos = new ArrayList<>();
		
		try {
			pedidos = pedidoRepository.findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}
	
	public List<Relatorio> listarTodasVendas() {
		
		List<Relatorio> pedidosFinais = new ArrayList<>();

		try {
			
			List<?> li = pedidoRepository.totalPorClientes();
			for (int i = 0; i < li.size(); i++) {
				Relatorio rela = new Relatorio();

				String nome = String.valueOf(((Object[]) li.get(i))[0]);
				rela.setNome(nome);

				String precoString = String.valueOf(((Object[]) li.get(i))[1]);
				Double valor = Double.parseDouble(precoString);
				rela.setPrecoTotal(valor);

				pedidosFinais.add(rela);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidosFinais;
	}

	public void deletar(Pedido p) {
		try {
			pedidoRepository.delete(p);
			init();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
