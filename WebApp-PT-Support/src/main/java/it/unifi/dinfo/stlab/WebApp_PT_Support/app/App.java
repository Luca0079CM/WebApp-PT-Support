package it.unifi.dinfo.stlab.WebApp_PT_Support.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Iterator;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutSessionDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.rest.CustomerRestEndpoint;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.*;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.*;

// classe principale per fare le prove
@Startup
@Singleton
public class App {
//	@PersistenceUnit
	EntityManagerFactory entityManagerFactory;

//	@PersistenceContext(unitName="WebApp-PT-Support")
	EntityManager em;

	private Long idTestCustomer;
	private Long idTestPersonalTrainer;
	private Long idTestWorkoutProgram;
	private Long idTestExercise;
	private Long idTestGymMachine;

    @PostConstruct
    @Transactional
    public void init() throws FileNotFoundException, ParseException, IOException, java.text.ParseException  {
    	entityManagerFactory = Persistence.createEntityManagerFactory("WebApp-PT-Support");
    	em = entityManagerFactory.createEntityManager();

//    	System.out.println("INIZIO LA POPOLAZIONE DELLE TABELLE");
//    	populateGymMachine();
//    	populateExercise();
//    	populateWorkoutProgram();
//    	populatePersonalTrainer();
//    	populateCustomer();


//    	System.out.println("INIZIO I TEST DI RECUPERO DALLE TABELLE");
//    	retrieveCustomer();
//    	retrievePersonalTrainer();
//    	retrieveWorkoutProgram();
//    	retrieveExercise();
//    	retrieveGymMachine();
//    	retrieveCustomersFromPersonalTrainer();
    	
    	testPopulateTablesForRestTest();
//    	populateWorkoutSession();
//    provaInflux();

    }

//    private void populateCustomer() {
//    	System.out.println("Provo a salvare un customer");
//    	CustomerDao customerDao = new CustomerDao();
//    	WorkoutProgramDao wpDao = new WorkoutProgramDao();
//    	PersonalTrainerDao ptDao = new PersonalTrainerDao();
//    	List<WorkoutProgram> wpList = wpDao.findAll();
//    	List<PersonalTrainer> ptList = ptDao.findAll();
//
//    	idTestCustomer = Long.valueOf(30);
//    	Long id;
//    	for(int i = 0; i<4; i++) {
//    		id = Long.valueOf(i+30);
//    		Customer c1 = new Customer();
//    		c1.setId(id);
//    		c1.setName("Name-"+id);
//    		c1.setSurname("Surname-"+id);
//    		c1.setPassword("password");
//    		c1.setEmail("user"+id+"@gmail.com");
//    		c1.setDateOfBirth(1997, i%12+1, i%30+1);
//    		c1.setPersonalTrainer(ptList.get(0));
//        	c1.setWorkoutProgramList(wpList);
//    		customerDao.save(c1);
//    	}
//    }

//    private void retrieveCustomer() {
//    	CustomerDao customerDao = new CustomerDao(entityManagerFactory);
//    	System.out.println("Provo a prendere il primo Customer");
//    	Customer c1 = customerDao.findById(idTestCustomer);
//    	if (c1 != null)
//    		System.out.println("Customer: " + c1.getName());
//    	else
//    		System.out.println("Customer non trovato");
//    	System.out.println("Provo a prendere tutti i Customer");
//    	List<Customer> customerList = customerDao.findAll();
//    	for(Customer c : customerList) {
//    		System.out.println("Customer: "+c.getName());
//    	}
//    }
//
//    private void populatePersonalTrainer() {
//    	System.out.println("Provo a salvare un personal trainer");
//    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao();
//    	WorkoutProgramDao wpDao = new WorkoutProgramDao();
//    	List<WorkoutProgram> wpList = wpDao.findAll();
//
//    	idTestPersonalTrainer = Long.valueOf(40);
//    	Long id;
//    	for(int i = 0; i<4; i++) {
//    		id = Long.valueOf(i+40);
//    		PersonalTrainer pt1 = new PersonalTrainer();
//    		pt1.setId(id);
//    		pt1.setName("Name-"+id);
//    		pt1.setSurname("Surname-"+id);
//    		pt1.setPassword("password");
//    		pt1.setEmail("user"+id+"@gmail.com");
//    		pt1.setDateOfBirth(1997, i%12+1, i%30+1);
//        	pt1.setWorkoutProgramList(wpList);
//        	System.out.println("BOOOOOO: "+ pt1.getId());
//    		personalTrainerDao.save(pt1);
//    	}
//    }
//
//    private void retrievePersonalTrainer() {
//    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
//    	System.out.println("Provo a prendere il primo PersonalTrainer");
//    	PersonalTrainer pt1 = personalTrainerDao.findById(idTestPersonalTrainer);
//    	if (pt1 != null)
//    		System.out.println("Personal Trainer: " + pt1.getName());
//    	else
//    		System.out.println("Personal Trainer non trovato");
//    	System.out.println("Provo a prendere tutti i PersonalTrainer");
//    	List<PersonalTrainer> personalTrainerList = personalTrainerDao.findAll();
//    	for(PersonalTrainer pt : personalTrainerList) {
//    		System.out.println("Personal Trainer: "+pt.getName());
//    	}
//    }
//
//    private void populateWorkoutProgram() {
//    	System.out.println("Provo a salvare un workout program");
//    	WorkoutProgramDao workoutProgramDao = new WorkoutProgramDao();
//    	ExerciseDao exerciseDao = new ExerciseDao();
//    	List<Exercise> eList = exerciseDao.findAll();
//
//    	idTestWorkoutProgram = Long.valueOf(0);
//    	Long id;
//    	for(int i = 0; i<3; i++) {
//    		id = Long.valueOf(i);
//    		WorkoutProgram wp1 = new WorkoutProgram();
//    		wp1.setId(id);
//    		wp1.setDifficultyLevel(i%10+1);
//    		wp1.setEstimatedDuration(i%60+20);
//    		wp1.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
//    		wp1.setExerciseList(eList);
//    		workoutProgramDao.save(wp1);
//    	}
//    }
//
//    private void retrieveWorkoutProgram() {
//    	WorkoutProgramDao workoutProgramDao = new WorkoutProgramDao(entityManagerFactory);
//    	System.out.println("Provo a prendere il primo WorkoutProgram");
//    	WorkoutProgram wp1 = workoutProgramDao.findById(idTestWorkoutProgram);
//    	if (wp1 != null)
//    		System.out.println("Workout Program: " + wp1.getId());
//    	else
//    		System.out.println("Workout Program non trovato");
//    	System.out.println("Provo a prenderle tutti gli WorkoutProgram");
//    	List<WorkoutProgram> workoutProgramList = workoutProgramDao.findAll();
//    	for(WorkoutProgram wp : workoutProgramList) {
//    		System.out.println("Workout Program: "+ wp.getId());
//    	}
//    }
//
//    private void populateExercise() {
//    	System.out.println("Provo a salvare un esercizio");
//    	ExerciseDao exerciseDao = new ExerciseDao();
//    	GymMachineDao gymMachineDao = new GymMachineDao();
//    	List<GymMachine> gymMachines = gymMachineDao.findAll();
//    	GymMachine testMachine = gymMachines.get(0);
//
//    	idTestExercise = Long.valueOf(10);
//    	Long id;
//    	for(int i = 0; i<4; i++) {
//    		id = Long.valueOf(i+10);
//    		System.out.println("AAAAAAA: " + id);
//    		Exercise e = new Exercise();
//    		e.setId(id);
//    		e.setDifficultyLevel(i%10+1);
//    		e.setDescription("Esercizio n° "+id);
//    		e.setMachine(testMachine);
//    		exerciseDao.save(e);
//    	}
//    }
//
//    private void retrieveExercise() {
//    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
//    	System.out.println("Provo a prendere il primo Exercise");
//    	Exercise e1 = exerciseDao.findById(idTestExercise);
//    	if (e1 != null)
//    		System.out.println("Exercise: " + e1.getId());
//    	else
//    		System.out.println("Exercise non trovato");
//    	System.out.println("Provo a prendere tutti gli Exercise");
//    	List<Exercise> exerciseList = exerciseDao.findAll();
//    	for(Exercise e : exerciseList) {
//    		System.out.println("Esercizio: "+ e.getId());
//    	}
//    }

//    private void populateGymMachine() {
//    	System.out.println("Provo a salvare una macchina");
////    	em = entityManagerFactory.createEntityManager();
//    	GymMachineDao gymMachineDao = new GymMachineDao();
//
//    	idTestGymMachine = Long.valueOf(20);
//    	Long id;
//    	for(int i = 0; i<4; i++) {
//    		id = Long.valueOf(i+20);
//    		GymMachine m = new GymMachine();
//    		m.setId(id);
//    		m.setName("Macchinario n°"+id);
//    		m.setDescription("Serve per allenarsi");
//    		gymMachineDao.save(m);
//    	}
//    }

//    private void retrieveGymMachine() {
//    	GymMachineDao gymMachineDao = new GymMachineDao(entityManagerFactory);
//    	System.out.println("Provo a prendere la prima Gym Machine");
//    	GymMachine g1 = gymMachineDao.findById(idTestGymMachine);
//    	if (g1 != null)
//    		System.out.println("Gym Machine: " + g1.getId());
//    	else
//    		System.out.println("Gym Machine non trovata");
//    	System.out.println("Provo a prendere tutte le Gym Machine");
//    	List<GymMachine> gymMachineList = gymMachineDao.findAll();
//    	for(GymMachine m : gymMachineList) {
//    		System.out.println("Macchinario: "+ m.getId());
//    	}
//    }
//
//    private void retrieveCustomersFromPersonalTrainer() {
//    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
//    	List<PersonalTrainer> ptList = personalTrainerDao.findAll();
//    	Long ptId = ptList.get(0).getId();
//    	System.out.println("Provo a prendere i customer associati al PT con id: " + ptId);
//    	List<Customer> customersOfPersonalTrainer = personalTrainerDao.findCustomersById(ptId);
//    	for(Customer c : customersOfPersonalTrainer)
//    		System.out.println("Customer: " + c.getId());
//    }

    private void populateWorkoutSession() throws ParseException, FileNotFoundException, IOException {
    	System.out.println("Popolo una workout session su InfluxDB");
    	WorkoutSessionDao wsDao = new WorkoutSessionDao();
    	WorkoutSession ws = new WorkoutSession();
    	ws.setId(Long.valueOf(100));
    	ws.setCustomer(em.find(Customer.class, 50l));
    	ws.setProgram(em.find(WorkoutProgram.class, 300l));
		ws.setStartTime(Instant.now());
		ws.setEndTime(Instant.now());

		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("/home/luca/git/repository/WebApp-PT-Support/src/main/java/it/unifi/dinfo/stlab/WebApp_PT_Support/app/testWorkoutSession.json");
		JSONArray jsonArr = (JSONArray)parser.parse(reader);

//		ws.setSessionData(jsonArr);
//
//		wsDao.buildConnection("M_eR6oFSVaFfVKj-UfdVgud1Kumz_Aa55_iPPM_e4-pFui3irqUYc6eMh8_Y-N51CAcG5JfDhroO9a4xHVJcPA==", "workoutsessions-bucket", "PT-Support");
//		System.out.println("Connessione stabilita");
//		wsDao.save(ws);
//		System.out.println("Salvataggio finito");
		
//		for(WorkoutSession ws1: wsDao.findAll())
//			System.out.println("Sessions retrieved: " + ws1);
		
//		List<Long> idList = new ArrayList<>();
//		idList.add(50l);
////		idList.add(51l);
////		System.out.println("ws list: " + wsDao.findAllByCustomerIdList(idList));
//		for(WorkoutSession ws2: wsDao.findAll()) {
//			System.out.println("Sessions retrieved: " + ws2);
//			System.out.println("Sessions data: " + ws2.getSessionData());
//		}
    }
    
    @Transactional
    private void testPopulateTablesForRestTest() {
    	Long gmId = Long.valueOf(10);
    	GymMachine gm1 = new GymMachine();
    	gm1.setId(gmId);
    	gm1.setName("Name-"+gmId);
    	gm1.setDescription("Serve per allenarsi");
		em.persist(gm1);
		
		Long exId = Long.valueOf(20);
		Exercise ex1 = new Exercise();
		ex1.setId(exId);
    	ex1.setName("Name-"+exId);
    	ex1.setDifficultyLevel(5);
    	ex1.setDescription("Allena le spalle");
    	ex1.setMachine(gm1);
		em.persist(ex1);
		List<Exercise> exList = new ArrayList<Exercise>();
		exList.add(ex1);
		
		
		Long wpId = Long.valueOf(300);
		WorkoutProgram wp1 = new WorkoutProgram();
		wp1.setId(wpId);
		wp1.setName("Chest Day Livello Medio");
		wp1.setDifficultyLevel(6);
		wp1.setEstimatedDuration(60);
		wp1.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
		wp1.setExerciseList(exList);
		em.persist(wp1);
		List<WorkoutProgram> wpList = new ArrayList<WorkoutProgram>();
		wpList.add(wp1);
    	
    	Long ptId = Long.valueOf(41);
		PersonalTrainer pt1 = new PersonalTrainer();
		pt1.setId(ptId);
		pt1.setName("Name-"+ptId);
		pt1.setSurname("Surname-"+ptId);
		pt1.setPassword("password");
		pt1.setEmail("user"+ptId+"@gmail.com");
		pt1.setDateOfBirth(1997, 10, 10);
//    	pt1.setWorkoutProgramList(wpList);
		em.persist(pt1);
		
		Long ptId2 = Long.valueOf(401);
		PersonalTrainer pt2 = new PersonalTrainer();
		pt2.setId(ptId2);
		pt2.setName("Name-"+ptId2);
		pt2.setSurname("Surname-"+ptId2);
		pt2.setPassword("password");
		pt2.setEmail("user"+ptId2+"@gmail.com");
		pt2.setDateOfBirth(1997, 10, 10);
//    	pt2.setWorkoutProgramList(wpList);
		em.persist(pt2);
		
    	for(int i = 0; i<2; i++) {
    		Long cId = Long.valueOf(i+50);
    		Customer c1 = new Customer();
    		c1.setId(cId);
    		c1.setName("Name-"+cId);
    		c1.setSurname("Surname-"+cId);
    		c1.setPassword("password");
    		c1.setEmail("user"+cId+"@gmail.com");
    		c1.setDateOfBirth(1997, i%12+1, i%30+1);
    		c1.setPersonalTrainer(pt1);
        	c1.setWorkoutProgramList(wpList);
    		em.persist(c1);	
    	}
    	
    	//Utente senza pt
    	Long cId = Long.valueOf(7315462);
		Customer c1 = new Customer();
		c1.setId(cId);
		c1.setName("Name-"+cId);
		c1.setSurname("Surname-"+cId);
		c1.setPassword("password");
		c1.setEmail("user"+cId+"@gmail.com");
		c1.setDateOfBirth(1997, 4, 15);
		c1.setPersonalTrainer(null);
    	c1.setWorkoutProgramList(null);
		em.persist(c1);	
    }

    public void provaInflux() throws ParseException, FileNotFoundException, IOException, java.text.ParseException{
    	String token = "M_eR6oFSVaFfVKj-UfdVgud1Kumz_Aa55_iPPM_e4-pFui3irqUYc6eMh8_Y-N51CAcG5JfDhroO9a4xHVJcPA==";
        String bucket = "workoutsessions-bucket";
        String org = "PT-Support";

        InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());
        WorkoutSession ws = new WorkoutSession();
    	ws.setId(Long.valueOf(100));
    	ws.setCustomer(em.find(Customer.class, 50l));
    	ws.setProgram(em.find(WorkoutProgram.class, 300l));
		ws.setStartTime(Instant.now());
		ws.setEndTime(Instant.now());

		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("/home/luca/git/repository/WebApp-PT-Support/src/main/java/it/unifi/dinfo/stlab/WebApp_PT_Support/app/testWorkoutSession.json");
		JSONArray jsonArr = (JSONArray)parser.parse(reader);
		ws.setSessionData(jsonArr);
        JSONArray dataArray = ws.getSessionData();
		Iterator<JSONObject> itr = dataArray.iterator();
		WriteApiBlocking writeApi = client.getWriteApiBlocking();
		JSONObject i = itr.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = dateFormat.parse(i.get("timestamp").toString());
		Instant instant = Instant.parse("2023-10-05T15:30:56.5544554Z");
		

		Point sessionpoint = Point
				  .measurement("workout-sessions")
//				  .addTag("host", "host1")
				  .addTag("sessionId", ws.getId().toString())
				  
//				  .addField("used_percent", 23.43234543)
				  .addField("necessaryfield", 0)
//				  .time(Instant.now(), WritePrecision.NS);
				  .time(Instant.parse(i.get("timestamp").toString()), WritePrecision.S);
//				  .time((long) instant.toEpochMilli(), WritePrecision.MS)
//				  .time(1696519856554l, WritePrecision.MS);
//				  .time(1697214464757l, WritePrecision.MS);
		
		
//					  .addTag("customerId", ws.getCustomer().getId().toString())
//					  .addTag("programName", ws.getProgram().getName())
//					  .addTag("startTime", ws.getStartTime().toString())
//					  .addTag("endTime", ws.getEndTime().toString())
//					  .addTag("exerciseName", i.get("exerciseName").toString())
//					  .addTag("machineId", i.get("machineId").toString())//oppure è un field?
//					  .addTag("load", i.get("load").toString())
//					  .addTag("repetitions", i.get("repetitions").toString())
//				  
//				  .time(Instant.parse(i.get("timestamp").toString()), WritePrecision.NS);
				  
		System.out.println(ws.getId().toString());
		System.out.println(Instant.parse(i.get("timestamp").toString()));
		System.out.println(Instant.now());
		System.out.println(instant.toEpochMilli());
		System.out.println(System.currentTimeMillis());
		writeApi.writePoint(bucket, org, sessionpoint);
		
		
		String query = "from(bucket: \"workoutsessions-bucket\") |> range(start: -1h)";
		List<FluxTable> tables = client.getQueryApi().query(query, org);

		for (FluxTable table : tables) {
		  for (FluxRecord record : table.getRecords()) {
		    System.out.println("-+-+-+-++-+-+-+-+-"+record);
		  }
		}
		
		client.close();
    }

}
