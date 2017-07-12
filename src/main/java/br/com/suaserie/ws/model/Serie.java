package br.com.suaserie.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "serie")
@Table(name = "tb_serie")
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String titulo;
	@Column
	private String plot;
	@Column
	private String notaIMdb;
	@Column
	private String classificacaoIMdb;
	@Column
	private String userNota;
	@Column
	private String ultimoEPVisto;
	@Column
	private String linkIMG;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getNotaIMdb() {
		return notaIMdb;
	}

	public void setNotaIMdb(String notaIMdb) {
		this.notaIMdb = notaIMdb;
	}

	public String getClassificacaoIMdb() {
		return classificacaoIMdb;
	}

	public void setClassificacaoIMdb(String classificacaoIMdb) {
		this.classificacaoIMdb = classificacaoIMdb;
	}

	public String getUserNota() {
		return userNota;
	}

	public void setUserNota(String userNota) {
		this.userNota = userNota;
	}

	public String getUltimoEPVisto() {
		return ultimoEPVisto;
	}

	public void setUltimoEPVisto(String ultimoEPVisto) {
		this.ultimoEPVisto = ultimoEPVisto;
	}

	public String getLinkIMG() {
		return linkIMG;
	}

	public void setLinkIMG(String linkIMG) {
		this.linkIMG = linkIMG;
	}

}
