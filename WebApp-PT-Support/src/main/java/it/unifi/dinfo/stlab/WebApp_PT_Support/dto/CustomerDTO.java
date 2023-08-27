package it.unifi.dinfo.stlab.WebApp_PT_Support.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerDTO implements Serializable {
	private Long id;
	private Long personalTrainerId;
	private String name;
	private String surname;
	private String email;
	private String dateOfBirth;
	private String password;
	private String personalTrainer;
	private String[] workoutProgramList;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPersonalTrainerId() {
		return personalTrainerId;
	}
	
	public void setPersonalTrainerId(Long personalTrainerId) {
		this.personalTrainerId = personalTrainerId;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPersonalTrainer() {
		return personalTrainer;
	}
	
	public void setPersonalTrainer(String personalTrainer) {
		this.personalTrainer = personalTrainer;
	}
	
	public String[] getWorkoutProgramList() {
		return workoutProgramList;
	}
	
	public void setWorkoutProgramList(String[] workoutProgramList) {
		this.workoutProgramList = workoutProgramList;
	}
	
}
