package com.sistema.trailers.service;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AlmacenService {

	public void IniciarAlmacenDeArchivos();

	public String almacenarArchivos(MultipartFile archivo);

	public Path cargarArchivo(String nombreArchivo);

	public Resource cargarComoRecurso(String nombreArchivo);

	public void eliminarArchivo(String nombreArchivo);

}
