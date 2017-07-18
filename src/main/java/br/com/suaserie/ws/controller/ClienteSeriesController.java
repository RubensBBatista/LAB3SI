package br.com.suaserie.ws.controller;


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
import br.com.suaserie.ws.model.Serie;
import br.com.suaserie.ws.service.ClienteService;
import br.com.suaserie.ws.service.SerieService;

@RestController
@RequestMapping("/cliente/")
public class ClienteSeriesController {
	
	@Autowired
	ClienteService clienteService;
	@Autowired
	SerieService serieService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "perfil/{id}", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> cadastrarSerieNoPerfil(@RequestBody Serie serie, @PathVariable("id") Long id) {
		
		Serie cadastrada = serieService.cadastrarSerie(serie);
		Cliente clienteEncontrado = clienteService.getClientesPorId(id);
		clienteEncontrado.addNoPerfil(cadastrada);
		clienteService.cadastrar(clienteEncontrado);
		return new ResponseEntity<>(cadastrada, HttpStatus.CREATED);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "watchlist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> cadastrarSerieNaWatchList(@RequestBody Serie serie, @PathVariable("id") Long id) {
		
		Serie cadastrada = serieService.cadastrarSerie(serie);
		Cliente clienteEncontrado = clienteService.getClientesPorId(id);
		clienteEncontrado.addWatchList(cadastrada);
		clienteService.cadastrar(clienteEncontrado);
		return new ResponseEntity<>(cadastrada, HttpStatus.CREATED);

	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "removerPerfil/{id}")
	public ResponseEntity<Serie> deletarDoPerfil(@RequestBody Serie serie, @PathVariable("id") Long id) {
		
		Cliente clienteEncontrado = clienteService.getClientesPorId(id);
		clienteEncontrado.removerSeriePerfil(serie);
		clienteService.cadastrar(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "removerWatchList/{id}")
	public ResponseEntity<Serie> deletarDaWatchList(@RequestBody Serie serie, @PathVariable("id") Long id) {
		
		Cliente clienteEncontrado = clienteService.getClientesPorId(id);
		clienteEncontrado.removerSerieWatchList(serie);
		clienteService.cadastrar(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	

}
