package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Student;

public interface IStudentService {

	public List<Student> findAllStudents();
	
	public void saveStudent(Student student);
	
	public Student findStudent(Long id);
	
	public void deleteStudent(Long id);
	
	public Student findStudentByCardNumber(Integer username);
	
}
