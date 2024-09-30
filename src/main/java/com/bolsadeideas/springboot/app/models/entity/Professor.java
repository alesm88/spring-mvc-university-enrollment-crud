package com.bolsadeideas.springboot.app.models.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name="professors")
public class Professor extends Person {
	
	@NotNull
	@PositiveOrZero
	private Integer active;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="professor")
	private List<Subject> subjects;
	
	public Professor() {
		subjects = new ArrayList<>();
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
}
