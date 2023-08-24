package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.GymMachineDTO;

public class GymMachineMapper {
	
	public GymMachineDTO toDTO(GymMachine machine) {
		GymMachineDTO machineDTO = new GymMachineDTO();
		machineDTO.setId(machine.getId());
		machineDTO.setName(machine.getName());
		return machineDTO;
	}
	
	public GymMachine toEntity(GymMachineDTO mDTO) {
		GymMachine m = new GymMachine();
		m.setId(mDTO.getId());
		m.setName(mDTO.getName());
		m.setDescription("EMPTY");
		return m;
	}
	
}
