package com.sistema.trailers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.trailers.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String>{

	Empleado findBycodigoE(String codigoE);
	
	
	
}
