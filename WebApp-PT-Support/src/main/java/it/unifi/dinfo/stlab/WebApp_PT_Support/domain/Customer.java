package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.List;
// import java.util.ArrayList;

@Entity
@Table(name = "customers")
public class Customer {
	private int id;
	private String name;
	private String surname;
	private String email;
	// Da rivedere come implementare la password
	private String password;
	private LocalDate dateOfBirth;
	
	private PersonalTrainer personalTrainer;
	private List<WorkoutProgram> workoutProgramList;
	// private WorkoutSession workout;
	
	
	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfBirth() {
		
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void setDateOfBirth(int year, int month, int day) {
		dateOfBirth = LocalDate.of(year, month, day);
	}

	@ManyToOne(fetch=FetchType.EAGER)
	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}

	public void setPersonalTrainer(PersonalTrainer personalTrainer) {
		this.personalTrainer = personalTrainer;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public List<WorkoutProgram> getWorkoutProgramList() {
		return workoutProgramList;
	}

	public void setWorkoutProgramList(List<WorkoutProgram> workoutProgramList) {
		this.workoutProgramList = workoutProgramList;
	}
	
}