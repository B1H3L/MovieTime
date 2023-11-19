package com.sistema.trailers.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.trailers.models.Genero;
import com.sistema.trailers.models.Pelicula;
import com.sistema.trailers.repository.GeneroRepository;
import com.sistema.trailers.repository.PeliculaRepository;
import com.sistema.trailers.service.AlmacenServicoImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private PeliculaRepository pelicularepositorio;

	@Autowired
	private GeneroRepository generorepositorio;

	@Autowired
	private AlmacenServicoImpl servicio;

	@GetMapping("")
	public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
		Page<Pelicula> peliculas = pelicularepositorio.findAll(pageable);
		return new ModelAndView("admin/index").addObject("peliculas", peliculas);
	}

	@GetMapping("/peliculas/nuevo")
	public ModelAndView mostrarformulariodenuevapelicula() {
		List<Genero> generos = generorepositorio.findAll(Sort.by("titulo"));
		return new ModelAndView("admin/nueva-pelicula")
				.addObject("pelicula", new Pelicula())
				.addObject("generos",generos);
	}
	
	
	@PostMapping("/peliculas/nuevo")
	public ModelAndView registrarpelicula(@Validated Pelicula pelicula,BindingResult bindingResult) {
		if(bindingResult.hasErrors()||pelicula.getPortada().isEmpty()) {
			if(pelicula.getPortada().isEmpty()) {
			bindingResult.rejectValue("portada", "MultipartNotEmpty");
			}
			List<Genero> generos=generorepositorio.findAll(Sort.by("titulo"));
			return new ModelAndView("admin/nueva-pelicula")
					.addObject("pelicula", pelicula)
					.addObject("generos",generos);
			
		}
	
		String rutaPortada=servicio.almacenarArchivos(pelicula.getPortada());
		pelicula.setRutaPortada(rutaPortada);
		pelicularepositorio.save(pelicula);
		return new ModelAndView("redirect:/admin");
		
	}
	
	@GetMapping("/peliculas/{id}/editar")
	public ModelAndView mostrareditarpelicula(@PathVariable Integer id) {
		Pelicula pelicula =pelicularepositorio.getOne(id);
		List<Genero> generos=generorepositorio.findAll(Sort.by("titulo"));
		return new ModelAndView("admin/editar-pelicula")
				.addObject("pelicula", pelicula)
				.addObject("generos",generos);
	}
	
	@PostMapping("/peliculas/{id}/editar")
	public ModelAndView actualizarpelicula(@PathVariable Integer id,@Validated Pelicula pelicula,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			List<Genero> generos=generorepositorio.findAll(Sort.by("titulo"));
			return new ModelAndView("admin/editar-pelicula")
					.addObject("pelicula", pelicula)
					.addObject("generos",generos);
		
		}
		Pelicula peliculabd=pelicularepositorio.getOne(id);
		peliculabd.setTitulo(pelicula.getTitulo());
		peliculabd.setSinopsis(pelicula.getSinopsis());
		peliculabd.setFechaEstreno(pelicula.getFechaEstreno());
		peliculabd.setTrailer(pelicula.getTrailer());
		peliculabd.setGeneros(pelicula.getGeneros());
		if(!pelicula.getPortada().isEmpty()) {
			servicio.eliminarArchivo(peliculabd.getRutaPortada());
			String rutaPortada=servicio.almacenarArchivos(pelicula.getPortada());
			peliculabd.setRutaPortada(rutaPortada);
		}
		pelicularepositorio.save(peliculabd);
		return new ModelAndView("redirect:/admin");
	}
	
	
	@PostMapping("/peliculas/{id}/eliminar")
	public String eliminarpelicula(@PathVariable Integer id) {
		
		Pelicula pelicula=pelicularepositorio.getOne(id);
		pelicularepositorio.delete(pelicula);
		servicio.eliminarArchivo(pelicula.getRutaPortada());
		return "redirect:/admin";
	}
	
	
}
