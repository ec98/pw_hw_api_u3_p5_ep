package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IProfesorRepository;
import com.example.demo.repository.modelo.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private IProfesorRepository profesorRepository;

	@Override
	public void creando(Profesor profesor) {
		// TODO Auto-generated method stub
		this.profesorRepository.create(profesor);
	}

	@Override
	public Profesor buscando(String cedula) {
		// TODO Auto-generated method stub
		return this.profesorRepository.read(cedula);
	}

	@Override
	public void actualizando(Profesor profesor) {
		// TODO Auto-generated method stub
		this.profesorRepository.update(profesor);
	}

	@Override
	public void eliminando(String cedula) {
		// TODO Auto-generated method stub
		this.profesorRepository.delete(cedula);
	}

	@Override
	public void actualizarPartial(int edad, String estadoCivil, String cedula) {
		// TODO Auto-generated method stub
		this.profesorRepository.updateParcial(edad, estadoCivil, cedula);
	}

	@Override
	public List<Profesor> filtroPorEdad(String titulo, int edad) {
		// TODO Auto-generated method stub
		return this.profesorRepository.filterTE(titulo, edad);
	}

	@Override
	public void actualizarPartial2(String titulo, String estadoCivil, String cedula) {
		// TODO Auto-generated method stub
		this.profesorRepository.updateParcial2(titulo, estadoCivil, cedula);
	}


}
