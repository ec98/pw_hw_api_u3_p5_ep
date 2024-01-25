package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;

//una API tiene muchos servicios
//API: por el proyecto JAVA

//servicio -> controller: clase controller
@RestController // tambien se le conoce como servicio

public class EstudianteControllerRestFul {

	// Metodos: capacidades
	public void guardar(Estudiante estudiante) {
		System.out.println("guardando estudiante.");
	}
}
