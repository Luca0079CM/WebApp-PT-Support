package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import jakarta.inject.Inject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutSessionDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutSessionDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.CustomerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.PersonalTrainerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.WorkoutProgramMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.WorkoutSessionMapper;

public class CustomerController {
	
	@Inject
	CustomerDao cDao;
	@Inject
	PersonalTrainerDao ptDao;
	@Inject
	WorkoutSessionDao wsDao;
	@Inject
	CustomerMapper cMapper;
	@Inject
	WorkoutProgramMapper wpMapper;
	@Inject
	PersonalTrainerMapper ptMapper;
	@Inject
	WorkoutSessionMapper wsMapper;
	
	
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
	
	public WorkoutSessionDTO saveWorkoutSession(WorkoutSessionDTO wsDTO) {
		WorkoutSession ws = new WorkoutSession();
		ws.setId(wsDTO.getId());
		ws.setStartTime(Instant.parse(wsDTO.getStartTime()));
		ws.setEndTime(Instant.parse(wsDTO.getEndTime()));
		ws.setSessionData(wsDTO.getSessionData());
		wsDao.save(ws);
		return wsMapper.toDTO(ws);
	}
	
	public List<WorkoutSessionDTO> listWorkoutSessionOfCustomer(Long custId){
		List<WorkoutSessionDTO> wsDTOList = new ArrayList<WorkoutSessionDTO>();
		for(WorkoutSession ws : wsDao.findByCustomerId(custId)) {
			wsDTOList.add(wsMapper.toDTO(ws));
		}
		return wsDTOList;
	}
	
}
