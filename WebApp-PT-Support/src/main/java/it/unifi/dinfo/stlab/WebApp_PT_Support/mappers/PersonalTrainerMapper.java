package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import java.time.LocalDate;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;

public class PersonalTrainerMapper {
	
	public PersonalTrainerDTO toDTO(PersonalTrainer pt) {
		PersonalTrainerDTO ptDTO = new PersonalTrainerDTO();
		ptDTO.setId(pt.getId());
		ptDTO.setName(pt.getName());
		ptDTO.setSurname(pt.getSurname());
		ptDTO.setEmail(pt.getEmail());
		ptDTO.setPassword(pt.getPassword());
		ptDTO.setDateOfBirth(pt.getDateOfBirth().toString());
		return ptDTO;
	}
	
	
	public PersonalTrainer toEntity(PersonalTrainerDTO ptDTO) {
		PersonalTrainer pt = new PersonalTrainer();
		pt.setId(ptDTO.getId());
		pt.setName(ptDTO.getName());
		pt.setSurname(ptDTO.getSurname());
		pt.setEmail(ptDTO.getEmail());
		pt.setPassword(ptDTO.getPassword());
		pt.setDateOfBirth(LocalDate.parse(ptDTO.getDateOfBirth()));
		pt.setCustomersList(null);
		return pt;
	}
}
