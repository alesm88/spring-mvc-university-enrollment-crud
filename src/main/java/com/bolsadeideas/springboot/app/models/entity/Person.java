package com.bolsadeideas.springboot.app.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@MappedSuperclass
public class Person extends BaseEntity {
	
	@NotNull
	@PositiveOrZero
	@Column(name = "identity_card_number")
	private Integer cardNumber;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String surname;
		
	public Person() {
	}

	public Person(Integer id, Integer cardNumber, String name, String surname) {
		super(id);
		this.cardNumber = cardNumber;
		this.name = name;
		this.surname = surname;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
