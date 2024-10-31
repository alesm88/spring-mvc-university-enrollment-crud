package com.bolsadeideas.springboot.app.models.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="subjects")
public class Subject extends BaseEntity {
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	@NotEmpty
	private String timetable;
	@NotNull
	@PositiveOrZero
	private Integer enrolled;
	
	@NotNull
	@PositiveOrZero
	@Column(name="max_quota")
	private Integer maxQuota;
	
	@ManyToOne
	@JoinColumn(name="id_professor")
	private Professor professor;
	
	@ManyToMany(mappedBy="subjects")
	private List<Student> students;
	
	public Subject() {
		students = new ArrayList<>();
	}
	
	public Subject(Integer id, String name, String description, String timetable, Integer enrolled, Integer maxQuota, Professor professor) {
		super(id);
		this.name = name;
		this.description = description;
		this.timetable = timetable;
		this.enrolled = enrolled;
		this.maxQuota = maxQuota;
		this.professor = professor;
		students = new ArrayList<>();
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimetable() {
		return timetable;
	}

	public void setTimetable(String timetable) {
		this.timetable = timetable;
	}

	public Integer getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(Integer enrolled) {
		this.enrolled = enrolled;
	}
	
	public Integer getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(Integer maxQuota) {
		this.maxQuota = maxQuota;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Subject [name=" + name 
				+ ", description=" + description 
				+ ", timetable=" + timetable 
				+ ", enrolled="	+ enrolled 
				+ ", maxQuota="	+ maxQuota 
				+ ", professor=" + professor 
				+ ", students=" + students + "]";
	}
	
	
}
