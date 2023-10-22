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
    	populateWorkoutSession();
//    	provaInflux();
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
		Reader reader = new FileReader("C:\\Users\\carlo\\Desktop\\Github_repos\\WebApp-PT-Support\\WebApp-PT-Support\\src\\main\\java\\it\\unifi\\dinfo\\stlab\\WebApp_PT_Support\\app\\testWorkoutSession.json");
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
    	Long gmId = 54974L;
    	GymMachine gm1 = new GymMachine();
    	gm1.setId(gmId);
    	gm1.setName("Chest Press");
    	gm1.setDescription("Sedersi sul macchinario e spingere in avanti i braccioli chiudendo il petto");
		em.persist(gm1);
		
		Long gmId2 = 54332L;
    	GymMachine gm2 = new GymMachine();
    	gm2.setId(gmId2);
    	gm2.setName("Pectoral Machine");
    	gm2.setDescription("Sedersi sul macchinario e avvicinare tra loro i braccioli chiudendo il petto");
		em.persist(gm2);
		
		Long exId = 45223L;
		Exercise ex1 = new Exercise();
		ex1.setId(exId);
    	ex1.setName("Spinte alla chest press");
    	ex1.setDifficultyLevel(5);
    	ex1.setDescription("Allena il petto e i tricipiti");
    	ex1.setMachine(gm1);
		em.persist(ex1);
		List<Exercise> exList = new ArrayList<Exercise>();
		exList.add(ex1);
		
		Long exId2 = 47854L;
		Exercise ex2 = new Exercise();
		ex2.setId(exId2);
    	ex2.setName("Chiusure del petto alla Pectoral Machine");
    	ex2.setDifficultyLevel(4);
    	ex2.setDescription("Allena il petto");
    	ex2.setMachine(gm2);
		em.persist(ex2);
		exList.add(ex2);
		
		Long exId3 = 22245L;
		Exercise ex3 = new Exercise();
		ex3.setId(exId3);
    	ex3.setName("Push Up");
    	ex3.setDifficultyLevel(4);
    	ex3.setDescription("Allena il petto e i tricipiti senza attrezzi");
		em.persist(ex3);
		exList.add(ex3);
		
		
		Long wpId = 35623L;
		WorkoutProgram wp1 = new WorkoutProgram();
		wp1.setId(wpId);
		wp1.setName("Chest Day Livello Medio");
		wp1.setDifficultyLevel(6);
		wp1.setEstimatedDuration(60);
		wp1.setWorkoutProgramType(WorkoutProgramType.WEIGHTS);
		wp1.setExerciseList(exList);
		em.persist(wp1);
		List<WorkoutProgram> wpList = new ArrayList<WorkoutProgram>();
		wpList.add(wp1);
    	
    	Long ptId = 12345L;
		PersonalTrainer pt1 = new PersonalTrainer();
		pt1.setId(ptId);
		pt1.setName("Carlo");
		pt1.setSurname("Ceccherelli");
		pt1.setPassword("password");
		pt1.setEmail("carlo-pt@gmail.com");
		pt1.setDateOfBirth(1997, 10, 10);
//    	pt1.setWorkoutProgramList(wpList);
		em.persist(pt1);
		
		Long ptId2 = 54321L;
		PersonalTrainer pt2 = new PersonalTrainer();
		pt2.setId(ptId2);
		pt2.setName("Francesco");
		pt2.setSurname("Ballerini");
		pt2.setPassword("password");
		pt2.setEmail("fraballe-pt@gmail.com");
		pt2.setDateOfBirth(1997, 10, 10);
//    	pt2.setWorkoutProgramList(wpList);
		em.persist(pt2);
		
    	
		Long cId1 = 34251L;
		Customer c1 = new Customer();
		c1.setId(cId1);
		c1.setName("Luca");
		c1.setSurname("Leuter");
		c1.setPassword("password");
		c1.setEmail("luca.leuter@gmail.com");
		c1.setDateOfBirth(1997, 3, 8);
		c1.setPersonalTrainer(pt1);
    	c1.setWorkoutProgramList(wpList);
		em.persist(c1);	
		
		Long cId2 = 11122L;
		Customer c2 = new Customer();
		c2.setId(cId2);
		c2.setName("Leonardo");
		c2.setSurname("Di Iorio");
		c2.setPassword("password");
		c2.setEmail("leo.iorio@gmail.com");
		c2.setDateOfBirth(1997, 7, 3);
		c2.setPersonalTrainer(pt2);
    	c2.setWorkoutProgramList(wpList);
		em.persist(c2);	
    	
    	
    	//Utente senza pt
    	Long cId = 73154L;
		Customer c3 = new Customer();
		c3.setId(cId);
		c3.setName("Chiara");
		c3.setSurname("Tesi");
		c3.setPassword("password");
		c3.setEmail("chiara.tesi@gmail.com");
		c3.setDateOfBirth(1997, 8, 1);
		c3.setPersonalTrainer(null);
    	c3.setWorkoutProgramList(null);
		em.persist(c3);
    }

}
