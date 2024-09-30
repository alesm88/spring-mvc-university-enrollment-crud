package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.ISubjectDao;
import com.bolsadeideas.springboot.app.models.entity.Subject;

@Service
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	private ISubjectDao subjectDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Subject> findAllSubjects() {
		return subjectDao.findAllOrdered();
	}

	@Override
	@Transactional
	public void saveSubject(Subject subject) {
		subjectDao.save(subject);
	}

	@Override
	@Transactional(readOnly = true)
	public Subject findSubject(Long id) {
		return subjectDao.findOne(id);
	}

	@Override
	@Transactional
	public void deleteSubject(Long id) {
		subjectDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subject> findSubjectsByStudent(Integer id) {
		return subjectDao.subjectsByStudent(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Subject> findSubjectsByStudentNotEnrolAndQuota(Integer id) {
		return subjectDao.subjectsByStudentNotEnrolAndQuota(id);
	}

	@Override
	@Transactional
	public void unassignedProfessor(Integer idProfessor) {
		subjectDao.unassignedProfessorFromSubjects(idProfessor);
	}

}
