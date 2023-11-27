package com.sistema.trailers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.trailers.models.Salas;
import com.sistema.trailers.repository.SalaRepository;
import com.sistema.trailers.repository.TipoSalaRepository;

@Controller
public class SalaController {
	
	@Autowired
	private SalaRepository sa;
	
	@Autowired
	private TipoSalaRepository tsa;
	
	
	@GetMapping("/listarSala")
	public String listadoSalas(Model model) {
		model.addAttribute("lstSala", sa.findAll());
		return "listadodesala";
	}
	
	@GetMapping("/grabarS")
	public String cargarpag(Model model) {
		Salas  sala = new Salas();
		model.addAttribute("sala", sala);
		model.addAttribute("lsttiposala", tsa.findAll());
		return "grabarsala";
	}
	
	
	@PostMapping("/grabarsala")
	public String grabarsala(@ModelAttribute Salas sala, RedirectAttributes attribute) {
		if(sa.save(sala) != null) {
			attribute.addFlashAttribute("success", "Registrado con éxito!");
		}else {
			attribute.addFlashAttribute("unsuccess", "Error registrando!");
		}
		return "redirect:/listarSala";
	}
	
	@PostMapping("/actualizarsala")
	public String actualizarsala(@ModelAttribute Salas sala, RedirectAttributes attribute) {
		if(sa.save( sala) != null) {
			attribute.addFlashAttribute("success", "Actualizado con éxito!");
		}else {
			attribute.addFlashAttribute("unsuccess", "Error actualizando!");
		}
		return "redirect:/editar/"+sala.getCodigoS();
	}
	@GetMapping("/editarsala/{codigoS}")
	public String editarsala(@ModelAttribute Salas sala, Model model) {
		model.addAttribute("sala", sa.findByCodigoS(sala.getCodigoS()));
		model.addAttribute("lsttiposala", tsa.findAll());
		return "editar-sala";
	}

	@GetMapping("/eliminarsala/{codigoS}")
	public String eliminarsala(@ModelAttribute Salas sala, RedirectAttributes attribute) {
		Salas salas = sa.findByCodigoS(sala.getCodigoS());
		sa.delete(salas);
		attribute.addFlashAttribute("success","Eliminado con éxito!");		
		return "redirect:/listarSala";
	}
	
	
	
	
	
	
	
	
	
	

}
