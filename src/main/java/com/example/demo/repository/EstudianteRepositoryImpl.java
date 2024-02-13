package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EstudianteRepositoryImpl implements IEstudianteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.persist(estudiante);
	}

	@Override
	public Estudiante read(String cedula) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Estudiante.class, cedula);
	}

	@Override
	public void update(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.merge(estudiante);
	}

	@Override
	public void updatePartial(String carrera, String nombre, String cedula) {
		// TODO Auto-generated method stub
		Query query = this.entityManager
				.createQuery("UPDATE estudiante e SET e.nombre=:valor1, e.carrera=:valor2 WHERE e.cedula=:valor3");
		query.setParameter("valor1", nombre);
		query.setParameter("valor2", carrera);
		query.setParameter("valor3", cedula);

		query.executeUpdate();
	}

	@Override
	public void delete(String cedula) {
		// TODO Auto-generated method stub
		Estudiante get = this.read(cedula);
		this.entityManager.remove(get);
	}

	@Override
	public List<Estudiante> readAll(String carrera) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> typedQuery = this.entityManager
				.createQuery("SELECT e FROM estudiante e WHERE e.carrera =:valor", Estudiante.class);
		typedQuery.setParameter("valor", carrera);
		return typedQuery.getResultList();
	}

}
