package com.bolsadeideas.springboot.app.models.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="professors")
public class Professor extends Person {
	
	@NotNull
	@Column(columnDefinition = "BOOLEAN")
	private Boolean active;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="professor")
	private List<Subject> subjects;
	
	public Professor() {
		subjects = new ArrayList<>();
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
}
