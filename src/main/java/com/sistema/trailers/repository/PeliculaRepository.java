package com.sistema.trailers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.trailers.models.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {


}
