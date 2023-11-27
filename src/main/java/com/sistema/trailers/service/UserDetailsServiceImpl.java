package com.sistema.trailers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sistema.trailers.models.Usuario;
import com.sistema.trailers.repository.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsuario(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return new MyUserDetails(usuario);
	}


}
