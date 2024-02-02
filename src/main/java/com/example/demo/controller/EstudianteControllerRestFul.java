package com.example.demo.controller;

import java.util.ArrayList;
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

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

//una API tiene muchos servicios
//API: por el proyecto JAVA

//servicio -> controller: clase controller
@RestController // tambien se le conoce como servicio
@RequestMapping(path = "/estudiantes")
public class EstudianteControllerRestFul {

	@Autowired
	private IEstudianteService estudianteService;

	// Metodos: capacidades
//	@PostMapping(path = "/guardar")
//	public void guardar(@RequestBody Estudiante estudiante) {
//		this.estudianteService.guardar(estudiante);
//	}

	//PATH VARIABLE: <<especificar un dato>>
	//example: /buscar/{id}	
//	@GetMapping(path = "/buscar/{id}/{nombre}")
//	public Estudiante buscar(@PathVariable int id, @PathVariable String nombre) {
//		System.out.println(nombre);
//		return this.estudianteService.buscar(id);
//	}

//	@DeleteMapping(path = "/borrar/{id}")
//	public void borrar(@PathVariable int id) {
//		this.estudianteService.borrar(id);
//	}
	// http://localhost:????/API/v1.0/Matricula/estudiantes/buscar

//	@PutMapping(path = "/actualizar")
//	public void actualizar(@RequestBody Estudiante estudiante) {
//		this.estudianteService.actualizar(estudiante);
//	}

//	@PatchMapping(path = "/actualizarParcial")
//	public void actualizarParcial(@RequestBody Estudiante estudiante) {
//		this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), estudiante.getId());
//	}
	
	//REQUEST PARAM <<filtrar o consultar todo>>
	//example: /estudiantes/listEstudiante?genero=Masculino
	// two o more REQUEST PARAM
	//example: /estudiantes/listEstudiante?genero=Masculino&contador=40
//	@GetMapping(path = "/listEstudiante")
//	public List<Estudiante> listaEstudiante(@RequestParam String genero, @RequestParam int contador){
//		
//		while(contador >= 10) {
//			contador++;
//			System.out.println(contador);
//		}
//		return this.estudianteService.consultAll(genero);
//	}
	
	//Buscar a partir de la ID.
	@GetMapping(path = "/{id}")
	public Estudiante consultar(@PathVariable int id) {
		return this.estudianteService.buscar(id);
	}
	
	@GetMapping
	public List<Estudiante> selectAll(@RequestParam(required = false, defaultValue = "Masculino") String genero){
		return this.estudianteService.consultAll(genero);
	}
	
	@PostMapping
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	
	@PutMapping(path = "/{id}")
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable int id) {
		//actualizar la informacion del estudiante o por su Id
		estudiante.setId(id);
		this.estudianteService.actualizar(estudiante);
	}
	
	@PatchMapping(path = "/{id}")
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable int id) {
		this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), id);
	}
	
	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable int id) {
		this.estudianteService.borrar(id);
	}
}
