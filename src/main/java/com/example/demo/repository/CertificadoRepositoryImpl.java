package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Certificado;
import com.example.demo.service.to.CertificadoTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CertificadoRepositoryImpl implements ICertificadoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Certificado> selectCedulaEstudent(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Certificado> typedQuery = this.entityManager
				.createQuery("SELECT c FROM certificado c WHERE c.estudiante.cedula =:cedula", Certificado.class);
		typedQuery.setParameter("cedula", cedula);
		return typedQuery.getResultList();
	}

}
