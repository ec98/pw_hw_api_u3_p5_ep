package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	// inyectando la capa repository
	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Override
	public void guardar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(estudiante);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizarParcial(apellido, nombre, id);
	}

	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionar(id);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.eliminar(id);
	}

	@Override
	public List<Estudiante> consultAll(String genero) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.selectAll(genero);
	}

	@Override
	public List<EstudianteTO> consultAllTo() {
		// TODO Auto-generated method stub
		// realizando una conversion tipo TO.
		List<Estudiante> list = this.estudianteRepository.selectAll("Femenina");
		List<EstudianteTO> listToFinal = new ArrayList<>();

		for (Estudiante est : list) {
			EstudianteTO convers = this.conversion(est);
			listToFinal.add(convers);
		}

		return listToFinal;
	}

	private EstudianteTO conversion(Estudiante estudiante) {
		EstudianteTO estuTo = new EstudianteTO();
		estuTo.setApellido(estudiante.getApellido());
		estuTo.setFechaNacimiento(estudiante.getFechaNacimiento());
		estuTo.setGenero(estudiante.getGenero());
		estuTo.setId(estudiante.getId()); // ?
		estuTo.setNombre(estudiante.getNombre());
		return estuTo;
	}
}
