package com.murilo.carrinhodecompras.services;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murilo.carrinhodecompras.models.Produto;
import com.murilo.carrinhodecompras.repositories.ProdutoRepository;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void save(Produto p) {
		try {
			produtoRepository.save(p);
			listarTodos();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	@PostConstruct
	public void init() {
		listarTodos();
	}
	
	public List<Produto> listarTodos(){
		List<Produto> produtos = new ArrayList<>();
		try {
			produtos = produtoRepository.findAll();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return produtos;
	}
	
	public void deletar(Produto p) {
		try {
			produtoRepository.delete(p);
			listarTodos();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
