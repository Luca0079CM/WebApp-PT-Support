package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.ExerciseDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.GymMachineDao;
import java.util.List;

public class ExerciseMapper {
	
	public ExerciseDTO toDTO(Exercise ex) {
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
	
	public Exercise toEntity(ExerciseDTO eDTO) {
		Exercise e = new Exercise();
		GymMachineDao mDao = new GymMachineDao();
		
		e.setId(eDTO.getId());
		e.setName(eDTO.getName());
		e.setDescription("EMPTY");
		e.setMachine(null);
		e.setDifficultyLevel(eDTO.getDifficultyLevel());
		List<GymMachine> gymMachineList = mDao.findAll();
		for(GymMachine gm : gymMachineList) {
			if(gm.getName().equals(eDTO.getName())) {
				e.setMachine(gm);
				break;
			}
		}
		return e;
	}
}
