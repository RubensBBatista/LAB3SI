package br.com.suaserie.ws.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "user")
@Table(name = "tb_user")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String nome;
	@Column
	private String login;
	@Column
	private String password;
	@OneToMany
	private List<Serie> meuPerfil;
	@OneToMany
	private List<Serie> watchlist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addNoPerfil(Serie serie) {
		if(meuPerfil == null) {
			meuPerfil = new ArrayList<>();
		}
		int indice = meuPerfil.indexOf(serie);
		if(indice != -1) {
			meuPerfil.set(indice,serie );
		}else {
			meuPerfil.add(serie);			
		}
	}
	
	public void addWatchList(Serie serie) {
		if(watchlist == null) {
			watchlist = new ArrayList<>();
		}
		watchlist.add(serie);
		
	}
	

	public List<Serie> getMeuPerfil() {
		return meuPerfil;
	}

	public void setMeuPerfil(List<Serie> meuPerfil) {
		this.meuPerfil = meuPerfil;
	}

	public List<Serie> getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(List<Serie> watchlist) {
		this.watchlist = watchlist;
	}


	public boolean removerSeriePerfilID(String id2) {
		for(Serie serie : meuPerfil) {
			if(serie.getImdbID().equals(id2)) {
				return meuPerfil.remove(serie);
			}
		}
		return false;
	}

	public boolean removerSerieWatchlistID(String id2) {
		for(Serie serie : watchlist) {
			if(serie.getImdbID().equals(id2)) {
				return watchlist.remove(serie);
			}
		}
		return false;
		
	}

}
