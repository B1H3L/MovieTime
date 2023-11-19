package com.sistema.trailers.models;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genero {

	@Id
	@Column(name = "id_genero")
	private int id;

	private String titulo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		this.id = id;
		this.titulo = titulo;
	}

	// constructor de id
	public Genero(int id) {
		super();
		this.id = id;
	}

	// constructor de titulo
	public Genero(String titulo) {
		super();
		this.titulo = titulo;
	}

}
