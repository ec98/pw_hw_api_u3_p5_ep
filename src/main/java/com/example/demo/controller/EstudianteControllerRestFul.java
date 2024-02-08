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

	// PATH VARIABLE: <<especificar un dato>>
	// example: /buscar/{id}
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

	// REQUEST PARAM <<filtrar o consultar todo>>
	// example: /estudiantes/listEstudiante?genero=Masculino
	// two o more REQUEST PARAM
	// example: /estudiantes/listEstudiante?genero=Masculino&contador=40
//	@GetMapping(path = "/listEstudiante")
//	public List<Estudiante> listaEstudiante(@RequestParam String genero, @RequestParam int contador){
//		
//		while(contador >= 10) {
//			contador++;
//			System.out.println(contador);
//		}
//		return this.estudianteService.consultAll(genero);
//	}

	// Buscar a partir de la ID.
	@GetMapping(path = "/{id}",produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Estudiante> consultar(@PathVariable int id) {
		// 240: grupo satisfactorias
		// 240: recurso ESTUDIANTE encontrado satisfactoriamente
		Estudiante estu = this.estudianteService.buscar(id);
//		int status = 241; // seteando codigo

		// http status code
//		HttpStatusCode -> HttpStatus.METHOD_NOT_ALLOWED
		return ResponseEntity.status(HttpStatus.OK).body(estu);
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Estudiante>> selectAll(
			@RequestParam(required = false, defaultValue = "Masculino") String genero) {
		List<Estudiante> estu = this.estudianteService.consultAll(genero);
		int status = 242;
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("msj_242", "Lista de ESTUDIANTES consultada de manera satisfactoria");
		// other head, can you put more headers.
		cabeceras.add("msj_inf_242", "El sistema se va a estar en mantenimiento");
		return new ResponseEntity<>(estu, cabeceras, status);
	}
	
	//Tambien puede haber dos contenidos en uno solo en la capacidad.
	//@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	//"produces" es para tipo de retorno
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}

	@PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable int id) {
		// actualizar la informacion del estudiante o por su Id
		estudiante.setId(id);
		this.estudianteService.actualizar(estudiante);
	}

	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable int id) {
		this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), id);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void borrar(@PathVariable int id) {
		this.estudianteService.borrar(id);
	}
}
