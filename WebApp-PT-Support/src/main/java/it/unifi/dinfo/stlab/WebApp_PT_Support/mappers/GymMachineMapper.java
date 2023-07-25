package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.GymMachineDTO;

public class GymMachineMapper {
	
	public GymMachineDTO generateGymMachineTO(GymMachine machine) {
		GymMachineDTO machineDTO = new GymMachineDTO();
		machineDTO.setId(machine.getId());
		machineDTO.setName(machine.getName());
		return machineDTO;
	}
	
}
