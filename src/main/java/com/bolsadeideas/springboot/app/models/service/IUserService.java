package com.bolsadeideas.springboot.app.models.service;

import java.util.List;
import java.util.Optional;

import com.bolsadeideas.springboot.app.models.entity.User;

public interface IUserService {

	public List<User> findAllUsers();
	
	public void saveUser(User User);
	
	public User findUser(Long id);
	
	public void deleteUser(Long id);
	
	public User findUserByName(String username);
	
	public Optional<User> login(String username, String password, String role);
	
}
