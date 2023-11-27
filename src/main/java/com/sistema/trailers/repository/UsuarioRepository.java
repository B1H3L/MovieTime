package com.sistema.trailers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.trailers.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByUsuario(String usuario);
}

