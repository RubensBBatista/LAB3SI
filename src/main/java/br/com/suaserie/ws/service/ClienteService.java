package br.com.suaserie.ws.service;

import java.util.Collection;
import java.util.List;

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

	public Cliente getClientesPorId(Long id) {
		
		Cliente clienteEncontrado = clienteRepository.findOne(id);
		if(clienteEncontrado != null) {
			return clienteEncontrado;
		}
		return new Cliente();
		
	}
	
	
	
	public Cliente autenticaUser(Cliente cliente) {
		List<Cliente> clientes = clienteRepository.findAll();
		for(Cliente c : clientes) {
			if(c.getLogin().equals(cliente.getLogin()) && c.getPassword().equals(cliente.getPassword()))
				return c;
		}
		return new Cliente();
	}

	

}
