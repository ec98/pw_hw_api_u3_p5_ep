package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Materia;

@Repository
@Transactional
public class MateriaRepositoryImpl implements IMateriaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Materia> selectIdEstudent(int id) {
		// TODO Auto-generated method stub
		TypedQuery<Materia> typedQuery = this.entityManager
				.createQuery("SELECT m FROM Materia m WHERE m.estudiante.id =:id ", Materia.class);
		typedQuery.setParameter("id", id);
		return typedQuery.getResultList();
	}

}