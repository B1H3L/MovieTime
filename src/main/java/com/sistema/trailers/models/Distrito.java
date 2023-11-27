
package com.sistema.trailers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_distrito")
public class Distrito {
	
	public String getCodigoD() {
		return codigoD;
	}

	public void setCodigoD(String codigoD) {
		this.codigoD = codigoD;
	}

	public String getNombreD() {
		return nombreD;
	}

	public void setNombreD(String nombreD) {
		this.nombreD = nombreD;
	}

	@Id
	@Column(name="cod_distrito")
	private String codigoD;
	
	@Column(name="nombre_distrito")
	private String nombreD;
	
	
	
	

}
