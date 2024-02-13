package com.example.demo.service.to;

import java.io.Serializable;

import jakarta.persistence.Column;

public class CertificadoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8994236892756098913L;

	private String titulo;

	private Integer horas;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

}
