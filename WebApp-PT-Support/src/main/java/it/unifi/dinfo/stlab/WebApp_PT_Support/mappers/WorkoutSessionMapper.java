package it.unifi.dinfo.stlab.WebApp_PT_Support.mappers;

import java.time.Instant;
import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutSessionDTO;

public class WorkoutSessionMapper {
	public WorkoutSessionDTO toDTO(WorkoutSession ws) {
		WorkoutSessionDTO wsDTO = new WorkoutSessionDTO();
		wsDTO.setId(ws.getId());
		wsDTO.setStartTime(ws.getStartTime().toString());
		wsDTO.setEndTime(ws.getEndTime().toString());
		wsDTO.setSessionData(ws.getSessionData());
		return wsDTO;
	}
	
	public WorkoutSession toEntity(WorkoutSessionDTO wsDTO) {
		WorkoutSession ws = new WorkoutSession();		
		ws.setId(wsDTO.getId());
		ws.setStartTime(Instant.parse(wsDTO.getStartTime()));
		ws.setEndTime(Instant.parse(wsDTO.getEndTime()));
		ws.setSessionData(wsDTO.getSessionData());
		return ws;
	}

}
