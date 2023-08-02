package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;

public class PersonalTrainerDaoTest extends JPATest {
	
	private PersonalTrainerDao personalTrainerDao;
	private PersonalTrainer personalTrainer;
	
	//se voglio popolare tabelle lo faccio in questo metodo
	@Override
	protected void init() throws InitializationError {
		personalTrainer = new PersonalTrainer();
		personalTrainer.setId(new Random().nextLong());
		personalTrainer.setName("Carlo");
		personalTrainer.setSurname("Ceccherelli");
		personalTrainer.setEmail("example@pt.com");
		personalTrainer.setDateOfBirth(1998, 10, 13);
		personalTrainer.setPassword("pw");
		WorkoutProgram workoutProgram = new WorkoutProgram();
		workoutProgram.setId(new Random().nextLong());
		workoutProgram.setDifficultyLevel(5);
		workoutProgram.setEstimatedDuration(60);
		workoutProgram.setWorkoutProgramType(WorkoutProgramType.CARDIO);
		em.persist(workoutProgram);
		List<WorkoutProgram> wpList = new ArrayList<WorkoutProgram>();
		wpList.add(workoutProgram);
		personalTrainer.setWorkoutProgramList(wpList);
		Customer customer1 = new Customer();
		customer1.setId(new Random().nextLong());
		customer1.setName("Fabio");
		customer1.setSurname("Ceccherelli");
		customer1.setEmail("example@cust.com");
		customer1.setDateOfBirth(1992, 10, 13);
		customer1.setPassword("pw");
		customer1.setPersonalTrainer(personalTrainer);
		em.persist(customer1);
		Customer customer2 = new Customer();
		customer2.setId(new Random().nextLong());
		customer2.setName("Mario");
		customer2.setSurname("Ceccherelli");
		customer2.setEmail("example@cust.com");
		customer2.setDateOfBirth(1990, 10, 13);
		customer2.setPassword("pw");
		customer2.setPersonalTrainer(personalTrainer);
		em.persist(customer2);
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer1);
		customerList.add(customer2);
		personalTrainer.setCustomersList(customerList);
		em.persist(personalTrainer);

		personalTrainerDao = new PersonalTrainerDao();
		try {
			FieldUtils.writeField(personalTrainerDao, "em", em, true);
		}
		catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testSave() {
		PersonalTrainer newPersonalTrainer = new PersonalTrainer();
		newPersonalTrainer.setId(new Random().nextLong());
		newPersonalTrainer.setName("Carlo");
		newPersonalTrainer.setSurname("Ceccherelli");
		newPersonalTrainer.setEmail("example@pt.com");
		newPersonalTrainer.setDateOfBirth(1998, 10, 13);
		newPersonalTrainer.setPassword("pw");
		personalTrainerDao.save(newPersonalTrainer);
		PersonalTrainer retrievedPersonalTrainer = em.createQuery("from PersonalTrainer where id=:id", PersonalTrainer.class)
				.setParameter("id", newPersonalTrainer.getId()).getSingleResult();

		Assertions.assertEquals(newPersonalTrainer, retrievedPersonalTrainer);
	}
	
	@Test
	public void testFindById() {
		PersonalTrainer result = personalTrainerDao.findById(personalTrainer.getId());

		Assertions.assertEquals(result, personalTrainer);
		Assertions.assertEquals(result.getId(), personalTrainer.getId());
		Assertions.assertEquals(result.getName(), personalTrainer.getName());
		Assertions.assertEquals(result.getSurname(), personalTrainer.getSurname());
		Assertions.assertEquals(result.getEmail(), personalTrainer.getEmail());
		Assertions.assertEquals(result.getPassword(), personalTrainer.getPassword());
		Assertions.assertEquals(result.getCustomersList().size(), personalTrainer.getCustomersList().size());
		Assertions.assertTrue(result.getCustomersList().containsAll(personalTrainer.getCustomersList()));
		Assertions.assertEquals(result.getWorkoutProgramList().size(), personalTrainer.getWorkoutProgramList().size());
		Assertions.assertTrue(result.getWorkoutProgramList().containsAll(personalTrainer.getWorkoutProgramList()));
	}

	@Test
	public void testFindAll() {
		List<PersonalTrainer> retrievedList = em.createQuery("from PersonalTrainer", PersonalTrainer.class).getResultList();
		List<PersonalTrainer> resultList = personalTrainerDao.findAll();

		Assertions.assertEquals(retrievedList.size(), resultList.size());
		Assertions.assertTrue(retrievedList.containsAll(resultList));
	}

	@Test
	public void testUpdate() {
		personalTrainer.setPassword("newpw");
		personalTrainerDao.update(personalTrainer);
		PersonalTrainer result = em.createQuery("from PersonalTrainer where id=:id", PersonalTrainer.class).setParameter("id", personalTrainer.getId()).getSingleResult();
		Assertions.assertEquals(personalTrainer.getPassword(), result.getPassword());
	}

}
