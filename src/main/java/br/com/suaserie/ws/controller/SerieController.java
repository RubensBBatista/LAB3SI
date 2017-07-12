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
import br.com.suaserie.ws.service.SerieService;

@RestController
public class SerieController {
	
	@Autowired
	SerieService serieService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/series", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> cadastrarSerie(@RequestBody Serie serie) {

		Serie serieCadastrada = serieService.cadastrarSerie(serie);
		return new ResponseEntity<>(serieCadastrada, HttpStatus.CREATED);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/series/{id}")
	public ResponseEntity<Serie> buscarSerie(@PathVariable Long id) {
		
		Serie serieEncontrada = serieService.getSerie(id);
		if(serieEncontrada.getTitulo() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
		
		return new ResponseEntity<>(serieEncontrada, HttpStatus.OK);	
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/series/{id}")
	public ResponseEntity<Serie> deletarSerie(@PathVariable Long id) {
		
		if(serieService.removerSerie(id)) {
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		
	}

}
