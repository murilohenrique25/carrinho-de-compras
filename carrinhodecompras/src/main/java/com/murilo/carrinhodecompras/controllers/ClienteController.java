package com.murilo.carrinhodecompras.controllers;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.murilo.carrinhodecompras.models.Cliente;
import com.murilo.carrinhodecompras.services.ClienteService;

import lombok.Getter;
import lombok.Setter;

@Controller
@Scope(value = "view")
public class ClienteController {
	 	 
	 @Autowired
	 ClienteService clienteService;
	 
	 
	 @Getter
	 @Setter
	 @Autowired
	 private Cliente cliente;
	 
	 @Autowired
	 @Getter
	 @Setter
	 private List<Cliente> clientes;
	 
	 
	 public void salvar() {
		 clienteService.save(cliente);
		 listarTodos();
	 }
	 
	 @PostConstruct
	 public List<Cliente> listarTodos() {

		return clientes = clienteService.listarTodos();
	 }
	 public void alterar(Cliente c) {
		 cliente = c;
	 }
	 
	 public void excluir(Cliente c) {
		 clienteService.deletar(c);
		 listarTodos();
	 }
	 public void novo() {
		 cliente = new Cliente();
	 }
	 public void editar(Cliente c) {
		 cliente = c;
	 }
	 
	 public Cliente buscarPorId(Long id) {
		 return clienteService.buscarPorId(id);
	 }
}
