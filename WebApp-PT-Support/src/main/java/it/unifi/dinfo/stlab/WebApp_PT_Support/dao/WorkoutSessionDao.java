package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.Iterator;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;
import jakarta.inject.Inject;

public class WorkoutSessionDao {
	private InfluxDBClient influxClient;
	private String url = "http://localhost:8086";
	private String token;
	private String bucket;
	private String org;
	@Inject
	CustomerDao custDao = new CustomerDao();
	@Inject
	WorkoutProgramDao wpDao = new WorkoutProgramDao();

	public void buildConnection(String token, String bucket, String org) {
		this.setToken(token);
		this.setBucket(bucket);
		this.setOrg(org);
		this.influxClient = InfluxDBClientFactory.create(getUrl(), getToken().toCharArray(), getBucket(), getOrg());
	}

	public void save(WorkoutSession ws) {
		WriteApiBlocking writeApi = influxClient.getWriteApiBlocking();
		List<HashMap<String, String>> dataArray = ws.getSessionData();
		System.out.println("data: "+ dataArray);
//		@SuppressWarnings("unchecked")
//		Iterator<JSONObject> itr = dataArray.iterator();
		for(HashMap<String, String> i : dataArray) {
//			JSONObject i = itr.next();
			Point sessionpoint = Point
					  .measurement("workout-sessions")
					  .addTag("sessionId", ws.getId().toString())
					  .addTag("customerId", ws.getCustomer().getId().toString())
					  .addTag("programName", ws.getProgram().getName())
					  .addTag("startTime", ws.getStartTime().toString())
					  .addTag("endTime", ws.getEndTime().toString())
					  .addTag("exerciseName", i.get("exerciseName").toString())
					  .addTag("machineId", i.get("machineId").toString())//oppure è un field?
					  .addTag("load", i.get("load").toString())
					  .addTag("repetitions", i.get("repetitions").toString())
					  .addField("necessaryfield", 0)
					  .time(Instant.parse(i.get("timestamp").toString()), WritePrecision.S);
			System.out.println("sessionId: "+ ws.getId());
			writeApi.writePoint(this.bucket, this.org, sessionpoint);
		}
	}
	
	public List<WorkoutSession> findAll(){
		List<WorkoutSession> result = new ArrayList<WorkoutSession>();
		String fluxQuery = "from(bucket: \"workoutsessions-bucket\") |> range(start: -30d) |> filter(fn: (r) => r[\"_measurement\"] == \"workout-sessions\")";
		QueryApi queryApi = influxClient.getQueryApi();
		List<FluxTable> tables = queryApi.query(fluxQuery, "PT-Support");
		Long wsId = -1l;
		for(FluxTable table : tables) {
			for(FluxRecord record : table.getRecords()) {
				if(Long.parseLong(record.getValueByKey("sessionId").toString()) != wsId) {
					WorkoutSession ws = new WorkoutSession();
					wsId = Long.parseLong(record.getValueByKey("sessionId").toString());
					ws.setId(wsId);
					ws.setCustomer(custDao.findById(Long.parseLong(record.getValueByKey("customerId").toString())));
					ws.setProgram(wpDao.findByName(record.getValueByKey("programName").toString()));
					ws.setStartTime(Instant.parse(record.getValueByKey("startTime").toString()));
					ws.setEndTime(Instant.parse(record.getValueByKey("endTime").toString()));
					ws.setSessionData(getMachineDataFromSession(wsId));
					result.add(ws);
				}
			}
		}
		return result;
	}
	
	//da cestinare
//	public List<WorkoutSession> findAllByCustomerIdList(List<Long> idList){
//		List<WorkoutSession> result = new ArrayList<WorkoutSession>();
//		String idString = "[\"" + idList.stream()
//		        .map(Object::toString)
//		        .collect(Collectors.joining("\", \"")) + "\"]";
//		String fluxQuery = "from(bucket: \"workoutsessions-bucket\") |> range(start: -30d) |> filter(fn: (r) => r[\"_measurement\"] == \"workout-sessions\" and contains(value: r.customerId, set: " + idString + "))";
//		QueryApi queryApi = influxClient.getQueryApi();
//		Long custId = -1l;
//		Long wsId = -1l;
//		List<FluxTable> tables = queryApi.query(fluxQuery, "PT-Support");
//		for(FluxTable table : tables) {
//			for(FluxRecord record : table.getRecords()) {
//				if((Long.parseLong(record.getValueByKey("customerId").toString()) != custId) || (Long.parseLong(record.getValueByKey("sessionId").toString()) != wsId)) {
//					WorkoutSession ws = new WorkoutSession();
//					custId = Long.parseLong(record.getValueByKey("customerId").toString());
//					ws.setCustomer(custDao.findById(custId));
//					wsId = Long.parseLong(record.getValueByKey("sessionId").toString());
//					ws.setId(wsId);
//					ws.setStartTime(Instant.parse(record.getValueByKey("startTime").toString()));
//					ws.setEndTime(Instant.parse(record.getValueByKey("endTime").toString()));
//					ws.setSessionData(getMachineDataFromSession(wsId));
//					result.add(ws);
//				}
//			}
//		}
//		return result;
//	}
	
	public List<WorkoutSession> findByCustomerIdAndProgramName(Long customerId, String programName){
		List<WorkoutSession> result = new ArrayList<WorkoutSession>();
		String fluxQuery = "from(bucket: \"workoutsessions-bucket\") |> range(start: -30d) |> filter(fn: (r) => r[\"_measurement\"] == \"workout-sessions\") |> filter(fn: (r) => r.customerId == \""+customerId+"\" and r.programName == \""+programName+"\")";
		QueryApi queryApi = influxClient.getQueryApi();
		List<FluxTable> tables = queryApi.query(fluxQuery, "PT-Support");
		Long wsId = -1l;
		for(FluxTable table : tables) {
			for(FluxRecord record : table.getRecords()) {
				if(Long.parseLong(record.getValueByKey("sessionId").toString()) != wsId) {
					WorkoutSession ws = new WorkoutSession();
					ws.setCustomer(custDao.findById(Long.parseLong(record.getValueByKey("customerId").toString()))); //should always be equal to customerId
					ws.setProgram(wpDao.findByName(record.getValueByKey("programName").toString()));
					wsId = Long.parseLong(record.getValueByKey("sessionId").toString());
					ws.setId(wsId);
					ws.setStartTime(Instant.parse(record.getValueByKey("startTime").toString()));
					ws.setEndTime(Instant.parse(record.getValueByKey("endTime").toString()));
					ws.setSessionData(getMachineDataFromSession(wsId));
					result.add(ws);
				}
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray getMachineDataFromSession(Long sessionId) {
		JSONArray jsonArray = new JSONArray();
		String fluxQuery = "from(bucket: \"workoutsessions-bucket\") |> range(start: -30d) |> filter(fn: (r) => r[\"_measurement\"] == \"workout-sessions\") |> filter(fn: (r) => r.sessionId == \""+sessionId+"\")";
		QueryApi queryApi = influxClient.getQueryApi();
		List<FluxTable> tables = queryApi.query(fluxQuery, "PT-Support");
		for(FluxTable table : tables) {
	        for(FluxRecord record : table.getRecords()) {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("exerciseName", record.getValueByKey("exerciseName"));
	            jsonObject.put("machineId", record.getValueByKey("machineId"));
//	            if(record.getField().equals("load"))
//	            	jsonObject.put("load", record.getValue());
//	            else if(record.getField().equals("repetitions"))
//	            	jsonObject.put("repetitions", record.getValue());
	            jsonObject.put("load", record.getValueByKey("load"));
	            jsonObject.put("repetitions", record.getValueByKey("repetitions"));
	            jsonObject.put("timestamp", record.getTime());

	            jsonArray.add(jsonObject);
	        }
	    }
	    return jsonArray;
	}
	
	
	
	//non serve?, le statistiche sulle macchine si tirano fuori sul fe a partire dalle sessions
	public List<FluxRecord> findByMachineId(String machineId){
		List<FluxRecord> result = new ArrayList<FluxRecord>();
		String fluxQuery = "from(bucket: \"workoutsessions-bucket\") |> range(start: -1h) |> filter(fn: (r) => r[\"_measurement\"] == \"workout-sessions\") |> filter(fn: (r) => r[\"_field\"] == \"sessionId\")";
		QueryApi queryApi = influxClient.getQueryApi();
		List<FluxTable> tables = queryApi.query(fluxQuery, "PT-Support");
		for(FluxTable table : tables) {
			for(FluxRecord record : table.getRecords()) {
				//the getValueByKey() must be a tag
				if(record.getValueByKey("machineId") != null && record.getValueByKey("machineId").toString().equals(machineId)) {
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
