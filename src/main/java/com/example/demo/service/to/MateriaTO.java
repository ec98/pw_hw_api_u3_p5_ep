package com.example.demo.service.to;

import java.io.Serializable;

public class MateriaTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3563295222331324897L;

	private int id;
	private String nombre;
	private Integer creditos;

	//get y set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

}
