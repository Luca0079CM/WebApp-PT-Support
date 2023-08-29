package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;

public class CustomerDaoTest extends JPATest {
	private CustomerDao customerDao;
	private Customer customer;
	
	//se voglio popolare tabelle lo faccio in questo metodo
	@Override
	protected void init() throws InitializationError {
		customer = new Customer();
		customer.setId(new Random().nextLong());
		customer.setName("Carlo");
		customer.setSurname("CeccherelliCust");
		customer.setEmail("example@cust.com");
		customer.setDateOfBirth(1998, 10, 13);
		customer.setPassword("pw");
		WorkoutProgram workoutProgram = new WorkoutProgram();
		workoutProgram.setId(new Random().nextLong());
		workoutProgram.setDifficultyLevel(5);
		workoutProgram.setEstimatedDuration(60);
		workoutProgram.setWorkoutProgramType(WorkoutProgramType.CARDIO);
		em.persist(workoutProgram);
		List<WorkoutProgram> wpList = new ArrayList<WorkoutProgram>();
		wpList.add(workoutProgram);
		customer.setWorkoutProgramList(wpList);
		PersonalTrainer personalTrainer = new PersonalTrainer();
		personalTrainer.setId(new Random().nextLong());
		personalTrainer.setName("Fabio");
		personalTrainer.setSurname("Ceccherelli");
		personalTrainer.setEmail("example@cust.com");
		personalTrainer.setDateOfBirth(1992, 10, 13);
		personalTrainer.setPassword("pw");
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer);
		personalTrainer.setCustomersList(customerList);
		em.persist(personalTrainer);
		em.persist(customer);

		customerDao = new CustomerDao();
		try {
			FieldUtils.writeField(customerDao, "em", em, true);
		}
		catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testSave() {
		Customer newCustomer = new Customer();
		newCustomer.setId(new Random().nextLong());
		newCustomer.setName("Carlo");
		newCustomer.setSurname("Ceccherelli");
		newCustomer.setEmail("example@pt.com");
		newCustomer.setDateOfBirth(1998, 10, 13);
		newCustomer.setPassword("pw");
		customerDao.save(newCustomer);
		Customer retrievedCustomer = em.createQuery("from Customer where id=:id", Customer.class)
				.setParameter("id", newCustomer.getId()).getSingleResult();

		Assertions.assertEquals(newCustomer, retrievedCustomer);
	}
	
	@Test
	public void testFindById() {
		Customer result = customerDao.findById(customer.getId());

		Assertions.assertEquals(result, customer);
		Assertions.assertEquals(result.getId(), customer.getId());
		Assertions.assertEquals(result.getName(), customer.getName());
		Assertions.assertEquals(result.getSurname(), customer.getSurname());
		Assertions.assertEquals(result.getEmail(), customer.getEmail());
		Assertions.assertEquals(result.getPassword(), customer.getPassword());
		Assertions.assertEquals(result.getPersonalTrainer(), customer.getPersonalTrainer());
		Assertions.assertEquals(result.getWorkoutProgramList().size(), customer.getWorkoutProgramList().size());
		Assertions.assertTrue(result.getWorkoutProgramList().containsAll(customer.getWorkoutProgramList()));
	}

	@Test
	public void testFindAll() {
		List<Customer> retrievedList = em.createQuery("from Customer", Customer.class).getResultList();
		List<Customer> resultList = customerDao.findAll();

		Assertions.assertEquals(retrievedList.size(), resultList.size());
		Assertions.assertTrue(retrievedList.containsAll(resultList));
	}

	@Test
	public void testUpdate() {
		customer.setPassword("newpw");
		customerDao.update(customer);
		Customer result = em.createQuery("from Customer where id=:id", Customer.class).setParameter("id", customer.getId()).getSingleResult();
		Assertions.assertEquals(customer.getPassword(), result.getPassword());
	}

}
