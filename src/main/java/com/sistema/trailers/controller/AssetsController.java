package com.sistema.trailers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.trailers.service.AlmacenServicoImpl;

@RestController
@RequestMapping("/assets")
public class AssetsController {
	@Autowired
	private AlmacenServicoImpl servicio;
	
	@GetMapping("/{filename:.+}")
	public Resource obtenerrecurso(@PathVariable("filename") String filename) {
		return servicio.cargarComoRecurso(filename);
		
	}

}
