package br.com.suaserie.ws.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.suaserie.ws.model.Cliente;

@RestController
public class LoginController {

	@RequestMapping(value="/autenticar", method= RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void autenticar(Cliente cliente) {
		
	}
	
}
