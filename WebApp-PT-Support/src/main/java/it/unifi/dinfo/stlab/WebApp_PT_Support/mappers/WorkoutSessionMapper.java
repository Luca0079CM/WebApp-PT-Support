package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import java.time.Instant;
import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutSessionDTO;
import jakarta.inject.Inject;

public class WorkoutSessionMapper {
	@Inject
	CustomerDao cDao;
	@Inject
	WorkoutProgramDao wpDao;
	
	public WorkoutSessionDTO toDTO(WorkoutSession ws) {
		WorkoutSessionDTO wsDTO = new WorkoutSessionDTO();
		wsDTO.setId(ws.getId());
		wsDTO.setCustomerId(ws.getCustomer().getId());
		wsDTO.setProgramName(ws.getProgram().getName());
		wsDTO.setStartTime(ws.getStartTime().toString());
		wsDTO.setEndTime(ws.getEndTime().toString());
		wsDTO.setSessionData(ws.getSessionData());
		return wsDTO;
	}
	
	public WorkoutSession toEntity(WorkoutSessionDTO wsDTO) {
		WorkoutSession ws = new WorkoutSession();		
		ws.setId(wsDTO.getId());
		ws.setCustomer(cDao.findById(wsDTO.getCustomerId()));
		ws.setProgram(wpDao.findByName(wsDTO.getProgramName()));
		ws.setStartTime(Instant.parse(wsDTO.getStartTime()));
		ws.setEndTime(Instant.parse(wsDTO.getEndTime()));
		ws.setSessionData(wsDTO.getSessionData());
		return ws;
	}

}
