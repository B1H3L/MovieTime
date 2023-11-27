package com.sistema.trailers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.trailers.models.Usuario;

@Controller
@RequestMapping("")
public class UsuarioController {
	@GetMapping("/logueo")
	public String index() {
		return "login";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	

}
