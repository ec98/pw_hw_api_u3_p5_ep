package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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
import com.example.demo.service.ICertificadoService;
import com.example.demo.service.IEstudianteService;
import com.example.demo.service.to.CertificadoTO;
import com.example.demo.service.to.EstudianteTO;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteControllerRestFul {

	@Autowired
	private IEstudianteService iEstudianteService;

	@Autowired
	private ICertificadoService iCertificadoService;

	// GET
//	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Estudiante> encontrar(@PathVariable String cedula) {
//		Estudiante entry = this.iEstudianteService.buscar(cedula);
//		return ResponseEntity.status(HttpStatus.OK).body(entry);
//	}

	// POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void guardar(@RequestBody Estudiante estudiante) {
		this.iEstudianteService.guardar(estudiante);
	}

	// PUT
	@PutMapping(path = "/{cedula}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable(required = true) String cedula) {
		estudiante.setCedula(cedula);
		this.iEstudianteService.actualizar(estudiante);
	}

	// PATCH
	@PatchMapping(path = "/{cedula}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable(required = true) String cedula) {
		this.iEstudianteService.actualizarParcial(estudiante.getCarrera(), estudiante.getNombre(), cedula);
	}

	// DELETE
	@DeleteMapping(path = "/{cedula}")
	public void eliminar(@PathVariable String cedula) {
		this.iEstudianteService.eliminar(cedula);
	}

	// GETALL
//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<Estudiante>> buscarTodo(@RequestParam String carrera) {
//		List<Estudiante> savels = this.iEstudianteService.buscarTodos(carrera);
//
//		HttpHeaders headers = new HttpHeaders();
//
//		headers.add("msj_#01", "Informacion completada");
//		headers.add("msj_#02", "Proceso completado " + carrera);
//
//		return new ResponseEntity<>(savels, headers, HttpStatus.OK);
//	}

	// hateoas lvl 3
//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<EstudianteTO>> selectAllHateoas(@RequestParam String carrera) {
//		List<EstudianteTO> lsto = this.iEstudianteService.buscarTodosTO(carrera);
//		return ResponseEntity.status(HttpStatus.OK).body(lsto);
//	}

	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> encontrar(@PathVariable String cedula) {
		EstudianteTO entryTO = this.iEstudianteService.buscarTO(cedula);

		Link link = linkTo(methodOn(EstudianteControllerRestFul.class).consultCedEstu(entryTO.getCedula()))
				.withRel("certificadosPersonal");
		entryTO.add(link);

		return ResponseEntity.status(HttpStatus.OK).body(entryTO);
	}

	// hateoas lvl 3 links
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteTO>> selectAllHateoas(@RequestParam String carrera) {
		List<EstudianteTO> lsto = this.iEstudianteService.buscarTodosTO(carrera);

		for (EstudianteTO eto : lsto) {
			Link link = linkTo(methodOn(EstudianteControllerRestFul.class).encontrar(eto.getCedula()))
					.withRel("certificado");
			eto.add(link);
		}

		return ResponseEntity.status(HttpStatus.OK).body(lsto);
	}

	@GetMapping(path = "/{cedula}/certificados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CertificadoTO>> consultCedEstu(@PathVariable(required = true) String cedula) {
		List<CertificadoTO> clsTo = this.iCertificadoService.buscarCedulaEst(cedula);
		return ResponseEntity.status(HttpStatus.OK).body(clsTo);
	}

}
