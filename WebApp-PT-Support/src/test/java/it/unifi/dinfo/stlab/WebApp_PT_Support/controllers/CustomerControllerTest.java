package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import it.unifi.dinfo.stlab.WebApp_PT_Support.controllers.CustomerController;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.WorkoutProgramMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;

import java.util.ArrayList;

public class CustomerControllerTest extends JPATest{
	
	private CustomerController customerController;
	private Customer customer;
	private PersonalTrainer personalTrainer;
	private WorkoutProgramDao workoutProgramDao;
	private CustomerDao customerDao;
	
	@Override
	protected void init() throws InitializationError {
		customerController = new CustomerController();

		personalTrainer = new PersonalTrainer();
		personalTrainer.setId(new Random().nextLong());
		personalTrainer.setName("Carlo");
		personalTrainer.setSurname("Ceccherelli");
		personalTrainer.setDateOfBirth(1997, 1, 1);
		personalTrainer.setPassword("password");
		personalTrainer.setCustomersList(new ArrayList<Customer>());
		
		customer = new Customer();
		customer.setId(Integer.toUnsignedLong(123));
		customer.setName("Luca");
		customer.setSurname("Leuter");
		customer.setEmail("luca0079@hotmail.it");
		customer.setDateOfBirth(1997, 3, 8);
		customer.setPassword("password");
		customer.setPersonalTrainer(personalTrainer);
		List<WorkoutProgram> wpList = new ArrayList<>();
		
		WorkoutProgram wp1 = new WorkoutProgram();
		wp1.setId(1L);
		wp1.setDifficultyLevel(1);
		wp1.setEstimatedDuration(100);
		wp1.setExerciseList(new ArrayList<Exercise>());
		wp1.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
		WorkoutProgram wp2 = new WorkoutProgram();
		wp2.setId(2L);
		wp2.setDifficultyLevel(1);
		wp2.setEstimatedDuration(100);
		wp2.setExerciseList(new ArrayList<Exercise>());
		wp2.setWorkoutProgramType(WorkoutProgramType.WEIGHTS);
		Exercise ex = new Exercise();
		ex.setId(1997L);
		ex.setName("Panca Piana");
		ex.setDescription("Per pettorali, tricipiti e spalle");
		ex.setDifficultyLevel(3);
		wp1.addExercise(ex);
		wp2.addExercise(ex);
		wpList.add(wp1);
		wpList.add(wp2);
		customer.setWorkoutProgramList(wpList);
		
		em.persist(customer);
		em.persist(wp1);
		em.persist(wp2);
		em.persist(personalTrainer);
		em.persist(ex);
		
		workoutProgramDao = new WorkoutProgramDao();
		customerDao = mock(CustomerDao.class);
		try {
			FieldUtils.writeField(workoutProgramDao, "em", em, true);
			FieldUtils.writeField(customerDao, "em", em, true);
			FieldUtils.writeField(customerController, "cDao", customerDao, true);
		}
		catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testDetachWorkoutProgram() {		
		when(customerDao.findById(customer.getId())).thenReturn(customer);
		WorkoutProgramMapper wpMapper = new WorkoutProgramMapper();
		WorkoutProgramDTO detachedWorkoutProgram = customerController.detachWorkoutProgram(customer.getId(), 2L);
		WorkoutProgramDTO retrievedWorkoutProgram = wpMapper.toDTO(workoutProgramDao.findById(2L));
		
		Assertions.assertEquals(detachedWorkoutProgram.getId(), retrievedWorkoutProgram.getId());
		Assertions.assertEquals(1, customer.getWorkoutProgramList().size());
	}
}
