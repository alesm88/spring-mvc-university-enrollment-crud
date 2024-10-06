package com.bolsadeideas.springboot.app.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User extends BaseEntity {
	
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;
	
	/*
	@OneToOne
	@JoinColumn(name="student_user_id")
	private Student student;
	*/
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/*
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	*/
	
	@Override
	public String toString() {
		return "User [username=" + username + ", role=" + role + "]";
	}
}
