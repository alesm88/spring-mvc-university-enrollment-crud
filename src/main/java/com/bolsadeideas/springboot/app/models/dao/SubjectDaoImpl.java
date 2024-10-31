package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Subject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SubjectDaoImpl implements ISubjectDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Subject> findAllOrdered() {
		return em.createQuery("select s from Subject s left outer join fetch s.professor ORDER BY s.name ASC", Subject.class).getResultList();
	}
	
	@Override
	public void save(Subject subject) {
		if (subject.getId() != null && subject.getId() > 0) {
			em.merge(subject);
		} else {
			em.persist(subject);
		}
	}

	@Override
	public Subject findOne(Long id) {
		return em.createQuery("select s from Subject s left outer join fetch s.professor where s.id=:id", Subject.class)
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

	@Override
	public List<Subject> subjectsByStudent(Integer idStudent) {
		return em.createQuery("select m from Subject m join m.students s WHERE s.id=:id", Subject.class)
				.setParameter("id", idStudent).getResultList();
	}
	
	@Override
	public List<Subject> subjectsByStudentNotEnrolAndQuota(Integer idStudent) {
		return em.createQuery("select m from Subject m WHERE m.maxQuota-m.enrolled>0 AND m.id NOT IN (SELECT mat.id FROM Student e JOIN e.subjects mat WHERE e.id=:idStudent)", Subject.class)
				.setParameter("idStudent", idStudent).getResultList();
	}
	
	@Override
	public void unassignedProfessorFromSubjects(Integer idProfessor) {
		em.createQuery("update Subject m SET m.professor = null WHERE m.professor.id=:idProfessor")
				.setParameter("idProfessor", idProfessor).executeUpdate();
	}
}
