package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Subject;

public interface ISubjectDao {
	
	public List<Subject> findAllOrdered();
	
	public void save(Subject subject);
	
	public Subject findOne(Long id);
	
	public void delete(Long id);
	
	public List<Subject> subjectsByStudent(Integer idStudent);
	
	public List<Subject> subjectsByStudentNotEnrolAndQuota(Integer idStudent);
	
	public void unassignedProfessorFromSubjects(Integer idProfessor);

}
