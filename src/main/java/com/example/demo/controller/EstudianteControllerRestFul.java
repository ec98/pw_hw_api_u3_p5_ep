package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;

//una API tiene muchos servicios
//API: por el proyecto JAVA

//servicio -> controller: clase controller
@RestController // tambien se le conoce como servicio
@RequestMapping(path = "/estudiantes")
@CrossOrigin
//(value = "http://localhost:8086")
public class EstudianteControllerRestFul {

	@Autowired
	private IEstudianteService estudianteService;

	@Autowired
	private IMateriaService materiaService;

	// Buscar a partir de la ID.
	@GetMapping(path = "tmp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> consultar(@PathVariable int id) {
		EstudianteTO estu = this.estudianteService.buscarTO(id);

		Link link = linkTo(methodOn(EstudianteControllerRestFul.class).consultMateriaId(estu.getId()))
				.withRel("materias");

		estu.add(link);

		return ResponseEntity.status(HttpStatus.OK).body(estu);
	}

	@GetMapping(path = "/tmp", produces = MediaType.APPLICATION_XML_VALUE)
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

	@GetMapping(path = "/esto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> consultarTO(@PathVariable int id) {
		EstudianteTO estu = this.estudianteService.buscarTO(id);

		Link link = linkTo(methodOn(EstudianteControllerRestFul.class).consultMateriaId(estu.getId()))
				.withRel("materias");

		estu.add(link);

		return ResponseEntity.status(HttpStatus.OK).body(estu);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteLigeroTO> consultLigeroTO(@PathVariable int id) {
		EstudianteLigeroTO estuTo = this.estudianteService.buscarLigeroTO(id);

		Link link = linkTo(methodOn(EstudianteControllerRestFul.class).consultarTO(estuTo.getId()))
				.withRel("EstudianteTO");
		estuTo.add(link);

		return ResponseEntity.status(HttpStatus.OK).body(estuTo);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteTO>> selectAllHateoas() {
		List<EstudianteTO> estuTo = this.estudianteService.consultAllTo();

		for (EstudianteTO eto : estuTo) {
			Link link = linkTo(methodOn(EstudianteControllerRestFul.class).consultMateriaId(eto.getId()))
					.withRel("materias");
			eto.add(link);
		}

		return ResponseEntity.status(HttpStatus.OK).body(estuTo);
	}

	// http://localhost:8086://API/v1.0/Matricula/estudiantes GET
	// http://localhost:8086://API/v1.0/Matricula/estudiantes/1 GET
	// http://localhost:8086://API/v1.0/Matricula/estudiantes/1/materias GET

	@GetMapping(path = "/{id}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MateriaTO>> consultMateriaId(@PathVariable(required = true) int id) {
		List<MateriaTO> listTo = this.materiaService.buscarIdEstud(id);
		return ResponseEntity.status(HttpStatus.OK).body(listTo);
	}

	// Tambien puede haber dos contenidos en uno solo en la capacidad.
	// @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces =
	// MediaType.APPLICATION_XML_VALUE)
	// "produces" es para tipo de retorno
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable int id) {
		// actualizar la informacion del estudiante o por su Id
		estudiante.setId(id);
		this.estudianteService.actualizar(estudiante);
	}

	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable int id) {
		this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), id);
	}

	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable int id) {
		this.estudianteService.borrar(id);
	}
}
