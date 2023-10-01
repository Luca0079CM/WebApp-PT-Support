package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import org.json.simple.JSONObject;

public class WorkoutSession {
	private Long id;
	private int startTime; //timestamp
	private int endTime; //timestamp
	private JSONObject sessionData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public JSONObject getSessionData() {
		return sessionData;
	}

	public void setSessionData(JSONObject sessionData) {
		this.sessionData = sessionData;
	}
}
