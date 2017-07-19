package br.com.suaserie.ws.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.suaserie.ws.model.Serie;
import br.com.suaserie.ws.repository.SerieRepository;

@Service
public class SerieService {
	
	@Autowired
	SerieRepository serieRepository;
	
	public Serie cadastrarSerie(Serie serie) {
		
		return serieRepository.save(serie);
		
	}
	
	public Serie getSerie(long id) {
		
		if(serieRepository.exists(id)) {
			return serieRepository.findOne(id);
		}
		return new Serie();
		
	}
	
	public boolean removerSerie(long id) {
		
		if(serieRepository.exists(id)) {
			serieRepository.delete(id);
			return true;
		}
		return false;
	}

}
