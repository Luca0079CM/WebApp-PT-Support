package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import jakarta.inject.Inject;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class CustomerMapper {
	@Inject
	PersonalTrainerDao ptDao;
	@Inject
	WorkoutProgramDao wpDao;
	
	public CustomerDTO toDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setPersonalTrainerId(customer.getPersonalTrainerId());
		customerDTO.setName(customer.getName());
		customerDTO.setSurname(customer.getSurname());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setDateOfBirth(customer.getDateOfBirth().toString());
		customerDTO.setPassword(customer.getPassword());
		if(customer.getPersonalTrainer() != null) {
//			customerDTO.setPersonalTrainerId(customer.getPersonalTrainer().getId());
			customerDTO.setPersonalTrainer(customer.getPersonalTrainer().getName() + " " + customer.getPersonalTrainer().getSurname());
		}

		if(customer.getWorkoutProgramList() != null) {
			String[] wpNameList = new String[customer.getWorkoutProgramList().size()];
			int i = 0;
			for(WorkoutProgram wp : customer.getWorkoutProgramList()) {
				wpNameList[i] = wp.getName();
			}
			customerDTO.setWorkoutProgramList(wpNameList);
		}
		return customerDTO;
	}
	
	public Customer toEntity(CustomerDTO customerDTO) {
		Customer c = new Customer();
		
		
		c.setId(customerDTO.getId());
		c.setName(customerDTO.getName());
		c.setSurname(customerDTO.getSurname());
		c.setEmail(customerDTO.getEmail());
		c.setDateOfBirth(LocalDate.parse(customerDTO.getDateOfBirth()));
		c.setPassword(customerDTO.getPassword());
		List<PersonalTrainer> ptList = ptDao.findAll();
		String ptString = customerDTO.getPersonalTrainer();
		if(ptString!=null) {
			for(PersonalTrainer pt : ptList) {
				if(ptString.contains(pt.getName()) && ptString.contains(pt.getSurname())) {
					c.setPersonalTrainer(pt);
					break;
				}
			}
		}
		if(customerDTO.getWorkoutProgramList() != null) {
			ArrayList<WorkoutProgram> wpList = new ArrayList<WorkoutProgram>();
			for(String wpName : customerDTO.getWorkoutProgramList()) {
				wpList.add(wpDao.findByName(wpName));
			}
			c.setWorkoutProgramList(wpList);
		}
		
		return c;
	}
}
