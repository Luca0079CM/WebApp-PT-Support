package it.unifi.dinfo.stlab.WebApp_PT_Support.dto;

import java.time.LocalDate;

public class PersonalTrainerDTO {
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String dateOfBirth;
	
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
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
}
