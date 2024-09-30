package com.bolsadeideas.springboot.app.models.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name="students")
public class Student extends Person {
	
	@NotNull
	@PositiveOrZero
	@Column(name="file_number")
	private Integer fileNumber;
	
	/*
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "student")
	private User user;
	*/
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tbl_students_subjects", joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns = @JoinColumn(name="subject_id"),
			uniqueConstraints=@UniqueConstraint(columnNames={"student_id", "subject_id"}))
	private List<Subject> subjects;

	public Student() {
		subjects = new ArrayList<>();
	}

	public Integer getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(Integer fileNumber) {
		this.fileNumber = fileNumber;
	}

	/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	*/

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public void addSubject(Subject subject) {
		this.subjects.add(subject);
		subject.getStudents().add(this);
	}
	
	public void removeSubject(Subject subject) {
		this.subjects.remove(subject);
		subject.getStudents().remove(this);
	}
	
	
}
