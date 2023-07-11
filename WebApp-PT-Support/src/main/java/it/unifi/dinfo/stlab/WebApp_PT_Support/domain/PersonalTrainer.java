package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	private List<Customer> usersList;
	private List<WorkoutProgram> workoutProgramList;
	
	
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
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="customers_ptrainers", 
	joinColumns={@JoinColumn(name="ptrainers_id")}, inverseJoinColumns={@JoinColumn(name="customers_id")})
	public List<Customer> getCustomersList(){
		return usersList;
	}
	
	public void setCustomersList(List<Customer> usersList) {
		this.usersList = usersList;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	public List<WorkoutProgram> getWorkoutProgramList() {
		return workoutProgramList;
	}

	public void setWorkoutProgramList(List<WorkoutProgram> workoutProgramList) {
		this.workoutProgramList = workoutProgramList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		PersonalTrainer other = (PersonalTrainer) obj;
		if (id != other.getId())
			return false;
		return true;
	}
	
}
