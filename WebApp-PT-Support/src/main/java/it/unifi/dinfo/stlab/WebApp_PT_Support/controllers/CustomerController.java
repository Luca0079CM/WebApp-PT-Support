package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.CustomerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.PersonalTrainerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.WorkoutProgramMapper;

public class CustomerController {
	
	@Inject
	CustomerDao cDao;
	@Inject
	PersonalTrainerDao ptDao;
	@Inject
	CustomerMapper cMapper;
	@Inject
	WorkoutProgramMapper wpMapper;
	@Inject
	PersonalTrainerMapper ptMapper;
	
	public WorkoutProgramDTO detachWorkoutProgram(Long userId, Long wpId) {
		Customer customer = cDao.findById(userId);
		WorkoutProgramDTO wpFound = null;
		for(WorkoutProgram wp : customer.getWorkoutProgramList()) {
			if(wp.getId() == wpId) {
				WorkoutProgramMapper wpMapper = new WorkoutProgramMapper();
				wpFound = wpMapper.toDTO(wp);
				customer.getWorkoutProgramList().remove(wp);
				cDao.update(customer);
				break;
			}
		}
		
		if (wpFound != null)
			System.out.println("Workout Program eliminato con successo");
		else
			System.out.println("Il Workout Program non è presente nella lista dell'utente selezionato");
		return wpFound;
	}
	
	public CustomerDTO searchCustomerByEmail(String email) {
		Customer c = cDao.findByEmail(email);
		return cMapper.toDTO(c);
	}
	
	public List<WorkoutProgramDTO> listWorkoutProgram(Long customerId){
		Customer c = cDao.findById(customerId);
		ArrayList<WorkoutProgramDTO> workoutProgramDTOList = new ArrayList<WorkoutProgramDTO>();
		for(WorkoutProgram wp : c.getWorkoutProgramList())
			workoutProgramDTOList.add(wpMapper.toDTO(wp));
		return workoutProgramDTOList;
	}
	
	public List<PersonalTrainerDTO> listPersonalTrainer(){
		ArrayList<PersonalTrainerDTO> ptDTOList = new ArrayList<PersonalTrainerDTO>();
		for(PersonalTrainer pt : ptDao.findAll()) {
			ptDTOList.add(ptMapper.toDTO(pt));
		}
		return ptDTOList;
	}
	
	public CustomerDTO changePersonalTrainer(Long customerId, Long ptId) {
		Customer c = cDao.findById(customerId);
		c.setPersonalTrainer(ptDao.findById(ptId));
		cDao.update(c);
		return cMapper.toDTO(c);
	}
	
}
