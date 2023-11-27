package com.sistema.trailers.service;

import java.io.IOException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.trailers.exception.AlmacenException;
import com.sistema.trailers.exception.FileNotFoundException;



@Service
public class AlmacenServicoImpl implements AlmacenService {

	@Value("${storage.location}")
	private String storageLocation;

	// este metodo se ejecutara cuando haya una nueva instancia
	@PostConstruct
	@Override
	public void IniciarAlmacenDeArchivos() {
		try {
			Files.createDirectories(Paths.get(storageLocation));
		} catch (IOException excepcion) {
			throw new AlmacenException("Error al iniciar la ubicacion del archivo");
		}

	}

	@Override
	public String almacenarArchivos(MultipartFile archivo) {
		String nombreArchivo = archivo.getOriginalFilename();
		if (archivo.isEmpty()) {
			throw new AlmacenException("No se puede alamcenar un archivo vacio");
		}
		try {

			InputStream InputStream = archivo.getInputStream();
			Files.copy(InputStream, Paths.get(storageLocation).resolve(nombreArchivo),
					StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException excepcion) {
			throw new AlmacenException("Error al almacenar el archivo" + nombreArchivo, excepcion);
		}
		return nombreArchivo;

	}

	@Override
	public Path cargarArchivo(String nombreArchivo) {
		// TODO Auto-generated method stub
		return Paths.get(storageLocation).resolve(nombreArchivo);
	}

	@Override
	public Resource cargarComoRecurso(String nombreArchivo) {
		try {

			Path archivo = cargarArchivo(nombreArchivo);
			Resource recurso = new UrlResource(archivo.toUri());
			if (recurso.exists() || recurso.isReadable()) {
				return recurso;
			} else {
				throw new FileNotFoundException("No se pudo encontrar el archico" + nombreArchivo);
			}

		} catch (MalformedURLException excepcion) {
			throw new FileNotFoundException("No se pudo encontrar el archico" + nombreArchivo + excepcion);
		}
	}

	@Override
	public void eliminarArchivo(String nombreArchivo) {
		Path archivo = cargarArchivo(nombreArchivo);
		try {
			FileSystemUtils.deleteRecursively(archivo);
		} catch (Exception excepcion) {
			System.out.println(excepcion);
		}

	}

}
