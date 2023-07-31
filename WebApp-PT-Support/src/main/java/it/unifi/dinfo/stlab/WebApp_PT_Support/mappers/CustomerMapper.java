package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import java.util.List;

public class CustomerMapper {
	
	public CustomerDTO generateCustomerTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setSurname(customer.getSurname());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setPersonalTrainer(customer.getPersonalTrainer().getName() + " "
				+ customer.getPersonalTrainer().getSurname());
		return customerDTO;
	}
	
	public Customer generateCustomerFromTO(CustomerDTO customerDTO) {
		Customer c = new Customer();
		PersonalTrainerDao ptDao = new PersonalTrainerDao();
		
		c.setId(customerDTO.getId());
		c.setName(customerDTO.getName());
		c.setSurname(customerDTO.getSurname());
		c.setEmail(customerDTO.getEmail());
		c.setDateOfBirth(customerDTO.getDateOfBirth());
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
