package com.sistema.trailers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.trailers.models.Empleado;
import com.sistema.trailers.repository.DistritoRepository;
import com.sistema.trailers.repository.EmpleadoRepository;



@Controller

public class EmpleadoController {
	
	@Autowired
	private DistritoRepository dis;
	@Autowired
	private EmpleadoRepository em;
	
	@GetMapping("/listarEmpleado")
	public String listadoEmpleado(Model model) {
		model.addAttribute("lstempleado", em.findAll());
		return "listadoEmpleado";
	}
	@GetMapping("/grabarempleado")
	public String cargarPag(Model model) {
		Empleado emple = new Empleado();
		model.addAttribute("empleado", emple);
		model.addAttribute("lstdistrito", dis.findAll());
		return "grabarEmpleado";
	}
	
	
	@PostMapping("/grabarempleado")
	public String grabarPag(@ModelAttribute Empleado empleado, RedirectAttributes attribute) {
		if(em.save( empleado) != null) {
			attribute.addFlashAttribute("success", "Registrado con éxito!");
		}else {
			attribute.addFlashAttribute("unsuccess", "Error registrando!");
		}
		return "redirect:/listarEmpleado";
	}
	
	
	@PostMapping("/actualizarempleado")
	public String actualizarPag(@ModelAttribute  Empleado empleado, RedirectAttributes attribute) {
		if(em.save(empleado) != null) {
			attribute.addFlashAttribute("success", "Actualizado con éxito!");
		}else {
			attribute.addFlashAttribute("unsuccess", "Error actualizando!");
		}
		return "redirect:/editar/"+empleado.getCodigoE();
	}
	
	
	@GetMapping("/editar/{CodigoE}")
	public String editar(@ModelAttribute Empleado empleado, Model model) {
		model.addAttribute("empleado", em.findBycodigoE(empleado.getCodigoE()));
		model.addAttribute("lstdistrito",dis.findAll());
		return "editar-empleado";
	}
	
	
	@GetMapping("/eliminar/{CodigoE}")
	public String eliminar(@ModelAttribute Empleado empleado, RedirectAttributes attribute) {
		Empleado emple = em.findBycodigoE(empleado.getCodigoE());
		em.delete(emple);
		attribute.addFlashAttribute("success","Eliminado con éxito!");		
		return "redirect:/listarEmpleado";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
