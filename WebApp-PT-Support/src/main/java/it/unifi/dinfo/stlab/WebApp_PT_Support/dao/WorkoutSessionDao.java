package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.Iterator;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.QueryApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;

public class WorkoutSessionDao {
	private InfluxDBClient influxClient;
	private String url = "http://localhost:8086";
	private String token;
	private String bucket;
	private String org;

	public void buildConnection(String token, String bucket, String org) {
		this.setToken(token);
		this.setBucket(bucket);
		this.setOrg(org);
		this.influxClient = InfluxDBClientFactory.create(getUrl(), getToken().toCharArray(), getBucket(), getOrg());
	}

	public void save(WorkoutSession ws) {
		WriteApiBlocking writeApi = influxClient.getWriteApiBlocking();
		JSONObject sessionData = ws.getSessionData();
		System.out.println("2");
		JSONArray array = (JSONArray)sessionData.get("data");
		@SuppressWarnings("unchecked")
		Iterator<JSONObject> itr = array.iterator();
		while(itr.hasNext()) {
			JSONObject i = itr.next();
			Point point = Point
					  .measurement("machinedata")
					  .addTag("gym", "virgin")
					  .addTag("sessionid", ws.getId().toString())
					  .addField("machineid", i.get("machineId").toString())
					  .time(Instant.now(), WritePrecision.NS);
			writeApi.writePoint(this.bucket, this.org, point);
		}
	}
	
	public List<FluxRecord> findAll(){
		List<FluxRecord> result = new ArrayList<FluxRecord>();
		String fluxQuery = "from(bucket: \"workoutsessions\") |> range(start: -1h) |> filter(fn: (r) => r[\"_measurement\"] == \"machinedata\") |> filter(fn: (r) => r[\"_field\"] == \"machineid\")";
		QueryApi queryApi = influxClient.getQueryApi();
		List<FluxTable> tables = queryApi.query(fluxQuery, "PT-Support");
		for(FluxTable table : tables) {
			for(FluxRecord record : table.getRecords()) {
				result.add(record);
			}
		}
		return result;
	}
	
	public List<FluxRecord> findByGymName(String gymName){
		List<FluxRecord> result = new ArrayList<FluxRecord>();
		String fluxQuery = "from(bucket: \"workoutsessions\") |> range(start: -1h) |> filter(fn: (r) => r[\"_measurement\"] == \"machinedata\") |> filter(fn: (r) => r[\"_field\"] == \"machineid\")";
		QueryApi queryApi = influxClient.getQueryApi();
		List<FluxTable> tables = queryApi.query(fluxQuery, "PT-Support");
		for(FluxTable table : tables) {
			for(FluxRecord record : table.getRecords()) {
				if(record.getValueByKey("gym") != null && record.getValueByKey("gym").toString().equals(gymName)) {
					result.add(record);
				}
			}
		}
		return result;
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
