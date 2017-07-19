package br.com.suaserie.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.suaserie.ws.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{

	//@Query(value="Select u from Cliente u where u.login=:plogin ")
	//public Cliente buscarPorLogin(@Param("plogin") String login);
	
	
	
}
