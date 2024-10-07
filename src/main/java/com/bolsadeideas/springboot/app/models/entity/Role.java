package com.bolsadeideas.springboot.app.models.entity;

public enum Role {
	ADMIN("Admin"),
	STUDENT("Student");
	
	private String displayName;
	
	Role() {
		
	}
	
	Role(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public Boolean isAdmin() {
		return this == ADMIN;
	}
	
	public Boolean isStudent() {
		return this == STUDENT;
	}
}
