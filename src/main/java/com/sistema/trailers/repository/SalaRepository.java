package com.sistema.trailers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.trailers.models.Salas;


public interface SalaRepository extends JpaRepository<Salas, String>{
	
	Salas  findByCodigoS(String codigoS);

}
