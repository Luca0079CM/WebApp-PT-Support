package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import jakarta.inject.Inject;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.WorkoutProgramMapper;

public class CustomerController {
	
	@Inject
	CustomerDao cDao;
	
	public WorkoutProgramDTO detachWorkoutProgram(Long userId, Long wpId) {
		Customer customer = cDao.findById(userId);
		WorkoutProgramDTO wpFound = null;
		for(WorkoutProgram wp : customer.getWorkoutProgramList()) {
			if(wp.getId() == wpId) {
				WorkoutProgramMapper wpMapper = new WorkoutProgramMapper();
				wpFound = wpMapper.generateWorkoutProgramTO(wp);
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
	
}
