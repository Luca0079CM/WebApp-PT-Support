package it.unifi.dinfo.stlab.WebApp_PT_Support.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WorkoutSessionDTO {
	private Long id;
	private Long customerId;
	private String startTime; //timestamp
	private String endTime; //timestamp
	private JSONArray sessionData;

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

	public JSONArray getSessionData() {
		return sessionData;
	}

	public void setSessionData(JSONArray sessionData) {
		this.sessionData = sessionData;
	}

}
