package it.unifi.dinfo.stlab.WebApp_PT_Support.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.unifi.dinfo.stlab.WebApp_PT_Support.controllers.PersonalTrainerController;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.CustomerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.ExerciseMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.WorkoutProgramMapper;

public class PersonalTrainerControllerTest extends JPATest{

	private PersonalTrainerController ptController;
	private Customer customer;
	private PersonalTrainer personalTrainer;
	private WorkoutProgramDao workoutProgramDao;
	private CustomerDao customerDao;
	private PersonalTrainerDao personalTrainerDao;
	private CustomerMapper customerMapper;
	private ExerciseMapper exerciseMapper;
	private WorkoutProgramMapper workoutProgramMapper;
	
	@Override
	protected void init() throws InitializationError {
		ptController = new PersonalTrainerController();
		
		personalTrainer = new PersonalTrainer();
		personalTrainer.setId(123L);
		personalTrainer.setName("Carlo");
		personalTrainer.setSurname("Ceccherelli");
		personalTrainer.setDateOfBirth(1997, 1, 1);
		personalTrainer.setPassword("password");
		personalTrainer.setCustomersList(new ArrayList<Customer>());
		
		customer = new Customer();
		customer.setId(123L);
		customer.setName("Luca");
		customer.setSurname("Leuter");
		customer.setPassword("password");
		customer.setDateOfBirth(1997, 3, 8);
		customer.setPersonalTrainer(personalTrainer);
		
		em.persist(personalTrainer);
		em.persist(customer);
		
		personalTrainerDao = mock(PersonalTrainerDao.class);
		customerDao = mock(CustomerDao.class);
		customerMapper = mock(CustomerMapper.class);
		exerciseMapper = mock(ExerciseMapper.class);
		workoutProgramMapper = mock(WorkoutProgramMapper.class);
		try {
			FieldUtils.writeField(personalTrainerDao, "em", em, true);
			FieldUtils.writeField(customerDao, "em", em, true);
			FieldUtils.writeField(ptController, "ptDao", personalTrainerDao, true);
			FieldUtils.writeField(ptController, "cDao", customerDao, true);
			FieldUtils.writeField(ptController,"cMapper", customerMapper, true);
			FieldUtils.writeField(ptController,"exMapper", exerciseMapper, true);
			FieldUtils.writeField(ptController,"wpMapper", workoutProgramMapper, true);
		}
		catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testCreateCustomer() {
		when(personalTrainerDao.findById(personalTrainer.getId())).thenReturn(personalTrainer);
		// DEVE ESSERE INDIPENDENTE DAI DAO
		CustomerMapper cMapper = new CustomerMapper();
		LocalDate dateOfBirth = LocalDate.of(1997, 3, 8);
		Customer c1 = cMapper.toEntity(
				ptController.createCustomer(123L, 79L, "Luca", "Leuter", "luca0079@hotmail.it", "password", dateOfBirth));
////		List<Customer> cList = customerDao.findAll();
////		for(Customer c: cList)
////			System.out.println(c.getName());
//		Customer c2 = em.createQuery("from Customer where id=:id", Customer.class).setParameter("id", 79L).getSingleResult();
//		Customer c3 = customerDao.findById(79L);
//
//		System.out.println(c2.getName());
//		System.out.println(c3.getName());
//		Assertions.assertEquals(c1, c3);
		
	}
}
