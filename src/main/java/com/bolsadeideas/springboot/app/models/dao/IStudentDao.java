package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Student;

public interface IStudentDao {
	
	public List<Student> findAll();
	
	public void save(Student student);
	
	public Student findOne(Long id);
	
	public void delete(Long id);
	
	public Student findByCardNumber(Integer username);

}
