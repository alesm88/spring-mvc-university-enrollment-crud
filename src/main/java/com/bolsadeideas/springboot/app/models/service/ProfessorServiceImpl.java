package com.bolsadeideas.springboot.app.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IProfessorDao;
import com.bolsadeideas.springboot.app.models.entity.Professor;
import com.bolsadeideas.springboot.app.models.entity.ProfessorDto;

@Service
public class ProfessorServiceImpl implements IProfessorService {

	@Autowired
	private IProfessorDao professorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Professor> findAllProfessors() {
		return professorDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProfessorDto> findAllProfessorsActive() {
		List<Professor> professors = professorDao.findAllActive();
		return professors.stream()
				.map(professor -> new ProfessorDto(
						professor.getId(),
						professor.getCardNumber(),
						professor.getSurname() + ", " + professor.getName(),
						professor.getActive()
				))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void saveProfessor(Professor professor) {
		professorDao.save(professor);
	}

	@Override
	@Transactional(readOnly = true)
	public Professor findProfessor(Long id) {
		return professorDao.findOne(id);
	}

	@Override
	@Transactional
	public void deleteProfessor(Long id) {
		professorDao.delete(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProfessorDto> getAllProfessorsDto() {
		List<Professor> professors = professorDao.findAll();
		return professors.stream()
				.map(professor -> new ProfessorDto(
						professor.getId(),
						professor.getCardNumber(),
						professor.getSurname() + ", " + professor.getName(),
						professor.getActive()
				))
				.collect(Collectors.toList());
	}

}
