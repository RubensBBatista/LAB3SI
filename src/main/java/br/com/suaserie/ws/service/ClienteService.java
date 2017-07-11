package br.com.suaserie.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.suaserie.ws.model.Cliente;
import br.com.suaserie.ws.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	public Cliente cadastrar(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}

	public Collection<Cliente> getClientes() {
		
		return clienteRepository.findAll();
	}
	
	public boolean remover(Long id) {
		
		if(clienteRepository.exists(id)) {
			clienteRepository.delete(id);	
			return true;
		}
		return false;
		
		
	}

}
