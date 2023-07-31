package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.ExerciseDao;


public class WorkoutProgramMapper {
	
	public WorkoutProgramDTO generateWorkoutProgramTO(WorkoutProgram wp) {
		WorkoutProgramDTO wpDTO = new WorkoutProgramDTO();
		wpDTO.setId(wp.getId());
		wpDTO.setDifficultyLevel(wp.getDifficultyLevel());
		wpDTO.setEstimatedDuration(wp.getEstimatedDuration());
		wpDTO.setWorkoutProgramType(wp.getWorkoutProgramType());
		List<Exercise> exList = wp.getExerciseList();
		String[] exerciseListTO = new String[wp.getExerciseList().size()];
		for(int i = 0; i < exerciseListTO.length; i++)
			exerciseListTO[i] = exList.get(i).getName();
		wpDTO.setExerciseList(exerciseListTO);
		return wpDTO;
	}
	
	public WorkoutProgram generateWorkoutProgramFromTO(WorkoutProgramDTO wpDTO) {
		WorkoutProgram wp = new WorkoutProgram();
		ExerciseDao exDao = new ExerciseDao();
		
		wp.setId(wpDTO.getId());
		wp.setDifficultyLevel(wpDTO.getDifficultyLevel());
		wp.setEstimatedDuration(wpDTO.getEstimatedDuration());
		wp.setWorkoutProgramType(wpDTO.getWorkoutProgramType());
		List<Exercise> allExList = exDao.findAll();
		for(String exString : wpDTO.getExerciseList()) {
			for(Exercise ex : allExList) {
				if(ex.getName().equals(exString)) {
					wp.addExercise(ex);
				}
			}
		}
		return wp;
	}
}