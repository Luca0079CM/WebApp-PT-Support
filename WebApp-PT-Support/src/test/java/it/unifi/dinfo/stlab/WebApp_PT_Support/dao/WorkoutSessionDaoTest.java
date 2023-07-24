package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runners.model.InitializationError;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutSessionDao;

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
		Reader reader = new FileReader("C:\\Users\\carlo\\Desktop\\Github_repos\\WebApp-PT-Support\\WebApp-PT-Support\\src\\main\\java\\it\\unifi\\dinfo\\stlab\\WebApp_PT_Support\\app\\testWorkoutSession.json");
		JSONObject jsonObj = (JSONObject)parser.parse(reader);
		workoutSession.setSessionData(jsonObj);
		
		workoutSessionDao = new WorkoutSessionDao();
//		try {
//			FieldUtils.writeField(workoutSessionDao, "influxClient", influxClient, true);
//		}
//		catch (IllegalAccessException e) {
//			throw new InitializationError(e);
//		}

		workoutSessionDao.buildConnection("gVYfigM83pD3n2evLZx3NI8Iv_df5S00R4kFw03oTrHoazIw1MWfEBnE-lMlOe-iULDND8w8Qrrf2_kp07rW9w==", "mainbucket", "PT-Support");
		System.out.println("Connessione stabilita");
	}
	
	@Test
	public void testSave() {
		workoutSessionDao.save(workoutSession);
		System.out.println("Salvataggio riuscito");
	}

}
