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
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> cadastrarSerieNoPerfil(@RequestBody Serie serie, @PathVariable("id") Long id) {
		
		Serie cadastrada = serieService.cadastrarSerie(serie);
		Cliente clienteEncontrado = clienteService.getClientesPorId(id);
		clienteEncontrado.addNoPerfil(cadastrada);
		clienteService.cadastrar(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "watchlist/{id}", 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> cadastrarSerieNaWatchlist(@RequestBody Serie serie, @PathVariable("id") Long id) {
		
		Serie cadastrada = serieService.cadastrarSerie(serie);
		Cliente clienteEncontrado = clienteService.getClientesPorId(id);
		clienteEncontrado.addWatchList(cadastrada);
		clienteService.cadastrar(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "removerPerfil/{id}/{id2}")
	public ResponseEntity<String> deletarSeriePerfil(@PathVariable Long id, @PathVariable String id2) {
		
		Cliente encontrado = clienteService.getClientesPorId(id);
		encontrado.removerSeriePerfilID(id2);
		clienteService.cadastrar(encontrado);
		return new ResponseEntity<>("Serie removida com sucesso", HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "removerWatchList/{id}/{id2}")
	public ResponseEntity<String> deletarSerieWatchlist(@PathVariable Long id, @PathVariable String id2) {
		
		Cliente encontrado = clienteService.getClientesPorId(id);
		encontrado.removerSerieWatchlistID(id2);
		clienteService.cadastrar(encontrado);
		return new ResponseEntity<>("Serie removida com sucesso", HttpStatus.OK);
		
	}
	

}
