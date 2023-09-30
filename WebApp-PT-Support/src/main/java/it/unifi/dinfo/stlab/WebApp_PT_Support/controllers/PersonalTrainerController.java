package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.ExerciseDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.GymMachineDao;
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
	GymMachineDao machineDao;
	@Inject
	PersonalTrainerMapper ptMapper;
	@Inject
	CustomerMapper cMapper;
	@Inject
	ExerciseMapper exMapper;
	@Inject
	WorkoutProgramMapper wpMapper;
	@Inject
	GymMachineMapper gmMapper;
	
	public CustomerDTO createCustomer(CustomerDTO cDTO) {
		Customer customer = new Customer();
		customer.setId(cDTO.getId());
		customer.setPersonalTrainerId(cDTO.getPersonalTrainerId());
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
	
	public List<CustomerDTO> searchCustomer(String name){
		ArrayList<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
		for (Customer c : cDao.findByName(name))
			customerDTOList.add(cMapper.toDTO(c));
		return customerDTOList;
	}
	
	public GymMachineDTO createGymMachine(GymMachineDTO gmDTO) {
		GymMachine gm = new GymMachine();
		gm.setId(gmDTO.getId());
		gm.setName(gmDTO.getName());
		gm.setDescription("Niente descrizione");
		machineDao.save(gm);
		return gmMapper.toDTO(gm);
	}
	
	public ExerciseDTO createExercise(ExerciseDTO exDTO) {
		Exercise ex = new Exercise();
		ex.setId(exDTO.getId());
		ex.setName(exDTO.getName());
		ex.setDifficultyLevel(exDTO.getDifficultyLevel());
		ex.setDescription(exDTO.getDescription());

		if(exDTO.getMachineId() != -1)
			ex.setMachine(machineDao.findById(exDTO.getMachineId()));
		else
			ex.setMachine(null);
		exDao.save(ex);
		return exMapper.toDTO(ex);
	}
	
	public WorkoutProgramDTO createWorkoutProgram(WorkoutProgramDTO wpDTO) {
		// NON prende il tipo dell'allenamento
		WorkoutProgram wp = new WorkoutProgram();
		wp.setId(wpDTO.getId());
		wp.setName(wpDTO.getName());
		wp.setDescription(wpDTO.getDescription());
		wp.setDifficultyLevel(wpDTO.getDifficultyLevel());
		wp.setEstimatedDuration(wpDTO.getEstimatedDuration());
		wp.setWorkoutProgramType(wpDTO.getWorkoutProgramType());
		wp.setExerciseList(null);
		wpDao.save(wp);
		return wpMapper.toDTO(wp);
	}
	
	public PersonalTrainerDTO searchPersonalTrainerByEmail(String email) {
		PersonalTrainer pt = ptDao.findByEmail(email);
		return ptMapper.toDTO(pt);
	}
	
	public List<ExerciseDTO> searchExercise(String name) {
		ArrayList<ExerciseDTO> exerciseDTOList = new ArrayList<ExerciseDTO>();
		for(Exercise ex : exDao.findByName(name))
			exerciseDTOList.add(exMapper.toDTO(ex));
		return exerciseDTOList;
	}
	
	public List<WorkoutProgramDTO> searchWorkoutProgram(String wpName) {
		ArrayList<WorkoutProgramDTO> wpDTOList = new ArrayList<WorkoutProgramDTO>();
		for(WorkoutProgram wp : wpDao.findByContainName(wpName))
			wpDTOList.add(wpMapper.toDTO(wp));
		return wpDTOList;
	}
	
	public CustomerDTO assignWorkoutProgramToCustomer(Long custId, WorkoutProgramDTO wpDTO) {
		Customer c = cDao.findById(custId);
		WorkoutProgram wp = wpDao.findById(wpDTO.getId());
		List<WorkoutProgram> wpList = c.getWorkoutProgramList();
		wpList.add(wp);
		c.setWorkoutProgramList(wpList);
		cDao.update(c);
		return cMapper.toDTO(c);
	}
	
	public WorkoutProgramDTO addExerciseToWorkoutProgram(Long wpId, ExerciseDTO exDTO) {
		WorkoutProgram wp = wpDao.findById(wpId);
		Exercise ex = exDao.findById(exDTO.getId());
		List<Exercise> exList = wp.getExerciseList();
		if(!exList.contains(ex)) {
			exList.add(ex);
			wp.setExerciseList(exList);
			wpDao.update(wp);
		}
		return wpMapper.toDTO(wp);
	}
	
	public List<GymMachineDTO> listGymMachines(){
		ArrayList<GymMachineDTO> gymMachineDTOList = new ArrayList<GymMachineDTO>();
		for(GymMachine gm : machineDao.findAll())
			gymMachineDTOList.add(gmMapper.toDTO(gm));
		return gymMachineDTOList;
	}
	
	public List<ExerciseDTO> listExercises(){
		ArrayList<ExerciseDTO> exerciseDTOList = new ArrayList<ExerciseDTO>();
		for(Exercise ex : exDao.findAll())
			exerciseDTOList.add(exMapper.toDTO(ex));
		return exerciseDTOList;
	}
	
	public List<WorkoutProgramDTO> listWorkoutProgram(){
		ArrayList<WorkoutProgramDTO> workoutProgramDTOList = new ArrayList<WorkoutProgramDTO>();
		for(WorkoutProgram wp : wpDao.findAll())
			workoutProgramDTOList.add(wpMapper.toDTO(wp));
		return workoutProgramDTOList;
	}
	
	
}
