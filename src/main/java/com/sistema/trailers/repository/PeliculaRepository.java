package com.sistema.trailers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.trailers.models.Genero;
import com.sistema.trailers.models.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
	List<Pelicula> findByGeneros(Genero genero);
}
