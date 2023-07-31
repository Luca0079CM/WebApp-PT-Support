package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import javax.persistence.EntityManagerFactory;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;

public class CustomerController {
	// Qui servirebbe @Inject
	CustomerDao cDao;
	
	public CustomerController(EntityManagerFactory emf) {
		cDao = new CustomerDao(emf);
	}
	
	public void detatchWorkoutProgram(Long userId, Long wpId) {
		Customer customer = cDao.findById(userId);
		
		boolean found = false;
		for(WorkoutProgram wp : customer.getWorkoutProgramList()) {
			if(wp.getId() == wpId) {
				found = true;
				customer.getWorkoutProgramList().remove(wp);
				cDao.update(customer);
				break;
			}
		}
		
		if (found)
			System.out.println("Workout Program eliminato con successo");
		else
			System.out.println("Il Workout Program non è presente nella lista dell'utente selezionato");
	}
	
}
