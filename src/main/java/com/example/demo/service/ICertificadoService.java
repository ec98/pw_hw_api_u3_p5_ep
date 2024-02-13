package com.example.demo.service;

import java.util.List;

import com.example.demo.service.to.CertificadoTO;

public interface ICertificadoService {

	public List<CertificadoTO> buscarCedulaEst(String cedula);
}
