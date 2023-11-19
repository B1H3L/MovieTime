package com.sistema.trailers.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula")
	private int id;

	@NotBlank
	private String titulo;

	@NotBlank
	private String sinopsis;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaEstreno;

	@NotBlank
	private String trailer;

	private String rutaPortada;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "genero_pelicula", joinColumns = @JoinColumn(name = "id_pelicula"), inverseJoinColumns = @JoinColumn(name = "id_genero"))
	private List<Genero> generos;

	@Transient
	private MultipartFile portada;

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

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getRutaPortada() {
		return rutaPortada;
	}

	public void setRutaPortada(String rutaPortada) {
		this.rutaPortada = rutaPortada;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public MultipartFile getPortada() {
		return portada;
	}

	public void setPortada(MultipartFile portada) {
		this.portada = portada;
	}

	public Pelicula() {
		super();
	}

	public Pelicula(int id, @NotBlank String titulo, @NotBlank String sinopsis, @NotNull LocalDate fechaEstreno,
			@NotBlank String trailer, String rutaPortada, @NotEmpty List<Genero> generos, MultipartFile portada) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.fechaEstreno = fechaEstreno;
		this.trailer = trailer;
		this.rutaPortada = rutaPortada;
		this.generos = generos;
		this.portada = portada;
	}

	public Pelicula(@NotBlank String titulo, @NotBlank String sinopsis, @NotNull LocalDate fechaEstreno,
			@NotBlank String trailer, String rutaPortada, @NotEmpty List<Genero> generos, MultipartFile portada) {
		super();
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.fechaEstreno = fechaEstreno;
		this.trailer = trailer;
		this.rutaPortada = rutaPortada;
		this.generos = generos;
		this.portada = portada;
	}

}
