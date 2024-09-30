package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Subject;

public interface ISubjectService {

	public List<Subject> findAllSubjects();
	
	public void saveSubject(Subject subject);
	
	public Subject findSubject(Long id);
	
	public void deleteSubject(Long id);
	
	public List<Subject> findSubjectsByStudent(Integer id);
	
	public List<Subject> findSubjectsByStudentNotEnrolAndQuota(Integer id);
	
	public void unassignedProfessor(Integer idProfessor);
	
}
