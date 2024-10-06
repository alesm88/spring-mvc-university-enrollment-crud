package com.bolsadeideas.springboot.app.models.entity;

public enum Role {
	ADMIN("This user has privileges to edit or delete"),
	STUDENT("This user can enrol to subjects");
	
	private String description;
	
	Role() {
		
	}
	
	Role(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public Boolean isAdmin() {
		return this == ADMIN;
	}
	
	public Boolean isStudent() {
		return this == STUDENT;
	}
}
