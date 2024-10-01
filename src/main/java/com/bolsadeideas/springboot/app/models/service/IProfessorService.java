package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Professor;
import com.bolsadeideas.springboot.app.models.entity.ProfessorDto;

public interface IProfessorService {

	public List<Professor> findAllProfessors();
	
	public List<ProfessorDto> findAllProfessorsActive();
	
	public void saveProfessor(Professor professor);
	
	public Professor findProfessor(Long id);
	
	public void deleteProfessor(Long id);
	
	public List<ProfessorDto> getAllProfessorsDto();
	
}
