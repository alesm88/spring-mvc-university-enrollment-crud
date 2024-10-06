package com.bolsadeideas.springboot.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IUserDao;
import com.bolsadeideas.springboot.app.models.entity.Role;
import com.bolsadeideas.springboot.app.models.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUser(Long id) {
		return userDao.findOne(id);
	}
	
	@Override
	@Transactional
	public void deleteUser(Long id) {
		userDao.delete(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findUserByName(String username) {
		return userDao.findByName(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> login(String username, String password, Role role) {
		return Optional.ofNullable(userDao.findByName(username)).filter(u -> u.getPassword().equals(password) && u.getRole().equals(role));
	}
}
