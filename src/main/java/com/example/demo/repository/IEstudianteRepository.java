package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTO;

public interface IEstudianteRepository {

	public void crear(Estudiante estudiante);

	public Estudiante read(String cedula);

	public void update(Estudiante estudiante);

	public void updatePartial(String carrera, String nombre, String cedula);

	public void delete(String cedula);

	public List<Estudiante> readAll(String carrera);
	
}
