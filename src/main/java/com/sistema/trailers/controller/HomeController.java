package com.sistema.trailers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.trailers.models.Pelicula;
import com.sistema.trailers.repository.PeliculaRepository;

@RestController
@RequestMapping("")
public class HomeController {

	@Autowired
	private PeliculaRepository pelicularepositorio;

	@GetMapping("")
	public ModelAndView verpaginadeinicio() {
		List<Pelicula> ultimaspeliculas = pelicularepositorio
				.findAll(PageRequest.of(0, 4, Sort.by("fechaEstreno").descending())).toList();
		return new ModelAndView("index").addObject("ultimaspeliculas", ultimaspeliculas);
	}

	@GetMapping("/peliculas")
	public ModelAndView listarpeliculas(
			@PageableDefault(sort = "fechaEstreno", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Pelicula> peliculas = pelicularepositorio.findAll(pageable);
		return new ModelAndView("peliculas").addObject("peliculas", peliculas);

	}
	
	@GetMapping("/peliculas/{id}")
	public ModelAndView mostrardetallesdepeliculas(@PathVariable Integer id) {
		Pelicula pelicula=pelicularepositorio.getOne(id);
		return new ModelAndView("pelicula").addObject("pelicula",pelicula);
		
	}
	
	

}
