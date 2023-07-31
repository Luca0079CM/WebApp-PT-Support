package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;

public class PersonalTrainerMapper {
	
	public PersonalTrainerDTO generatePersonalTrainerTO(PersonalTrainer pt) {
		PersonalTrainerDTO ptDTO = new PersonalTrainerDTO();
		ptDTO.setId(pt.getId());
		ptDTO.setName(pt.getName());
		ptDTO.setSurname(pt.getSurname());
		ptDTO.setEmail(pt.getEmail());
		ptDTO.setDateOfBirth(pt.getDateOfBirth());
		return ptDTO;
	}
	
	
	public PersonalTrainer generatePersonalTrainerFromTO(PersonalTrainerDTO ptDTO) {
		PersonalTrainer pt = new PersonalTrainer();
		pt.setId(ptDTO.getId());
		pt.setName(ptDTO.getName());
		pt.setSurname(pt.getSurname());
		pt.setEmail(ptDTO.getEmail());
		pt.setPassword("password");
		pt.setDateOfBirth(ptDTO.getDateOfBirth());
		pt.setCustomersList(null);
		pt.setWorkoutProgramList(null);
		return pt;
	}
}
