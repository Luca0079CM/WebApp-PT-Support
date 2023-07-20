package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.ExerciseDTO;

public class ExerciseMapper {
	
	public ExerciseDTO generateExerciseTO(Exercise ex) {
		ExerciseDTO exDTO = new ExerciseDTO();
		exDTO.setId(ex.getId());
		exDTO.setName(ex.getName());
		exDTO.setDifficultyLevel(ex.getDifficultyLevel());
		if(ex.getMachine() != null)
			exDTO.setMachine(ex.getMachine().getName());
		else
			exDTO.setMachine("Nessun macchinario per l'esercizio");
		return exDTO;
	}
}
