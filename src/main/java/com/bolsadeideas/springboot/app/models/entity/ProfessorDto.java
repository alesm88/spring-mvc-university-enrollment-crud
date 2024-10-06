package com.bolsadeideas.springboot.app.models.entity;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(active, cardNumber, fullname, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfessorDto other = (ProfessorDto) obj;
		return Objects.equals(active, other.active) && Objects.equals(cardNumber, other.cardNumber)
				&& Objects.equals(fullname, other.fullname) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ProfessorDto [id=" + id 
				+ ", cardNumber=" + cardNumber 
				+ ", fullname=" + fullname 
				+ ", active=" + active + "]";
	}
	
}
