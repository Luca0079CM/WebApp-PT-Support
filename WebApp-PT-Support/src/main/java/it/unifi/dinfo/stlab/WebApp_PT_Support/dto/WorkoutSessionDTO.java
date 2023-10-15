package it.unifi.dinfo.stlab.WebApp_PT_Support.dto;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WorkoutSessionDTO {
	private Long id;
	private Long customerId;
	private String programName;
	private String startTime; //timestamp
	private String endTime; //timestamp
	private List<HashMap<String, String>> sessionData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<HashMap<String, String>> getSessionData() {
		return sessionData;
	}

	public void setSessionData(List<HashMap<String, String>> sessionData) {
		this.sessionData = sessionData;
	}

}
