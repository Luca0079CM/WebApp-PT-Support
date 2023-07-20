package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;

public class WorkoutSessionDao {
	private InfluxDBClient influxClient;
	private String url = "http://localhost:8086";
	private String token;
	private String bucket;
	private String org;

	public void buildConnection(String token, String bucket, String org) {
		setToken(token);
		setBucket(bucket);
		setOrg(org);
		this.influxClient = InfluxDBClientFactory.create(getUrl(), getToken().toCharArray(), getBucket(), getOrg());
	}

	public boolean save(WorkoutSession ws) {
		WriteApiBlocking writeApi = influxClient.getWriteApiBlocking();
		boolean success = false;
		String data;
		JSONObject sessionData = ws.getSessionData();
		JSONArray array = (JSONArray)sessionData.get("data");
		Iterator<JSONObject> itr = array.iterator();
		while(itr.hasNext()) {
			JSONObject i = itr.next();
			data = "machine-data-point,machine-id=" + i.get("machineId");
			writeApi.writeRecord(this.bucket, this.org, WritePrecision.NS, data);
			data = "";
		}
		success = true;
		return success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getUrl() {
		return url;
	}

}
