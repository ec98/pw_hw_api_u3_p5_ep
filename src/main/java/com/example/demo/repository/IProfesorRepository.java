package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Profesor;

public interface IProfesorRepository {
	// C-R-U-D
	public void create(Profesor profesor);

	public Profesor read(String cedula);

	public void update(Profesor profesor);

	public void delete(String cedula);

	// Parcial
	public void updateParcial(int edad, String estadoCivil, String cedula);
	
	public void updateParcial2(String titulo, String estadoCivil, String cedula);
	
	public List<Profesor> filterTE(String titulo, int edad);
}
