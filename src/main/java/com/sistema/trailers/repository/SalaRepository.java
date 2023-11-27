package com.sistema.trailers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.trailers.models.Salas;
import com.sistema.trailers.models.TipoSala;


public interface SalaRepository extends JpaRepository<Salas, String>{
	
	Salas  findByCodigoS(String codigoS);
	List<Salas> findByTiposala(TipoSala tiposala);

}
