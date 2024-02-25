package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Estudiante;

@Repository
@Transactional
public class EstudianteRepositoryImpl implements IEstudianteRepository {

	// interaccion con DB
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.persist(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.merge(estudiante);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {
		// TODO Auto-generated method stub

		// SQL: UPDATE estudiante e set e.estu_nombre=:valor, e.estu_apellido=:valor2
		// WHERE e.estu_id=:valor3

		// usando Querys -> {Query}
		Query query = this.entityManager
				.createQuery("UPDATE Estudiante e SET e.nombre=:valor1, e.apellido=:valor2 WHERE e.id=:valor3"); // JPQL
		query.setParameter("valor1", nombre);
		query.setParameter("valor2", apellido);
		query.setParameter("valor3", id);

		query.executeUpdate(); // retorna un numero (registros que se actualicen)
	}

	@Override
	public Estudiante seleccionar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Estudiante.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		Estudiante ident = this.seleccionar(id);
		this.entityManager.remove(ident);
	}

	@Override
	public List<Estudiante> selectAll(String genero) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.genero =:valor",
				Estudiante.class);
		myQuery.setParameter("valor", genero);
		return myQuery.getResultList();
	}

}
