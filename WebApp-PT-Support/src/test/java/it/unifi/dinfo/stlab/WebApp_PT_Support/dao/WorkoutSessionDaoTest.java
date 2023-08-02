package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject;
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
		workoutSession.setDuration(60);

    	JSONParser parser = new JSONParser();
		Reader reader = new FileReader("/home/luca/git/repository/WebApp-PT-Support/src/main/java/it/unifi/dinfo/stlab/WebApp_PT_Support/app/testWorkoutSession.json");
		JSONObject jsonObj = (JSONObject)parser.parse(reader);
		workoutSession.setSessionData(jsonObj);
		
		workoutSessionDao = new WorkoutSessionDao();
//		try {
//			FieldUtils.writeField(workoutSessionDao, "influxClient", influxClient, true);
//		}
//		catch (IllegalAccessException e) {
//			throw new InitializationError(e);
//		}

		workoutSessionDao.buildConnection("DyZgvNUz2YGZmv3Dv_noqJGkUz1glxRcEmoI5RtCN09QK5eQ_iMDY_wNc4H8aF_WSqjVUIvGfoCfnYqB4MgMPg==DyZgvNUz2YGZmv3Dv_noqJGkUz1glxRcEmoI5RtCN09QK5eQ_iMDY_wNc4H8aF_WSqjVUIvGfoCfnYqB4MgMPg==", "mainbucket", "PT-Support");
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
		List<FluxRecord> result = workoutSessionDao.findAll();
		for(FluxRecord record : result)
			System.out.println(record);
	}
	
	@Test
	public void testFindByGymName() {
		String gymName = "virgin";
		List<FluxRecord> result = workoutSessionDao.findByGymName(gymName);
		for(FluxRecord record : result)
			System.out.println(record);
	}

}
