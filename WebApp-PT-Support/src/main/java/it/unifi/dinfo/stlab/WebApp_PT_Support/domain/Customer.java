package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import java.time.LocalDate;
import java.util.List;
 import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	private Long id;
	private Long personalTrainerId;
	private String name;
	private String surname;
	private String email;
	// Da rivedere come implementare la password
	private String password;
	private LocalDate dateOfBirth;

	private PersonalTrainer personalTrainer;
	private List<WorkoutProgram> workoutProgramList = new ArrayList<>();
	// private WorkoutSession workout;


	@Id
//	@GeneratedValue
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
//		personalTrainer.addCustomer(this);
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
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || !(obj instanceof Customer))
			return false;
		Customer otherUser = (Customer)obj;
		return id.equals(otherUser.id);
	}

}