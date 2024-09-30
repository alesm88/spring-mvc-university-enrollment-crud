package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Professor;

public interface IProfessorDao {
	
	public List<Professor> findAll();
	
	public List<Professor> findAllActive();
	
	public void save(Professor student);
	
	public Professor findOne(Long id);
	
	public void delete(Long id);

}
