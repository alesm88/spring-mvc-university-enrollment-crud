package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Subject;
import com.bolsadeideas.springboot.app.models.entity.User;

public interface IUserDao {
	
	public List<User> findAll();
	
	public void save(User user);
	
	public User findOne(Long id);
	
	public User findByName(String username);
	
	public void delete(Long id);

}
