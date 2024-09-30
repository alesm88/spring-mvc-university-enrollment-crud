package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IStudentDao;
import com.bolsadeideas.springboot.app.models.entity.Student;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentDao studentDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Student> findAllStudents() {
		return studentDao.findAll();
	}

	@Override
	@Transactional
	public void saveStudent(Student student) {
		studentDao.save(student);
	}

	@Override
	@Transactional(readOnly = true)
	public Student findStudent(Long id) {
		return studentDao.findOne(id);
	}

	@Override
	@Transactional
	public void deleteStudent(Long id) {
		studentDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Student findStudentByCardNumber(Integer username) {
		return studentDao.findByCardNumber(username);
	}

}
