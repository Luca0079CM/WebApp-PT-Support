package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
// import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {
	private int id;
	private String name;
	private String surname;
	private String email;
	// Da rivedere come implementare la password
	private String password;
	private LocalDate dateOfBirth;
	/*/
	private PersonalTrainer personalTrainer;
	private ArrayList<WorkoutProgram> workoutProgramList;
	private WorkoutSession workout;
	/*/
	@Id
	@Column(name = "id", nullable = false)
	@NotNull
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

	/*/
	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}

	public void setPersonalTrainer(PersonalTrainer personalTrainer) {
		this.personalTrainer = personalTrainer;
	}

	public ArrayList<WorkoutProgram> getWorkoutProgramList() {
		return workoutProgramList;
	}

	public void setWorkoutProgramList(ArrayList<WorkoutProgram> workoutProgramList) {
		this.workoutProgramList = workoutProgramList;
	}

	public WorkoutSession getWorkout() {
		return workout;
	}

	public void setWorkout(WorkoutSession workout) {
		this.workout = workout;
	}
	
	/*/
}