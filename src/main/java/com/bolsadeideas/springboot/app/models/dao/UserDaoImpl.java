package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class UserDaoImpl implements IUserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> findAll() {
		return em.createQuery("from User").getResultList();
	}
	
	@Override
	public void save(User user) {
		if (user.getId() != null && user.getId() > 0) {
			em.merge(user);
		} else {
			em.persist(user);
		}
	}

	@Override
	public User findOne(Long id) {
		return em.find(User.class, id);
	}
	
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}
	
	@Override
	public User findByName(String username) {
		Query query = em.createQuery("from User u where u.username=:username", User.class);
        query.setParameter("username", username);
        query.setMaxResults(1);
        User user = new User();
        try {
        	user = (User) query.getSingleResult();
        } catch (NoResultException e){
        	e.printStackTrace();
        	user = null;
        }
		return user;
	}
	
}
