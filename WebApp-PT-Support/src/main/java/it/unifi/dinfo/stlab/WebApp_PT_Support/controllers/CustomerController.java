package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.ExerciseDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.CustomerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.ExerciseMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.PersonalTrainerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.WorkoutProgramMapper;

public class CustomerController {
	
	@Inject
	CustomerDao cDao;
	@Inject
	PersonalTrainerDao ptDao;
	@Inject
	WorkoutProgramDao wpDao;
	@Inject
	CustomerMapper cMapper;
	@Inject
	WorkoutProgramMapper wpMapper;
	@Inject
	PersonalTrainerMapper ptMapper;
	@Inject
	ExerciseMapper exMapper;
	
	
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
	
	public List<ExerciseDTO> searchExerciseOfWorkoutProgram(Long wpId) {
		WorkoutProgram wp = wpDao.findById(wpId);
		List<ExerciseDTO> exDTOList = new ArrayList<ExerciseDTO>();
		for(Exercise e : wp.getExerciseList()) {
			exDTOList.add(exMapper.toDTO(e));
		}
		return exDTOList;
	}
	
}
