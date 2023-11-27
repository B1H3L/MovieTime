package com.sistema.trailers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_tipoSala")
public class TipoSala {
	
	@Id
	@Column(name="cod_tipoSala")
	private String codtipoSala;


	@Column(name="tipoSala")
	private String tipoSala;


	public String getCodtipoSala() {
		return codtipoSala;
	}


	public void setCodtipoSala(String codtipoSala) {
		this.codtipoSala = codtipoSala;
	}


	public String getTipoSala() {
		return tipoSala;
	}


	public void setTipoSala(String tipoSala) {
		this.tipoSala = tipoSala;
	}
	
	
	
	
	
	

}
