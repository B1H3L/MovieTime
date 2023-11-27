package com.sistema.trailers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="tb_salas")
public class Salas {
	
	@Id
	@Column(name="cod_sala")
	private String codigoS;
	

	@Column(name="numero_sala")
	private String nombreS;
	

	@Column(name="butacas")
	private int Nbutacas;
	
	
	@ManyToOne
	@JoinColumn(name="cod_tipoSala")
	private TipoSala tiposala;


	public String getCodigoS() {
		return codigoS;
	}


	public void setCodigoS(String codigoS) {
		this.codigoS = codigoS;
	}


	public String getNombreS() {
		return nombreS;
	}


	public void setNombreS(String nombreS) {
		this.nombreS = nombreS;
	}


	public int getNbutacas() {
		return Nbutacas;
	}


	public void setNbutacas(int nbutacas) {
		Nbutacas = nbutacas;
	}


	public TipoSala getTiposala() {
		return tiposala;
	}


	public void setTiposala(TipoSala tiposala) {
		this.tiposala = tiposala;
	}


	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}



