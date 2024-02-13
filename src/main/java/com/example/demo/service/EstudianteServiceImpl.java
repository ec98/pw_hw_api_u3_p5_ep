package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepository iEstudianteRepository;

	@Override
	public void guardar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.iEstudianteRepository.crear(estudiante);
	}

	@Override
	public Estudiante buscar(String cedula) {
		// TODO Auto-generated method stub
		return this.iEstudianteRepository.read(cedula);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.iEstudianteRepository.update(estudiante);
	}

	@Override
	public void actualizarParcial(String carrera, String nombre, String cedula) {
		// TODO Auto-generated method stub
		this.iEstudianteRepository.updatePartial(carrera, nombre, cedula);
	}

	@Override
	public void eliminar(String cedula) {
		// TODO Auto-generated method stub
		this.iEstudianteRepository.delete(cedula);
	}

	@Override
	public List<Estudiante> buscarTodos(String carrera) {
		// TODO Auto-generated method stub
		return this.iEstudianteRepository.readAll(carrera);
	}

	@Override
	public List<EstudianteTO> buscarTodosTO(String carrera) {
		// TODO Auto-generated method stub

		List<Estudiante> ls = this.iEstudianteRepository.readAll(carrera);
		List<EstudianteTO> lsTo = new ArrayList<EstudianteTO>();

		for (Estudiante est : ls) {
			EstudianteTO convers = this.conversor(est);
			lsTo.add(convers);
		}

		return lsTo;
	}

	private EstudianteTO conversor(Estudiante estudiante) {
		EstudianteTO esto = new EstudianteTO();
		esto.setCedula(estudiante.getCedula());
		esto.setApellido(estudiante.getApellido());
		esto.setEstadoCivil(estudiante.getEstadoCivil());
		esto.setFechaNacimiento(estudiante.getFechaNacimiento());
		return esto;
	}

	@Override
	public EstudianteTO buscarTO(String cedula) {
		// TODO Auto-generated method stub
		return this.conversor(this.iEstudianteRepository.read(cedula));
	}

}
