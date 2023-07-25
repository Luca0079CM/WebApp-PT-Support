package it.unifi.dinfo.stlab.WebApp_PT_Support.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerDTO implements Serializable {
	private Long id;
	private String name;
	private String surname;
	private String email;
	private LocalDate dateOfBirth;
	private String personalTrainer;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getPersonalTrainer() {
		return personalTrainer;
	}
	
	public void setPersonalTrainer(String personalTrainer) {
		this.personalTrainer = personalTrainer;
	}
	
}
