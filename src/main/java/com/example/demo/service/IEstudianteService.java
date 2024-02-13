package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTO;

public interface IEstudianteService {

	public void guardar(Estudiante estudiante);

	public Estudiante buscar(String cedula);
	
	public EstudianteTO buscarTO(String cedula);

	public void actualizar(Estudiante estudiante);

	public void actualizarParcial(String carrera, String nombre, String cedula);

	public void eliminar(String cedula);
	
	public List<Estudiante> buscarTodos(String carrera);
	
	public List<EstudianteTO> buscarTodosTO(String carrera);
}
