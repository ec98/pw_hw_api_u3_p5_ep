package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Estudiante;

public interface IEstudianteRepository {
	
	//CRUD -> {Create, Read, Update (Parcial), Delete}
	public void insertar(Estudiante estudiante);
	public void actualizar(Estudiante estudiante);
	
	//ojo con los atributos (livianos/pesados) -> informacion.
	public void actualizarParcial(String apellido, String nombre, Integer id);
	
	//puede ser id, nombre, cedula.
	public Estudiante seleccionar(Integer id);
	
	public void eliminar(Integer id);
	
	public List<Estudiante> selectAll(String genero);
}
