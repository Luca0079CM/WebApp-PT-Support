package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;

public class ExerciseDaoTest extends JPATest {

	private ExerciseDao exerciseDao;
	private Exercise exercise;
	private GymMachine gymMachine;

	//se voglio popolare tabelle lo faccio in questo metodo
	@Override
	protected void init() throws InitializationError {
		exercise = new Exercise();
		exercise.setId(new Random().nextLong());
		exercise.setDifficultyLevel(1);
		exercise.setDescription("Fa bruciare calorie");
		gymMachine = new GymMachine();
		gymMachine.setId(new Random().nextLong());
		gymMachine.setName("Lat Machine");
		gymMachine.setDescription("Serve per allenarsi");
		exercise.setMachine(gymMachine);
		em.persist(gymMachine);
		em.persist(exercise);

		exerciseDao = new ExerciseDao();
		try {
			FieldUtils.writeField(exerciseDao, "em", em, true);
		}
		catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}

	@Test
	public void testSave() {
		Exercise newExercise = new Exercise();
		newExercise.setId(Long.valueOf(11));
		newExercise.setDifficultyLevel(1);
		newExercise.setDescription("Fa bruciare calorie");
		newExercise.setMachine(gymMachine);
		exerciseDao.save(newExercise);
		Exercise retrievedExercise = em.createQuery("from Exercise where id=:id", Exercise.class)
				.setParameter("id", newExercise.getId()).getSingleResult();

		Assertions.assertEquals(newExercise, retrievedExercise);
	}

	@Test
	public void testFindById() {
		Exercise result = exerciseDao.findById(exercise.getId());

		Assertions.assertEquals(result, exercise);
		Assertions.assertEquals(result.getId(), exercise.getId());
		Assertions.assertEquals(result.getDifficultyLevel(), exercise.getDifficultyLevel());
		Assertions.assertEquals(result.getDescription(), exercise.getDescription());
		Assertions.assertEquals(result.getMachine(), exercise.getMachine());
	}

	@Test
	public void testFindAll() {
		List<Exercise> retrievedList = em.createQuery("from Exercise", Exercise.class).getResultList();
		List<Exercise> resultList = exerciseDao.findAll();

		Assertions.assertEquals(retrievedList.size(), resultList.size());
		Assertions.assertTrue(retrievedList.containsAll(resultList));
	}

	@Test
	public void testUpdate() {
		exercise.setDifficultyLevel(2);
		exerciseDao.update(exercise);
		Exercise result = em.createQuery("from Exercise where id=:id", Exercise.class).setParameter("id", exercise.getId()).getSingleResult();
		Assertions.assertEquals(exercise.getDifficultyLevel(), result.getDifficultyLevel());
	}

}
