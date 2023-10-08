package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import java.time.Instant;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WorkoutSession {
	private Long id;
	private Long customerId;
	private Instant startTime; //timestamp
	private Instant endTime; //timestamp
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

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	public JSONArray getSessionData() {
		return sessionData;
	}

	public void setSessionData(JSONArray sessionData) {
		this.sessionData = sessionData;
	}
}
