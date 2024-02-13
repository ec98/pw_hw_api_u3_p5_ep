package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ICertificadoRepository;
import com.example.demo.repository.modelo.Certificado;
import com.example.demo.service.to.CertificadoTO;

@Service
public class CertificadoServiceImpl implements ICertificadoService {

	@Autowired
	private ICertificadoRepository iCertificadoRepository;

	@Override
	public List<CertificadoTO> buscarCedulaEst(String cedula) {
		// TODO Auto-generated method stub

		List<Certificado> lsc = this.iCertificadoRepository.selectCedulaEstudent(cedula);
		List<CertificadoTO> lscTO = new ArrayList<>();

		for (Certificado c : lsc) {
			CertificadoTO convert = this.conversor(c);
			lscTO.add(convert);
		}

		return lscTO;
	}

	private CertificadoTO conversor(Certificado certificado) {
		CertificadoTO cto = new CertificadoTO();
		cto.setHoras(certificado.getHoras());
		cto.setTitulo(certificado.getTitulo());
		return cto;
	}

}
