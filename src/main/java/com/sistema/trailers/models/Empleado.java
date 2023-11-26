package com.sistema.trailers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_Empleado")
public class Empleado {
	@Id
	@Column(name="cod_Empleado")
	private String codigoE;
	
	@Column(name="nombre_Empleado")
	private String nombreE;

	@Column(name="apellido_Empleado")
	private String apellidoE;

	@Column(name="dni_Empleado")
	private String dniE;
	
	@Column(name="sueldo_Empleado")
	private double sueldoE;
	
	@ManyToOne
	@JoinColumn(name="cod_distrito")
	private Distrito distrito;



}
