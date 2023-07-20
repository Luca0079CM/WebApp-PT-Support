package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;

public class WorkoutProgramDaoTest extends JPATest {
	
	private WorkoutProgramDao workoutProgramDao;
	private WorkoutProgram workoutProgram;
	
	//se voglio popolare tabelle lo faccio in questo metodo
	@Override
	protected void init() throws InitializationError {
		workoutProgram = new WorkoutProgram();
		workoutProgram.setId(new Random().nextLong());
		workoutProgram.setDifficultyLevel(5);
		workoutProgram.setEstimatedDuration(60);
		workoutProgram.setWorkoutProgramType(WorkoutProgramType.CARDIO);
		Exercise exercise1 = new Exercise();
		exercise1.setId(new Random().nextLong());
		exercise1.setDifficultyLevel(1);
		exercise1.setDescription("Fa bruciare calorie");
		em.persist(exercise1);
		Exercise exercise2 = new Exercise();
		exercise2.setId(new Random().nextLong());
		exercise2.setDifficultyLevel(2);
		exercise2.setDescription("Fa bruciare calorie");
		em.persist(exercise2);
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		exerciseList.add(exercise1);
		exerciseList.add(exercise2);
		workoutProgram.setExerciseList(exerciseList);
		em.persist(workoutProgram);

		workoutProgramDao = new WorkoutProgramDao();
		try {
			FieldUtils.writeField(workoutProgramDao, "em", em, true);
		}
		catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testSave() {
		WorkoutProgram newWorkoutProgram = new WorkoutProgram();
		newWorkoutProgram.setId(Long.valueOf(41));
		newWorkoutProgram.setDifficultyLevel(5);
		newWorkoutProgram.setEstimatedDuration(60);
		newWorkoutProgram.setWorkoutProgramType(WorkoutProgramType.CARDIO);
		workoutProgramDao.save(newWorkoutProgram);
		WorkoutProgram retrievedWorkoutProgram = em.createQuery("from WorkoutProgram where id=:id", WorkoutProgram.class)
				.setParameter("id", newWorkoutProgram.getId()).getSingleResult();

		Assertions.assertEquals(newWorkoutProgram, retrievedWorkoutProgram);
	}
	
	@Test
	public void testFindById() {
		WorkoutProgram result = workoutProgramDao.findById(workoutProgram.getId());

		Assertions.assertEquals(result, workoutProgram);
		Assertions.assertEquals(result.getId(), workoutProgram.getId());
		Assertions.assertEquals(result.getDifficultyLevel(), workoutProgram.getDifficultyLevel());
		Assertions.assertEquals(result.getEstimatedDuration(), workoutProgram.getEstimatedDuration());
		Assertions.assertEquals(result.getWorkoutProgramType(), workoutProgram.getWorkoutProgramType());
		Assertions.assertEquals(result.getExerciseList().size(), workoutProgram.getExerciseList().size());
		Assertions.assertTrue(result.getExerciseList().containsAll(workoutProgram.getExerciseList()));
	}

	@Test
	public void testFindAll() {
		List<WorkoutProgram> retrievedList = em.createQuery("from WorkoutProgram", WorkoutProgram.class).getResultList();
		List<WorkoutProgram> resultList = workoutProgramDao.findAll();

		Assertions.assertEquals(retrievedList.size(), resultList.size());
		Assertions.assertTrue(retrievedList.containsAll(resultList));
	}

	@Test
	public void testUpdate() {
		workoutProgram.setDifficultyLevel(6);
		workoutProgramDao.update(workoutProgram);
		WorkoutProgram result = em.createQuery("from WorkoutProgram where id=:id", WorkoutProgram.class).setParameter("id", workoutProgram.getId()).getSingleResult();
		Assertions.assertEquals(workoutProgram.getDifficultyLevel(), result.getDifficultyLevel());
	}

}
