package it.unifi.dinfo.stlab.WebApp_PT_Support.app;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.PersonalTrainerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutProgramDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.ExerciseDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.GymMachineDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutSession;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.WorkoutSessionDao;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

// classe principale per fare le prove
@Startup
@Singleton
public class App {
	EntityManagerFactory entityManagerFactory;
	private Long idTestCustomer;
	private Long idTestPersonalTrainer;
	private Long idTestWorkoutProgram;
	private Long idTestExercise;
	private Long idTestGymMachine;

    @PostConstruct
    @Transactional
    public void init() {
    	entityManagerFactory = Persistence.createEntityManagerFactory("WebApp-PT-Support");
    	
    	System.out.println("INIZIO LA POPOLAZIONE DELLE TABELLE");
    	populateGymMachine();
    	populateExercise();
    	populateWorkoutProgram();
    	populatePersonalTrainer();
    	populateCustomer();
    	
//    	populateWorkoutSession();
    	    	
    	System.out.println("INIZIO I TEST DI RECUPERO DALLE TABELLE");
    	retrieveCustomer();
    	retrievePersonalTrainer();
    	retrieveWorkoutProgram();
    	retrieveExercise();
    	retrieveGymMachine();
    	retrieveCustomersFromPersonalTrainer();
    }
    
    private void populateCustomer() {
    	System.out.println("Provo a salvare un customer");
    	CustomerDao customerDao = new CustomerDao(entityManagerFactory);    	
    	WorkoutProgramDao wpDao = new WorkoutProgramDao(entityManagerFactory);
    	PersonalTrainerDao ptDao = new PersonalTrainerDao(entityManagerFactory);
    	List<WorkoutProgram> wpList = wpDao.findAll();
    	List<PersonalTrainer> ptList = ptDao.findAll();
  
    	idTestCustomer = Long.valueOf(30);
    	Long id;
    	for(int i = 0; i<4; i++) {
    		id = Long.valueOf(i+30);
    		Customer c1 = new Customer();
    		c1.setId(id);
    		c1.setName("Name-"+id);
    		c1.setSurname("Surname-"+id);
    		c1.setPassword("password");
    		c1.setEmail("user"+id+"@gmail.com");
    		c1.setDateOfBirth(1997, i%12+1, i%30+1);
    		c1.setPersonalTrainer(ptList.get(0));
        	c1.setWorkoutProgramList(wpList);
    		boolean success = customerDao.save(c1);
    		if(success)
    			System.out.println("Salvataggio riuscito");
    		else
    			System.out.println("Salvataggio fallito");
    	}
    }
    
    private void retrieveCustomer() {
    	CustomerDao customerDao = new CustomerDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo Customer");
    	Customer c1 = customerDao.findById(idTestCustomer);
    	if (c1 != null)
    		System.out.println("Customer: " + c1.getName());
    	else
    		System.out.println("Customer non trovato");
    	System.out.println("Provo a prendere tutti i Customer");
    	List<Customer> customerList = customerDao.findAll();
    	for(Customer c : customerList) {
    		System.out.println("Customer: "+c.getName());
    	}
    }

    private void populatePersonalTrainer() {
    	System.out.println("Provo a salvare un personal trainer");
    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
    	WorkoutProgramDao wpDao = new WorkoutProgramDao(entityManagerFactory);
    	List<WorkoutProgram> wpList = wpDao.findAll();
    	
    	idTestPersonalTrainer = Long.valueOf(40);
    	Long id;
    	for(int i = 0; i<4; i++) {
    		id = Long.valueOf(i+40);
    		PersonalTrainer pt1 = new PersonalTrainer();
    		pt1.setId(id);
    		pt1.setName("Name-"+id);
    		pt1.setSurname("Surname-"+id);
    		pt1.setPassword("password");
    		pt1.setEmail("user"+id+"@gmail.com");
    		pt1.setDateOfBirth(1997, i%12+1, i%30+1);
        	pt1.setWorkoutProgramList(wpList);
        	System.out.println("BOOOOOO: "+ pt1.getId());
    		boolean success = personalTrainerDao.save(pt1);
    		if(success)
    			System.out.println("Salvataggio riuscito");
    		else
    			System.out.println("Salvataggio fallito");
    	}
    }
    
    private void retrievePersonalTrainer() {
    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo PersonalTrainer");
    	PersonalTrainer pt1 = personalTrainerDao.findById(idTestPersonalTrainer);
    	if (pt1 != null)
    		System.out.println("Personal Trainer: " + pt1.getName());
    	else
    		System.out.println("Personal Trainer non trovato");
    	System.out.println("Provo a prendere tutti i PersonalTrainer");
    	List<PersonalTrainer> personalTrainerList = personalTrainerDao.findAll();
    	for(PersonalTrainer pt : personalTrainerList) {
    		System.out.println("Personal Trainer: "+pt.getName());
    	}
    }

    private void populateWorkoutProgram() {
    	System.out.println("Provo a salvare un workout program");
    	WorkoutProgramDao workoutProgramDao = new WorkoutProgramDao(entityManagerFactory);    	
    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
    	List<Exercise> eList = exerciseDao.findAll();
    	
    	idTestWorkoutProgram = Long.valueOf(0);
    	Long id;
    	for(int i = 0; i<3; i++) {
    		id = Long.valueOf(i);
    		WorkoutProgram wp1 = new WorkoutProgram();
    		wp1.setId(id);
    		wp1.setDifficultyLevel(i%10+1);
    		wp1.setEstimatedDuration(i%60+20);
    		wp1.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
    		wp1.setExerciseList(eList);
    		boolean success = workoutProgramDao.save(wp1);
    		if(success)
    			System.out.println("Salvataggio riuscito");
    		else
    			System.out.println("Salvataggio fallito");
    	}
    }
    
    private void retrieveWorkoutProgram() {
    	WorkoutProgramDao workoutProgramDao = new WorkoutProgramDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo WorkoutProgram");
    	WorkoutProgram wp1 = workoutProgramDao.findById(idTestWorkoutProgram);
    	if (wp1 != null)
    		System.out.println("Workout Program: " + wp1.getId());
    	else
    		System.out.println("Workout Program non trovato");
    	System.out.println("Provo a prenderle tutti gli WorkoutProgram");
    	List<WorkoutProgram> workoutProgramList = workoutProgramDao.findAll();
    	for(WorkoutProgram wp : workoutProgramList) {
    		System.out.println("Workout Program: "+ wp.getId());
    	}
    }
    
    private void populateExercise() {
    	System.out.println("Provo a salvare un esercizio");
    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
    	GymMachineDao gymMachineDao = new GymMachineDao(entityManagerFactory);
    	List<GymMachine> gymMachines = gymMachineDao.findAll();
    	GymMachine testMachine = gymMachines.get(0);
		
    	idTestExercise = Long.valueOf(10);
    	Long id;
    	for(int i = 0; i<4; i++) {
    		id = Long.valueOf(i+10);
    		System.out.println("AAAAAAA: " + id);
    		Exercise e = new Exercise();
    		e.setId(id);
    		e.setDifficultyLevel(i%10+1);
    		e.setDescription("Esercizio n° "+id);
    		e.setMachine(testMachine);
    		boolean success = exerciseDao.save(e);
    		if(success)
    			System.out.println("Salvataggio riuscito");
    		else
    			System.out.println("Salvataggio fallito");
//    		System.out.println("ID EXERCISE: " + e.getId());
    	}
    }
    
    private void retrieveExercise() {
    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo Exercise");
    	Exercise e1 = exerciseDao.findById(idTestExercise);
    	if (e1 != null)
    		System.out.println("Exercise: " + e1.getId());
    	else
    		System.out.println("Exercise non trovato");
    	System.out.println("Provo a prendere tutti gli Exercise");
    	List<Exercise> exerciseList = exerciseDao.findAll();
    	for(Exercise e : exerciseList) {
    		System.out.println("Esercizio: "+ e.getId());
    	}
    }
    
    private void populateGymMachine() {
    	System.out.println("Provo a salvare una macchina");
    	GymMachineDao gymMachineDao = new GymMachineDao(entityManagerFactory);
    	
    	idTestGymMachine = Long.valueOf(20);
    	Long id;
    	for(int i = 0; i<4; i++) {
    		id = Long.valueOf(i+20);
    		GymMachine m = new GymMachine();
    		m.setId(id);
    		m.setName("Macchinario n°"+id);
    		m.setDescription("Serve per allenarsi");
    		boolean success = gymMachineDao.save(m);
    		if(success)
    			System.out.println("Salvataggio riuscito");
    		else
    			System.out.println("Salvataggio fallito");
    	}
    }
    
    private void retrieveGymMachine() {
    	GymMachineDao gymMachineDao = new GymMachineDao(entityManagerFactory);
    	System.out.println("Provo a prendere la prima Gym Machine");
    	GymMachine g1 = gymMachineDao.findById(idTestGymMachine);
    	if (g1 != null)
    		System.out.println("Gym Machine: " + g1.getId());
    	else
    		System.out.println("Gym Machine non trovata");
    	System.out.println("Provo a prendere tutte le Gym Machine");
    	List<GymMachine> gymMachineList = gymMachineDao.findAll();
    	for(GymMachine m : gymMachineList) {
    		System.out.println("Macchinario: "+ m.getId());
    	}
    }
    
    private void retrieveCustomersFromPersonalTrainer() {
    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
    	List<PersonalTrainer> ptList = personalTrainerDao.findAll();
    	Long ptId = ptList.get(0).getId();
    	System.out.println("Provo a prendere i customer associati al PT con id: " + ptId);
    	List<Customer> customersOfPersonalTrainer = personalTrainerDao.findCustomersById(ptId);
    	for(Customer c : customersOfPersonalTrainer)
    		System.out.println("Customer: " + c.getId());
    }
    
//    private void populateWorkoutSession() throws ParseException, FileNotFoundException, IOException {
//    	System.out.println("Popolo una workout session su InfluxDB");
//    	WorkoutSessionDao wsDao = new WorkoutSessionDao();
//    	WorkoutSession ws = new WorkoutSession();
//    	ws.setId(Long.valueOf(100));
//    	ws.setDuration(60);
//    	
//    	JSONParser parser = new JSONParser();
//		Reader reader = new FileReader("src/main/java/it/unifi/dinfo/stlab/WebApp_PT_Support/app/testWorkoutSession.json");
//		JSONObject jsonObj = (JSONObject)parser.parse(reader);
//		ws.setSessionData(jsonObj);
//		
//		wsDao.buildConnection("gVYfigM83pD3n2evLZx3NI8Iv_df5S00R4kFw03oTrHoazIw1MWfEBnE-lMlOe-iULDND8w8Qrrf2_kp07rW9w==", "mainbucket", "PT-Support");
//		System.out.println("Connessione stabilita");
//		wsDao.save(ws);
//		System.out.println("Salvataggio finito");
//    }

}
