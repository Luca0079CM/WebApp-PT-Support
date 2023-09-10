package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.ExerciseDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.CustomerMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.ExerciseMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.mappers.WorkoutProgramMapper;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.ExerciseDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;

public class PersonalTrainerControllerTest extends JPATest{

	private PersonalTrainerController ptController;
	private Customer customer;
	private PersonalTrainer personalTrainer;
	private Exercise exercise;
	private WorkoutProgram workoutProgram;
	private WorkoutProgramDao workoutProgramDao;
	private CustomerDao customerDao;
	private PersonalTrainerDao personalTrainerDao;
	private ExerciseDao exerciseDao;
	private CustomerMapper customerMapper;
	private ExerciseMapper exerciseMapper;
	private WorkoutProgramMapper workoutProgramMapper;
	
	@Override
	protected void init() throws InitializationError {
		ptController = new PersonalTrainerController();
		
		personalTrainer = new PersonalTrainer();
		personalTrainer.setId(new Random().nextLong());
		personalTrainer.setName("Carlo");
		personalTrainer.setSurname("Ceccherelli");
		personalTrainer.setDateOfBirth(1997, 1, 1);
		personalTrainer.setPassword("password");
		personalTrainer.setCustomersList(new ArrayList<Customer>());
		
		customer = new Customer();
		customer.setId(new Random().nextLong());
		customer.setName("Luca");
		customer.setSurname("Leuter");
		customer.setPassword("password");
		customer.setDateOfBirth(1997, 3, 8);
		customer.setPersonalTrainer(personalTrainer);
		customer.setWorkoutProgramList(new ArrayList<WorkoutProgram>());
		personalTrainer.addCustomer(customer);
		
		exercise = new Exercise();
		exercise.setId(new Random().nextLong());
		exercise.setDifficultyLevel(1);
		exercise.setDescription("Fa bruciare calorie");
		exercise.setMachine(null);
		
		workoutProgram = new WorkoutProgram();
		workoutProgram.setId(new Random().nextLong());
		workoutProgram.setDifficultyLevel(1);
		workoutProgram.setEstimatedDuration(5);
		workoutProgram.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
		workoutProgram.setExerciseList(new ArrayList<Exercise>());
		
		em.persist(personalTrainer);
		em.persist(customer);
		em.persist(exercise);
		
		personalTrainerDao = mock(PersonalTrainerDao.class);
		customerDao = mock(CustomerDao.class);
		exerciseDao = mock(ExerciseDao.class);
		workoutProgramDao = mock(WorkoutProgramDao.class);
		
		customerMapper = mock(CustomerMapper.class);
		exerciseMapper = mock(ExerciseMapper.class);
		workoutProgramMapper = mock(WorkoutProgramMapper.class);
		try {
			FieldUtils.writeField(personalTrainerDao, "em", em, true);
			FieldUtils.writeField(customerDao, "em", em, true);
			FieldUtils.writeField(exerciseDao, "em", em, true);
			FieldUtils.writeField(workoutProgramDao, "em", em, true);
			FieldUtils.writeField(ptController, "ptDao", personalTrainerDao, true);
			FieldUtils.writeField(ptController, "cDao", customerDao, true);
			FieldUtils.writeField(ptController, "exDao", exerciseDao, true);
			FieldUtils.writeField(ptController, "wpDao", workoutProgramDao, true);
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
		CustomerDTO cDTO = new CustomerMapper().toDTO(customer);
		when(customerMapper.toDTO(customer)).thenReturn(cDTO);
		CustomerDTO cDTO2 = ptController.createCustomer(cDTO);
		Assertions.assertEquals(cDTO.getId(), cDTO2.getId());
	}
	
	@Test
	public void testDisableCustomer() {
		when(customerDao.findById(customer.getId())).thenReturn(customer);
		Assertions.assertEquals(personalTrainer.getId(), customer.getPersonalTrainer().getId());
		ptController.disableCustomer(customer.getId());
		Assertions.assertEquals(null, customer.getPersonalTrainer());
		Assertions.assertEquals(null, customer.getWorkoutProgramList());
	}
	
	@Test
	public void testFindCustomersByPTId() {
		when(personalTrainerDao.findById(personalTrainer.getId())).thenReturn(personalTrainer);
		ArrayList<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
		CustomerDTO cDTO = new CustomerMapper().toDTO(customer);
		customerDTOList.add(cDTO);
		when(customerMapper.toDTO(customer)).thenReturn(cDTO);
		List<CustomerDTO> customerDTOList2 = ptController.findCustomersByPTId(personalTrainer.getId());
		Assertions.assertEquals(customerDTOList.size(), customerDTOList2.size());
		Assertions.assertEquals(customerDTOList.get(0), customerDTOList2.get(0));
	}
	
	@Test
	public void testCreateExercise() {
		ExerciseDTO exDTO = new ExerciseMapper().toDTO(exercise);
		when(exerciseMapper.toDTO(exercise)).thenReturn(exDTO);
		ExerciseDTO exDTO2 = ptController.createExercise(exDTO);
		Assertions.assertEquals(exercise.getId(), exDTO2.getId());
	}
	
	@Test
	public void testCreateWorkoutProgram() {
		WorkoutProgramDTO wpDTO = new WorkoutProgramMapper().toDTO(workoutProgram);
		when(workoutProgramMapper.toDTO(workoutProgram)).thenReturn(wpDTO);
		WorkoutProgramDTO wpDTO2 = ptController.createWorkoutProgram(wpDTO);
		Assertions.assertEquals(wpDTO.getId(), wpDTO2.getId());
	}
	
	@Test
	public void testSearchExercise() {
		ExerciseDTO exDTO = new ExerciseMapper().toDTO(exercise);
		when(exerciseDao.findById(exercise.getId())).thenReturn(exercise);
		when(exerciseMapper.toDTO(exercise)).thenReturn(exDTO);
		ExerciseDTO exDTO2 = ptController.searchExercise(exercise.getId());
		Assertions.assertEquals(exercise.getId(), exDTO2.getId());
	}
	
	@Test
	public void testSearchWorkoutProgram() {
		WorkoutProgramDTO wpDTO = new WorkoutProgramMapper().toDTO(workoutProgram);
		when(workoutProgramDao.findById(workoutProgram.getId())).thenReturn(workoutProgram);
		when(workoutProgramMapper.toDTO(workoutProgram)).thenReturn(wpDTO);
		WorkoutProgramDTO wpDTO2 = ptController.searchWorkoutProgram(workoutProgram.getId());
		Assertions.assertEquals(wpDTO.getId(), wpDTO2.getId());
	}
	
	@Test
	public void testAssignWorkoutProgramToCustomer() {
		when(customerDao.findById(customer.getId())).thenReturn(customer);
		when(workoutProgramDao.findById(workoutProgram.getId())).thenReturn(workoutProgram);
		ptController.assignWorkoutProgramToCustomer(workoutProgram.getId(), customer.getId()); //penso sia wpMapper.toDTO(workoutProgram)
		Assertions.assertEquals(1, customer.getWorkoutProgramList().size());
		Assertions.assertEquals(workoutProgram, customer.getWorkoutProgramList().get(0));
	}
	
	@Test
	public void testAddExerciseToWorkoutProgram() {
		when(exerciseDao.findById(exercise.getId())).thenReturn(exercise);
		when(workoutProgramDao.findById(workoutProgram.getId())).thenReturn(workoutProgram);
		ptController.addExerciseToWorkoutProgram(exercise.getId(), workoutProgram.getId()); //penso sia exMapper.toDTO(exercise)
		Assertions.assertEquals(1, workoutProgram.getExerciseList().size());
		Assertions.assertEquals(exercise, workoutProgram.getExerciseList().get(0));
	}
}
