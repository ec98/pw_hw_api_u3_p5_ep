package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Profesor;

public interface IProfesorService {

	// C-R-U-D
	public void creando(Profesor profesor);

	public Profesor buscando(String cedula);

	public void actualizando(Profesor profesor);

	public void eliminando(String cedula);

	// Parcial
	public void actualizarPartial(int edad, String estadoCivil, String cedula);
	
	public List<Profesor> filtroPorEdad(String titulo, int edad);

}
