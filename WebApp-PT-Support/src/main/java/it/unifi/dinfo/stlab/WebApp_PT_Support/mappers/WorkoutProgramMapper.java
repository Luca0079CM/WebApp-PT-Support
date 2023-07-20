package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;

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
}
