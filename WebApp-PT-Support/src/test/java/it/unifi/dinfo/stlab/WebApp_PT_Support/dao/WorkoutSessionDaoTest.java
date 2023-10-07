package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runners.model.InitializationError;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;

public class WorkoutSessionDaoTest extends JPATest {
	
	private WorkoutSessionDao workoutSessionDao;
	private WorkoutSession workoutSession;
	
	@Override
	protected void init() throws InitializationError, IOException, ParseException {
		System.out.println("Popolo una workout session su InfluxDB");
		workoutSession = new WorkoutSession();
		workoutSession.setId(Long.valueOf(100));
		workoutSession.setStartTime(Instant.now());
		workoutSession.setEndTime(Instant.now());

    	JSONParser parser = new JSONParser();
		Reader reader = new FileReader("C:\\Users\\carlo\\Desktop\\Github_repos\\WebApp-PT-Support\\WebApp-PT-Support\\src\\main\\java\\it\\unifi\\dinfo\\stlab\\WebApp_PT_Support\\app\\testWorkoutSession.json");
		JSONArray jsonArr = (JSONArray)parser.parse(reader);
		workoutSession.setSessionData(jsonArr);
		
		workoutSessionDao = new WorkoutSessionDao();
//		try {
//			FieldUtils.writeField(workoutSessionDao, "influxClient", influxClient, true);
//		}
//		catch (IllegalAccessException e) {
//			throw new InitializationError(e);
//		}

		workoutSessionDao.buildConnection("57my30fVD2mvRW7pKOgTTqGbymad0B2U5HR7rGUszU1GPBSDnnFU4Dt8rQdNiLJaIJdm_jOLG6l4hQLK8FHB5Q==", "mainbucket", "PT-Support");
		System.out.println("Connessione stabilita");
	}
	
	@Test
	public void testSave() {
		System.out.println("Provo a salvare");
		workoutSessionDao.save(workoutSession);
		System.out.println("Salvataggio riuscito");
	}
	
	@Test
	public void testFindAll() {
		List<WorkoutSession> result = workoutSessionDao.findAll();
		for(WorkoutSession record : result)
			System.out.println(record);
	}
	
//	@Test
//	public void testFindByGymName() {
//		String gymName = "virgin";
//		List<FluxRecord> result = workoutSessionDao.findByGymName(gymName);
//		for(FluxRecord record : result)
//			System.out.println(record);
//	}

}
