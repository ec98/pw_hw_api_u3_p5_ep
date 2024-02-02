package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@GetMapping(path = "/{cedula}")
	public Profesor encontrar(@PathVariable String cedula) {
		return this.profesorService.buscando(cedula);
	}

	/**
	 * 
	 * @param titulo
	 * @param edad
	 * @return listProfesor.
	 */

	@GetMapping
	public List<Profesor> listProfesor(@RequestParam(required = true) String titulo,
			@RequestParam(required = true) int edad) {
		return this.profesorService.filtroPorEdad(titulo, edad);
	}

	@PostMapping
	public void almacenar(@RequestBody Profesor profesor) {
		this.profesorService.creando(profesor);
	}

	@PutMapping(path = "/{cedula}")
	public void actualizar(@RequestBody Profesor profesor, @PathVariable(required = true) String cedula) {
		profesor.setCedula(cedula);
		this.profesorService.actualizando(profesor);
	}

//	@PatchMapping(path = "/{cedula}")
//	public void updatePartial(@RequestBody Profesor profesor, @PathVariable(required = true) String cedula) {
//		this.profesorService.actualizarPartial(profesor.getEdad(), profesor.getEstadoCivil(), cedula);
//	}

	@PatchMapping(path = "/{cedula}")
	public void updatePartial2(@RequestBody Profesor profesor, @PathVariable(required = true) String cedula) {
		this.profesorService.actualizarPartial2(profesor.getTitulo(), profesor.getEstadoCivil(), cedula);
	}

	@DeleteMapping(path = "/{cedula}")
	public void eliminar(@PathVariable String cedula) {
		this.profesorService.eliminando(cedula);
	}
}
