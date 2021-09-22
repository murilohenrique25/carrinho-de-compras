package com.murilo.carrinhodecompras.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.murilo.carrinhodecompras.models.Produto;
import com.murilo.carrinhodecompras.services.ProdutoService;

import lombok.Getter;
import lombok.Setter;

@Controller
@Scope
public class ProdutoController {
	 	 
	 @Autowired
	 ProdutoService produtoService;
	 
	 @Getter
	 @Setter
	 @Autowired
	 private Produto produto;
	 
	 @Autowired
	 @Getter
	 @Setter
	 private List<Produto> produtos;
	 
	 public void salvar() {
		 produtoService.save(produto);
	 }
	 
	 public List<Produto> listarTodos() {
		return produtos = produtoService.listarTodos();
	 }
	 public void alterar(Produto c) {
		 produto = c;
	 }
	 
	 public void excluir(Produto p) {
		 produtoService.deletar(p);
	 }
	 public void novo() {
		 produto = new Produto();
	 }
	
	 public void editar(Produto p) {
		 produto = p;
	 }
}
