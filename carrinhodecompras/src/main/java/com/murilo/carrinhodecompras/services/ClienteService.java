package com.murilo.carrinhodecompras.services;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murilo.carrinhodecompras.models.Cliente;
import com.murilo.carrinhodecompras.repositories.ClienteRepository;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public void save(Cliente c) {
		try {
			clienteRepository.save(c);
			listarTodos();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	@PostConstruct
	public List<Cliente> listarTodos(){
		List<Cliente> clientes = new ArrayList<>();
		try {
			clientes = clienteRepository.findAll();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return clientes;
	}
	
	public void deletar(Cliente c) {
		try {
			clienteRepository.delete(c);
			listarTodos();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public Cliente buscarPorId(Long id) {
		Cliente c = new Cliente();
		try {
			c = clienteRepository.porId(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
}
