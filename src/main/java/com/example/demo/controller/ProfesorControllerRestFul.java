package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Profesor;
import com.example.demo.service.IProfesorService;

@RestController
@RequestMapping(path = "/profesores")
public class ProfesorControllerRestFul {

	@Autowired
	private IProfesorService profesorService;

	// URL API => http://localhost:8070/API/v2.1/Profesion/profesores/almacenar

	// CRUD
//	@PostMapping(path = "/almacenar")
//	public void almacenar(@RequestBody Profesor profesor) {
//		this.profesorService.creando(profesor);
//	}

//	@GetMapping(path = "/encontrar/{cedula}")
//	public Profesor encontrar(@PathVariable String cedula) {
//		return this.profesorService.buscando(cedula);
//	}

//	@PutMapping(path = "/actualizar")
//	public void actualizar(@RequestBody Profesor profesor) {
//		this.profesorService.actualizando(profesor);
//	}

//	@DeleteMapping(path = "/eliminar/{cedula}")
//	public void eliminar(@PathVariable String cedula) {
//		this.profesorService.eliminando(cedula);
//	}

	// PATCH
//	@PatchMapping(path = "/updatePartial")
//	public void updatePartial(@RequestBody Profesor profesor) {
//		this.profesorService.actualizarPartial(profesor.getEdad(), profesor.getEstadoCivil(), profesor.getCedula());
//	}

	// REQUEST PARAM
	/**
	 * 
	 * @FiltrarPorTituloYEdadMayor
	 * 
	 *                             URL API =>
	 *                             http://localhost:8070/API/v2.1/Profesion/profesores/listProfesor?titulo=Ciencias
	 *                             en la Computacion&edad=10
	 */
//	@GetMapping(path = "/listProfesor")
//	public List<Profesor> listProfesor(@RequestParam String titulo, @RequestParam int edad) {
//		return this.profesorService.filtroPorEdad(titulo, edad);
//	}

	// CRUD - PATCH
	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Profesor> encontrar(@PathVariable String cedula) {
		Profesor prof = this.profesorService.buscando(cedula);
		// status and body
//		int status = 240;
//		return ResponseEntity.status(status).body(prof);
		return ResponseEntity.status(HttpStatus.OK).body(prof);
	}

	/**
	 * 
	 * @param titulo
	 * @param edad
	 * @return listProfesor.
	 */

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Profesor>> listProfesor(@RequestParam(required = true) String titulo,
			@RequestParam(required = true) int edad) {
		List<Profesor> list = this.profesorService.filtroPorEdad(titulo, edad);
		// status and headers
//		int status = 242;
		HttpHeaders headers = new HttpHeaders();
		headers.add("msj_242", "Enviando informacion de PROFESOR");
		headers.add("msj_242_satisfaccion", "El proceso de PROFESOR ha sido satisfactorio");
		// nuevo retorn(list,headers,statusCode)
		return new ResponseEntity<>(list, headers, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public void almacenar(@RequestBody Profesor profesor) {
		this.profesorService.creando(profesor);
	}

	@PutMapping(path = "/{cedula}", consumes = MediaType.APPLICATION_XML_VALUE)
	public void actualizar(@RequestBody Profesor profesor, @PathVariable(required = true) String cedula) {
		profesor.setCedula(cedula);
		this.profesorService.actualizando(profesor);
	}

//	@PatchMapping(path = "/{cedula}")
//	public void updatePartial(@RequestBody Profesor profesor, @PathVariable(required = true) String cedula) {
//		this.profesorService.actualizarPartial(profesor.getEdad(), profesor.getEstadoCivil(), cedula);
//	}

	@PatchMapping(path = "/{cedula}", consumes = MediaType.APPLICATION_XML_VALUE)
	public void updatePartial2(@RequestBody Profesor profesor, @PathVariable(required = true) String cedula) {
		this.profesorService.actualizarPartial2(profesor.getTitulo(), profesor.getEstadoCivil(), cedula);
	}

	@DeleteMapping(path = "/{cedula}")
	public void eliminar(@PathVariable String cedula) {
		this.profesorService.eliminando(cedula);
	}

//	@GetMapping(path = "/respuesta/{cedula}", consumes = MediaType.APPLICATION_XML_VALUE)
//	public void respuesta(@PathVariable String cedula) {
//		try {
//			ResponseEntity<Profesor> pf = encontrar(cedula);
//			if (pf != null) {
//				System.out.println("Profesor encontrado -> " + pf.toString());
//			} else {
//				System.out.println("Profesor no encontrado");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
