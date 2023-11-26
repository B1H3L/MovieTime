
package com.sistema.trailers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_distrito")
public class Distrito {
	
	@Id
	@Column(name="cod_distrito")
	private String codigoD;
	
	@Column(name="nombre_distrito")
	private String nombreD;
	
	
	
	

}
