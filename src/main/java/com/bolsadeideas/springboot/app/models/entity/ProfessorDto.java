package com.bolsadeideas.springboot.app.models.entity;

public class ProfessorDto {
	
	private Integer id;
	private Integer cardNumber;
	private String fullname;
	private Boolean active;
	
	public ProfessorDto(Integer id, Integer cardNumber, String fullname, Boolean active) {
		this.id = id;
		this.cardNumber = cardNumber;
		this.fullname = fullname;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
