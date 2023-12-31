package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ptrainers")
public class PersonalTrainer {
	private Long id;
	private String name;
	private String surname;
	private String email;
	private LocalDate dateOfBirth;
	// da rivedere come implementare la password
	private String password;

	private List<Customer> customersList = new ArrayList<>();
//	private List<WorkoutProgram> workoutProgramList;


	@Id
//	@GeneratedValue
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

	public void setDateOfBirth(int year, int month, int day) {
		dateOfBirth = LocalDate.of(year, month, day);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="personalTrainer")//mappedBy="personalTrainer"
//	@JoinTable(name="ptrainers_customers",
//			   joinColumns = @JoinColumn(name="pt_id"),
//               inverseJoinColumns = @JoinColumn(name="customer_id"))
	public List<Customer> getCustomersList(){
		return customersList;
	}

	public void setCustomersList(List<Customer> customersList) {
		this.customersList = customersList;
	}

//	@ManyToMany(fetch=FetchType.LAZY)
//	public List<WorkoutProgram> getWorkoutProgramList() {
//		return workoutProgramList;
//	}
//
//	public void setWorkoutProgramList(List<WorkoutProgram> workoutProgramList) {
//		this.workoutProgramList = workoutProgramList;
//	}

	public void addCustomer(Customer customer) {
		this.customersList.add(customer);
//		customer.setPersonalTrainer(this);
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
		if ((obj == null) || !(obj instanceof PersonalTrainer))
			return false;
		PersonalTrainer otherUser = (PersonalTrainer)obj;
		return id.equals(otherUser.id);
	}

}
