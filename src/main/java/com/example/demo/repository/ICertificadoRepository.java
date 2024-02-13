package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Certificado;
import com.example.demo.service.to.CertificadoTO;

public interface ICertificadoRepository {

	public List<Certificado> selectCedulaEstudent(String cedula);

}
