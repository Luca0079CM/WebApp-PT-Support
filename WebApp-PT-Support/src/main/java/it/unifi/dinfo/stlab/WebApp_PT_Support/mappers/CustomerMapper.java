package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;

import java.time.LocalDate;
import java.util.List;

public class CustomerMapper {
	
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
		List<WorkoutProgram> wpList = customer.getWorkoutProgramList();
		if(wpList != null && !wpList.isEmpty()) {
			String[] wpListTO = new String[customer.getWorkoutProgramList().size()];
			for(int i = 0; i < wpListTO.length; i++)
				wpListTO[i] = wpList.get(i).getName();
			customerDTO.setWorkoutProgramList(wpListTO);
		}
		return customerDTO;
	}
	
	public Customer toEntity(CustomerDTO customerDTO) {
		Customer c = new Customer();
		PersonalTrainerDao ptDao = new PersonalTrainerDao();
		
		c.setId(customerDTO.getId());
		c.setName(customerDTO.getName());
		c.setSurname(customerDTO.getSurname());
		c.setEmail(customerDTO.getEmail());
		c.setDateOfBirth(LocalDate.parse(customerDTO.getDateOfBirth()));
		c.setPassword("password");
		List<PersonalTrainer> ptList = ptDao.findAll();
		String ptString = customerDTO.getPersonalTrainer();
		for(PersonalTrainer pt : ptList) {
			if(ptString.contains(pt.getName()) && ptString.contains(pt.getSurname())) {
				c.setPersonalTrainer(pt);
				break;
			}
		}
		c.setWorkoutProgramList(null);
		
		return c;
	}
}
