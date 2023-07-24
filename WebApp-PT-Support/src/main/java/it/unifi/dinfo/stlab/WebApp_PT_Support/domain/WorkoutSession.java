package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import org.json.simple.JSONObject;

public class WorkoutSession {
	private Long id;
	private int duration;
	private JSONObject sessionData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public JSONObject getSessionData() {
		return sessionData;
	}

	public void setSessionData(JSONObject sessionData) {
		this.sessionData = sessionData;
	}
}
