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
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.GymMachineDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.*;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;

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
	GymMachineDao machineDao;
	@Inject
	CustomerMapper cMapper;
	@Inject
	ExerciseMapper exMapper;
	@Inject
	WorkoutProgramMapper wpMapper;
	
	//TODO: gestire in personal trainer e customer (dto) local date e string
	//TODO: gestire i parametri dei metodi dei controller (e quindi dei servizi rest di post e put) in modo che prendano solo il dto
	//--> gestire il setPersonalTrainer() delle entità a partire dal dto
	
	public CustomerDTO createCustomer(CustomerDTO cDTO) {
		Customer customer = new Customer();
		customer.setId(cDTO.getId());
		customer.setName(cDTO.getName());
		customer.setSurname(cDTO.getSurname());
		customer.setEmail(cDTO.getEmail());
		customer.setDateOfBirth(LocalDate.parse(cDTO.getDateOfBirth()));
		customer.setPassword(cDTO.getPassword());
		if(cDTO.getPersonalTrainerId() != null)
			customer.setPersonalTrainer(ptDao.findById(Long.valueOf(cDTO.getPersonalTrainerId())));
		else
			customer.setPersonalTrainer(null);
		customer.setWorkoutProgramList(null);
		cDao.save(customer);
		return cMapper.toDTO(customer);
	}
	
	public CustomerDTO disableCustomer(Long customerId) {
		Customer customer = cDao.findById(customerId);
		customer.setPersonalTrainer(null);
		customer.setWorkoutProgramList(null);
		cDao.update(customer);
		return cMapper.toDTO(customer);
	}
	
	public List<CustomerDTO> findCustomersByPTId(Long ptId) {
		PersonalTrainer pt = ptDao.findById(ptId);
		ArrayList<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
		for (Customer c : pt.getCustomersList())
			customerDTOList.add(cMapper.toDTO(c));
		return customerDTOList;
	}
	
	public ExerciseDTO createExercise(ExerciseDTO exDTO) {
		Exercise ex = new Exercise();
		ex.setId(exDTO.getId());
		ex.setName(exDTO.getName());
		ex.setDifficultyLevel(exDTO.getDifficultyLevel());
		ex.setDescription(exDTO.getDescription());
		if(exDTO.getMachineId() != null)
			ex.setMachine(machineDao.findById(exDTO.getMachineId()));
		else
			ex.setMachine(null);
		exDao.save(ex);
		return exMapper.toDTO(ex);
	}
	
	public WorkoutProgramDTO createWorkoutProgram(WorkoutProgramDTO wpDTO) {
		WorkoutProgram wp = new WorkoutProgram();
		wp.setId(wpDTO.getId());
		wp.setDifficultyLevel(wpDTO.getDifficultyLevel());
		wp.setEstimatedDuration(wpDTO.getEstimatedDuration());
		wp.setWorkoutProgramType(wpDTO.getWorkoutProgramType());
//		Gson gson = new Gson();
//		List<Exercise> exList = new ArrayList<Exercise>();
//		for(String exString : wpDTO.getExerciseList()) {
//			exList.add(gson.fromJson(exString, Exercise.class));
//		}
		wp.setExerciseList(null);
		wpDao.save(wp);
		return wpMapper.toDTO(wp);
	}
	
	public ExerciseDTO searchExercise(Long exId) {
		return exMapper.toDTO(exDao.findById(exId));
	}
	
	public WorkoutProgramDTO searchWorkoutProgram(Long wpId) {
		return wpMapper.toDTO(wpDao.findById(wpId));
	}
	
	public CustomerDTO assignWorkoutProgramToCustomer(Long wpId, Long cId) {
		Customer c = cDao.findById(cId);
		WorkoutProgram wp = wpDao.findById(wpId);
		List<WorkoutProgram> wpList = c.getWorkoutProgramList();
		wpList.add(wp);
		c.setWorkoutProgramList(wpList);
		cDao.update(c);
		return cMapper.toDTO(c);
	}
	
	public WorkoutProgramDTO addExerciseToWorkoutProgram(Long exId, Long wpId) {
		WorkoutProgram wp = wpDao.findById(wpId);
		Exercise ex = exDao.findById(exId);
		List<Exercise> exList = wp.getExerciseList();
		exList.add(ex);
		wp.setExerciseList(exList);
		wpDao.update(wp);
		return wpMapper.toDTO(wp);
	}
}