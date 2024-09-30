package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Professor;

public interface IProfessorService {

	public List<Professor> findAllProfessors();
	
	public List<Professor> findAllProfessorsActive();
	
	public void saveProfessor(Professor professor);
	
	public Professor findProfessor(Long id);
	
	public void deleteProfessor(Long id);
	
}
