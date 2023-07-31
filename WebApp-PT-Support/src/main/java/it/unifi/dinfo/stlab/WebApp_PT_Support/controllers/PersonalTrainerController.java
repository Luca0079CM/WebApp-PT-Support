package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.ExerciseDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.*;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import jakarta.inject.Inject;

public class PersonalTrainerController {
	
	@Inject
	PersonalTrainerDao ptDao;
	@Inject
	CustomerDao cDao;
	@Inject
	ExerciseDao exDao;
	@Inject
	WorkoutProgramDao wpDao;
	@Inject
	CustomerMapper cMapper;
	@Inject
	ExerciseMapper exMapper;
	@Inject
	WorkoutProgramMapper wpMapper;
	
	public CustomerDTO createCustomer(Long ptId, Long customerId, String name, String surname, String email, String password, LocalDate dateOfBirth) {
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
		return cMapper.generateCustomerTO(customer);
	}
	
	public CustomerDTO disableCustomer(Long customerId) {
		Customer customer = cDao.findById(customerId);
		customer.setPersonalTrainer(null);
		customer.setWorkoutProgramList(null);
		cDao.update(customer);
		return cMapper.generateCustomerTO(customer);
	}
	
	public ExerciseDTO createExercise(Long id, String name, int difficultyLevel, String description) {
		Exercise ex = new Exercise();
		ex.setId(id);
		ex.setName(name);
		ex.setDifficultyLevel(difficultyLevel);
		ex.setDescription(description);
		exDao.save(ex);
		return exMapper.generateExerciseTO(ex);
	}
	
	public ExerciseDTO searchExercise(Long exId) {
		return exMapper.generateExerciseTO(exDao.findById(exId));
	}
	
	public List<CustomerDTO> findCustomersByPTId(Long ptId) {
		PersonalTrainer pt = ptDao.findById(ptId);
		ArrayList<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
		for (Customer c : pt.getCustomersList())
			customerDTOList.add(cMapper.generateCustomerTO(c));
		return customerDTOList;
	}
	
	public WorkoutProgramDTO searchWorkoutProgram(Long wpId) {
		return wpMapper.generateWorkoutProgramTO(wpDao.findById(wpId));
	}
	
	public CustomerDTO assignWorkoutProgramToCustomer(Long wpId, Long cId) {
		Customer c = cDao.findById(cId);
		WorkoutProgram wp = wpDao.findById(wpId);
		List<WorkoutProgram> wpList = c.getWorkoutProgramList();
		wpList.add(wp);
		c.setWorkoutProgramList(wpList);
		cDao.update(c);
		return cMapper.generateCustomerTO(c);
	}
	
	public WorkoutProgramDTO addExerciseToWorkoutProgram(Long exId, Long wpId) {
		WorkoutProgram wp = wpDao.findById(wpId);
		Exercise ex = exDao.findById(exId);
		List<Exercise> exList = wp.getExerciseList();
		exList.add(ex);
		wp.setExerciseList(exList);
		wpDao.update(wp);
		return wpMapper.generateWorkoutProgramTO(wp);
	}
	
	public WorkoutProgramDTO createWorkoutProgram(Long id, int difficultyLevel, int estimatedDuration, WorkoutProgramType wpType) {
		WorkoutProgram wp = new WorkoutProgram();
		wp.setId(id);
		wp.setDifficultyLevel(difficultyLevel);
		wp.setEstimatedDuration(estimatedDuration);
		wp.setWorkoutProgramType(wpType);
		wp.setExerciseList(null);
		wpDao.save(wp);
		return wpMapper.generateWorkoutProgramTO(wp);
	}
	
}