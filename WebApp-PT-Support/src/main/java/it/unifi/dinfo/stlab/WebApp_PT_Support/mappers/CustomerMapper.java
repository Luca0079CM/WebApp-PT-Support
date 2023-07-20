package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;

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
}
