package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class StudentDaoImpl implements IStudentDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Student> findAll() {
		return em.createQuery("from Student", Student.class).getResultList();
	}

	@Override
	public void save(Student student) {
		if (student.getId() != null && student.getId() > 0) {
			em.merge(student);
		} else {
			em.persist(student);
		}
	}

	@Override
	public Student findOne(Long id) {
		return em.find(Student.class, id);
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

	@Override
	public Student findByCardNumber(Integer username) {
		return em.createQuery("select s from Student s where s.cardNumber=:username", Student.class)
				.setParameter("username", username).getSingleResult();
	}

}
