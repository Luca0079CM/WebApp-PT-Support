package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.ExerciseDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;

public class PersonalTrainerController {
	// Qui servirebbe @Inject
	PersonalTrainerDao ptDao;
	CustomerDao cDao;
	ExerciseDao exDao;
	WorkoutProgramDao wpDao;
	
	public PersonalTrainerController(EntityManagerFactory emf) {
		this.ptDao = new PersonalTrainerDao(emf);
		this.cDao = new CustomerDao(emf);
		this.exDao = new ExerciseDao(emf);
		this.wpDao = new WorkoutProgramDao(emf);
	}
	
	public void createCustomer(Long ptId, Long customerId, String name, String surname, String email, String password, LocalDate dateOfBirth) {
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setName(name);
		customer.setSurname(surname);
		customer.setEmail(email);
		customer.setDateOfBirth(dateOfBirth);
		customer.setPassword(password);
		customer.setPersonalTrainer(ptDao.findById(ptId));
		customer.setWorkoutProgramList(null);
		cDao.save(customer);
	}
	
	public void disableCustomer(Long customerId) {
		Customer customer = cDao.findById(customerId);
		customer.setPersonalTrainer(null);
		customer.setWorkoutProgramList(null);
		cDao.update(customer);
	}
	
	public void createExercise(Long id, String name, int difficultyLevel, String description) {
		Exercise ex = new Exercise();
		ex.setId(id);
		ex.setName(name);
		ex.setDifficultyLevel(difficultyLevel);
		ex.setDescription(description);
		exDao.save(ex);
	}
	
	public Exercise searchExercise(Long exId) {
		return exDao.findById(exId);
	}
	
	public void listCustomer() {
		for (Customer c : cDao.findAll()) {
			System.out.println("Nome: "+ c.getName());
			System.out.println("Cognome: "+c.getSurname());
			System.out.println("Email: "+c.getEmail());
			System.out.println("Data di nascita: "+c.getDateOfBirth());
			System.out.println("Personal Trainer del cliente: "
					+c.getPersonalTrainer().getName()+" "+c.getPersonalTrainer().getSurname());
			System.out.println("");
		}
	}
	
	public WorkoutProgram searchWorkoutProgram(Long wpId) {
		return wpDao.findById(wpId);
	}
	
	public void assignWorkoutProgramToCustomer(Long wpId, Long cId) {
		Customer c = cDao.findById(cId);
		WorkoutProgram wp = wpDao.findById(wpId);
		List<WorkoutProgram> wpList = c.getWorkoutProgramList();
		wpList.add(wp);
		c.setWorkoutProgramList(wpList);
		cDao.update(c);
	}
	
	public void addExerciseToWorkoutProgram(Long exId, Long wpId) {
		WorkoutProgram wp = wpDao.findById(wpId);
		Exercise ex = exDao.findById(exId);
		List<Exercise> exList = wp.getExerciseList();
		exList.add(ex);
		wp.setExerciseList(exList);
		wpDao.update(wp);
	}
	
	public void createWorkoutProgram(Long id, int difficultyLevel, int estimatedDuration, WorkoutProgramType wpType) {
		WorkoutProgram wp = new WorkoutProgram();
		wp.setId(id);
		wp.setDifficultyLevel(difficultyLevel);
		wp.setEstimatedDuration(estimatedDuration);
		wp.setWorkoutProgramType(wpType);
		wp.setExerciseList(null);
		wpDao.save(wp);
	}
	
	
	
}