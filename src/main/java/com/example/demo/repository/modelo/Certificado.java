package com.example.demo.repository.modelo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table
@Entity(name = "certificado")
@JsonIgnoreProperties("estudiante")
public class Certificado {

	@Id
	@GeneratedValue(generator = "seq_certificado", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_certificado", sequenceName = "seq_certificado", allocationSize = 1)
	@Column(name = "cert_id")
	private Integer id;

	@Column(name = "cert_titulo")
	private String titulo;

	@Column(name = "cert_horas")
	private Integer horas;

	@Column(name = "cert_fecha_hoja")
	private LocalDateTime fechaHoja;

	@ManyToOne
	@JoinColumn(name = "cert_id_estudiante")
	private Estudiante estudiante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public LocalDateTime getFechaHoja() {
		return fechaHoja;
	}

	public void setFechaHoja(LocalDateTime fechaHoja) {
		this.fechaHoja = fechaHoja;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

}
