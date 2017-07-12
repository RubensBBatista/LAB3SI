package br.com.suaserie.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.suaserie.ws.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie,Long>{

}
