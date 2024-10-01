package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Professor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProfessorDaoImpl implements IProfessorDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Professor> findAll() {
		return em.createQuery("from Professor", Professor.class).getResultList();
	}
	
	@Override
	public List<Professor> findAllActive() {
		return em.createQuery("select p from Professor p where p.active=true", Professor.class).getResultList();
	}

	@Override
	public void save(Professor professor) {
		if (professor.getId() != null && professor.getId() > 0) {
			em.merge(professor);
		} else {
			em.persist(professor);
		}
	}

	@Override
	public Professor findOne(Long id) {
		return em.find(Professor.class, id);
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
