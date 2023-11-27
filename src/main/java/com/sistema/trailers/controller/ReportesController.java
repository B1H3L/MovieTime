package com.sistema.trailers.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.trailers.models.Genero;
import com.sistema.trailers.models.Pelicula;
import com.sistema.trailers.models.Salas;
import com.sistema.trailers.models.TipoSala;
import com.sistema.trailers.repository.GeneroRepository;
import com.sistema.trailers.repository.PeliculaRepository;
import com.sistema.trailers.repository.SalaRepository;
import com.sistema.trailers.repository.TipoSalaRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReportesController {
	
	@Autowired
	private PeliculaRepository pe;
	
	@Autowired
	private GeneroRepository ge;
	
	@Autowired
	private SalaRepository sa;
	
	@Autowired
	private TipoSalaRepository tisa;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private DataSource dataSource;
	
	@GetMapping("/reportes")
	public String reportes(Model model) {
		return "reportes";
	}
	
	@GetMapping("/reportePeliculas")
	public String reporte(Model model) {
		List<Genero> listaGeneros = ge.findAll();
		model.addAttribute("listaGeneros", listaGeneros);
		return "reportePeliculas";
	}
	
	@PostMapping(value="/reportePelis", params="listar")
	public String listarPelis(@RequestParam(name="idGenero") int idGenero, Model model) {
		List<Genero> listaGeneros = ge.findAll();
		Genero genero = new Genero();
		genero.setIdGenero(idGenero);
		List<Pelicula> listaPeliculas = pe.findByGeneros(genero);
		model.addAttribute("listaPeliculas", listaPeliculas);
		model.addAttribute("listaGeneros", listaGeneros);
		model.addAttribute("seleccion", idGenero);
		return "reporteListarPeliculas";
	}
	
	@PostMapping(value="/reportePelis", params="exportar")
	public void exportarPelis(@RequestParam(name="idGenero") int idGenero, HttpServletResponse response) throws JRException, SQLException {
		String nombreReporte = "reporte_peliculas";
		response.setHeader("Content-Disposition", "attachment; filename="+nombreReporte+".pdf");
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:reportes/reportePeliculas.jasper").getURI().getPath();
			HashMap<String, Object>parametros = new HashMap<>();
			parametros.put("idGenero", idGenero);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection()); 
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/reporteSalas")
	public String reporteS(Model model) {
		List<TipoSala> listaTipoSala = tisa.findAll();
		model.addAttribute("listaTipoSala", listaTipoSala);
		return "reporteSalas";
	}
	
	@PostMapping(value="/reporteSala", params="listar")
	public String listarSalas(@RequestParam(name="codtipoSala") String codtipoSala, Model model) {
		List<TipoSala> listaTipoSala = tisa.findAll();
		TipoSala tiposala = new TipoSala();
		tiposala.setCodtipoSala(codtipoSala);
		List<Salas> listaSalas = sa.findByTiposala(tiposala);
		model.addAttribute("listaSalas", listaSalas);
		model.addAttribute("listaTipoSala", listaTipoSala);
		model.addAttribute("seleccion", codtipoSala);
		return "reporteListarSalas";
	}
	
	@PostMapping(value="/reporteSala", params="exportar")
	public void exportarSalas(@RequestParam(name="codTipoSala") String codtipoSala, HttpServletResponse response) throws JRException, SQLException {
		String nombreReporte = "reporte_salas";
		response.setHeader("Content-Disposition", "attachment; filename="+nombreReporte+".pdf");
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:reportes/reporteSalas.jasper").getURI().getPath();
			HashMap<String, Object>parametros = new HashMap<>();
			parametros.put("codtipoSala", codtipoSala);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection()); 
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
