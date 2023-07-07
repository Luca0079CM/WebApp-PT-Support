package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import java.time.LocalDate;
// import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ptrainers")
public class PersonalTrainer {
	private int id;
	private String name;
	private String surname;
	private String email;
	private LocalDate dateOfBirth;
	// da rivedere come implementare la password
	private String password;
	/*/
	private ArrayList<User> usersList;
	private ArrayList<WorkoutProgram> workoutProgramList;
	/*/
	
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
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void setDateOfBirth(int year, int month, int day) {
		dateOfBirth = LocalDate.of(year, month, day);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*/
	public ArrayList<User> getUsersList(){
		return usersList;
	}
	
	public void setUsersList(ArrayList<User> usersList) {
		this.usersList = usersList;
	}
	
	public ArrayList<WorkoutProgram> getWorkoutProgramList() {
		return workoutProgramList;
	}

	public void setWorkoutProgramList(ArrayList<WorkoutProgram> workoutProgramList) {
		this.workoutProgramList = workoutProgramList;
	}
	/*/
}
