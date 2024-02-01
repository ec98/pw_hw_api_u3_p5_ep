package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Profesor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProfesorRepositoryImpl implements IProfesorRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Profesor profesor) {
		// TODO Auto-generated method stub
		this.entityManager.persist(profesor);
	}

	@Override
	public Profesor read(String cedula) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Profesor.class, cedula);
	}

	@Override
	public void update(Profesor profesor) {
		// TODO Auto-generated method stub
		this.entityManager.merge(profesor);
	}

	@Override
	public void delete(String cedula) {
		// TODO Auto-generated method stub
		Profesor getCedula = this.read(cedula);
		this.entityManager.remove(getCedula);
	}

	@Override
	public void updateParcial(int edad, String estadoCivil, String cedula) {
		// TODO Auto-generated method stub
		Query query = this.entityManager
				.createQuery("UPDATE Profesor p SET p.edad =:valor1, p.estadoCivil =:valor2 WHERE p.cedula =:valor3");
		query.setParameter("valor1", edad);
		query.setParameter("valor2", estadoCivil);
		query.setParameter("valor3", cedula);

		query.executeUpdate();
	}

	@Override
	public List<Profesor> filterTE(String titulo, int edad) {
		// TODO Auto-generated method stub
		TypedQuery<Profesor> typedQuery = this.entityManager
				.createQuery("SELECT p FROM Profesor p WHERE p.titulo =:valor1 AND p.edad > :valor2", Profesor.class);
		typedQuery.setParameter("valor1", titulo);
		typedQuery.setParameter("valor2", edad);
		return typedQuery.getResultList();
	}
}
