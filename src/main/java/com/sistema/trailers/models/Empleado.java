package com.sistema.trailers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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

	
	
	public String getCodigoE() {
		return codigoE;
	}

	public void setCodigoE(String codigoE) {
		this.codigoE = codigoE;
	}

	public String getNombreE() {
		return nombreE;
	}

	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}

	public String getApellidoE() {
		return apellidoE;
	}

	public void setApellidoE(String apellidoE) {
		this.apellidoE = apellidoE;
	}

	public String getDniE() {
		return dniE;
	}

	public void setDniE(String dniE) {
		this.dniE = dniE;
	}

	public double getSueldoE() {
		return sueldoE;
	}

	public void setSueldoE(double sueldoE) {
		this.sueldoE = sueldoE;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}




}
