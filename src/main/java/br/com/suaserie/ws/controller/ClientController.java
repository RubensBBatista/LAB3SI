package br.com.suaserie.ws.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.suaserie.ws.model.Cliente;
import br.com.suaserie.ws.service.ClienteService;

@RestController
public class ClientController {
	
	@Autowired
	ClienteService clienteService;

	// End points

	@RequestMapping(method = RequestMethod.POST, value ="/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		
		Cliente clienteCadastrado = clienteService.cadastrar(cliente);
		return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> getAllCliente() {

		Collection<Cliente> allClientes = clienteService.getClientes();
		return new ResponseEntity<>(allClientes, HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.DELETE, value ="/clientes/{id}")
	public ResponseEntity<Cliente> removerCliente(@PathVariable Integer id) {
		
		Cliente clienteEncontrado = clienteService.getPorId(id);
		if(clienteEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clienteService.remover(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
		

	}

}