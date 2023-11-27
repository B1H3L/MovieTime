package com.sistema.trailers.models;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genero {

	@Id
	@Column(name = "id_genero")
	private int idGenero;

	@Column(name="titulo")
	private String titulo;

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	// constructor vacio
	public Genero() {
		super();
	}

	// constructor completo
	public Genero(int id, String titulo) {
		super();
		this.idGenero = id;
		this.titulo = titulo;
	}

	// constructor de id
	public Genero(int id) {
		super();
		this.idGenero = id;
	}

	// constructor de titulo
	public Genero(String titulo) {
		super();
		this.titulo = titulo;
	}

}
