package it.unifi.dinfo.stlab.WebApp_PT_Support.app;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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
import java.util.Random;
import java.util.List;

// classe principale per fare le prove
@Startup
@Singleton
public class App {
	EntityManagerFactory entityManagerFactory;
	private int idTestCustomer;
	private int idTestPersonalTrainer;
	private int idTestWorkoutProgram;
	private int idTestExercise;
	private int idTestGymMachine;

    @PostConstruct
    @Transactional
    public void init() {
    	entityManagerFactory = Persistence.createEntityManagerFactory("WebApp-PT-Support");
    	
    	// Popola Tabelle
    	populateGymMachine();
    	populateExercise();
    	populateWorkoutProgram();
    	populatePersonalTrainer();
    	populateCustomer();
    	
    	// Prendi elementi
    	System.out.println("Test di Query per prendere gli elementi:");
    	retrieveCustomer();
    	retrievePersonalTrainer();
    	retrieveWorkoutProgram();
    	retrieveGymMachine();
    	retrieveExercise();
    }
    
    private void populateCustomer() {
    	System.out.println("Provo a salvare un utente");
    	CustomerDao customerDao = new CustomerDao(entityManagerFactory);
    	Customer c = new Customer();
    	Random random = new Random();
    	idTestCustomer = random.nextInt(1000);
    	
    	WorkoutProgramDao wpDao = new WorkoutProgramDao(entityManagerFactory);
    	PersonalTrainerDao ptDao = new PersonalTrainerDao(entityManagerFactory);
    	List<WorkoutProgram> wpList = wpDao.findAll();
    	List<PersonalTrainer> ptList = ptDao.findAll();

    	c.setId(idTestCustomer);
    	c.setName("Luca");
    	c.setSurname("Leuter");
    	c.setEmail("luca0079@hotmail.it");
    	c.setDateOfBirth(1997, 3, 8);
    	c.setPassword("password");
    	c.setPersonalTrainer(ptList.get(0));
    	c.setWorkoutProgramList(wpList);
    	customerDao.save(c);
    	int id;
    	for(int i = 0; i<7; i++) {
    		id = random.nextInt(2000);
    		Customer c1 = new Customer();
    		c1.setId(id);
    		c1.setName("Name-"+id);
    		c1.setSurname("Surname-"+id);
    		c1.setPassword("password");
    		c1.setEmail("user"+id+"@gmail.com");
    		c1.setDateOfBirth(1997, id%12+1, id%30+1);
    		if (i == 0)
    			c1.setPersonalTrainer(ptList.get(0));
    		else if (i == 1 || i == 2)
    			c1.setPersonalTrainer(ptList.get(1));
    		else if (i == 3 || i == 4)
    			c1.setPersonalTrainer(ptList.get(2));
    		else if (i == 5 || i == 6)
    			c1.setPersonalTrainer(ptList.get(3));
        	c1.setWorkoutProgramList(wpList);
    		customerDao.save(c1);
    	}
    	
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveCustomer() {
    	CustomerDao customerDao = new CustomerDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo customer");
    	System.out.println(idTestCustomer);
    	Customer c1 = customerDao.findOne(idTestCustomer);
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
    	Random random = new Random();
    	
    	WorkoutProgramDao wpDao = new WorkoutProgramDao(entityManagerFactory);
    	List<Customer> cList = null;
    	List<WorkoutProgram> wpList = wpDao.findAll();    	
    	
    	int id;
    	for(int i = 0; i<4; i++) {
    		id = random.nextInt(2000);
    		PersonalTrainer pt1 = new PersonalTrainer();
    		pt1.setId(id);
    		pt1.setName("Name-"+id);
    		pt1.setSurname("Surname-"+id);
    		pt1.setPassword("password");
    		pt1.setEmail("user"+id+"@gmail.com");
    		pt1.setDateOfBirth(1997, id%12+1, id%30+1);
    		pt1.setCustomersList(cList);
        	pt1.setWorkoutProgramList(wpList);
    		personalTrainerDao.save(pt1);
    		if(i == 0)
    			idTestPersonalTrainer = id;
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrievePersonalTrainer() {
    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo PersonalTrainer");
    	PersonalTrainer pt1 = personalTrainerDao.findOne(idTestPersonalTrainer);
    	if (pt1 != null)
    		System.out.println("Personal Trainer: " + pt1.getName());
    	else
    		System.out.println("Personal Trainer non trovato");
    	System.out.println("Provo a prenderli tutti");
    	List<PersonalTrainer> personalTrainerList = personalTrainerDao.findAll();
    	for(PersonalTrainer pt : personalTrainerList) {
    		System.out.println("Personal Trainer: "+pt.getName());
    		System.out.println("Lista clienti: ");
    		for(Customer c : pt.getCustomersList())
    			System.out.println(c.getId());
    		System.out.println("-----------");
    	}
    }    

    private void populateWorkoutProgram() {
    	System.out.println("Provo a salvare un workout program");
    	WorkoutProgramDao workoutProgramDao = new WorkoutProgramDao(entityManagerFactory);
    	Random random = new Random();
    	
    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
    	List<Exercise> eList = exerciseDao.findAll();
    	
    	int id;
    	for(int i = 0; i<4; i++) {
    		id = random.nextInt(2000);
    		WorkoutProgram wp1 = new WorkoutProgram();
    		wp1.setId(id);
    		wp1.setDifficultyLevel(id%10+1);
    		wp1.setEstimatedDuration(id%60+20);
    		wp1.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
    		wp1.setExerciseList(eList);
    		if (i == 0)
    			idTestWorkoutProgram = id;
    		workoutProgramDao.save(wp1);
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveWorkoutProgram() {
    	WorkoutProgramDao workoutProgramDao = new WorkoutProgramDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo Workout Program");
    	WorkoutProgram wp1 = workoutProgramDao.findOne(idTestWorkoutProgram);
    	if (wp1 != null)
    		System.out.println("Workout Program: " + wp1.getId());
    	else
    		System.out.println("Workout Program non trovato");
    	System.out.println("Provo a prendere tutti gli Workout Program");
    	List<WorkoutProgram> workoutProgramList = workoutProgramDao.findAll();
    	for(WorkoutProgram wp : workoutProgramList) {
    		System.out.println("Workout Program: "+ wp.getId());
    	}
    }
    
    private void populateExercise() {
    	System.out.println("Provo a salvare un esercizio");
    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
    	Random random = new Random();
    	int id;
    	GymMachineDao gymMachineDao = new GymMachineDao(entityManagerFactory);
    	List<GymMachine> gymMachines = gymMachineDao.findAll();
    	GymMachine testMachine = gymMachines.get(0);
		
    	for(int i = 0; i<4; i++) {
    		id = random.nextInt(2000);
    		Exercise e = new Exercise();
    		e.setId(id);
    		e.setDifficultyLevel(id%10+1);
    		e.setDescription("Esercizio n° "+id);
    		e.setMachine(testMachine);
    		if (i == 0)
    			idTestExercise = id;
    		exerciseDao.save(e);
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveExercise() {
    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo Esercizio");
    	Exercise e1 = exerciseDao.findOne(idTestExercise);
    	if (e1 != null)
    		System.out.println("Esercizio: " + e1.getId());
    	else
    		System.out.println("Esercizio non trovato");
    	System.out.println("Provo a prendere tutti gli esercizi");
    	List<Exercise> exerciseList = exerciseDao.findAll();
    	for(Exercise e : exerciseList) {
    		System.out.println("Esercizio: "+ e.getId());
    	}
    }
    
    private void populateGymMachine() {
    	System.out.println("Provo a salvare una macchina");
    	GymMachineDao gymMachineDao = new GymMachineDao(entityManagerFactory);
    	Random random = new Random();
    	int id;
    	for(int i = 0; i<4; i++) {
    		id = random.nextInt(2000);
    		GymMachine m = new GymMachine();
    		m.setId(id);
    		m.setName("Macchinario n°"+id);
    		m.setDescription("Serve per allenarsi");
    		if (i == 0)
    			idTestGymMachine = id;
    		gymMachineDao.save(m);
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveGymMachine() {
    	GymMachineDao gymMachineDao = new GymMachineDao(entityManagerFactory);
    	System.out.println("Provo a prendere la prima Gym Machine");
    	GymMachine g1 = gymMachineDao.findOne(idTestGymMachine);
;    	if (g1 != null)
    		System.out.println("Gym Machine: " + g1.getId());
    	else
    		System.out.println("Gym Machine non trovata");
    	System.out.println("Provo a prendere tutte le Gym Machine");
    	List<GymMachine> gymMachineList = gymMachineDao.findAll();
    	for(GymMachine m : gymMachineList) {
    		System.out.println("Macchinario: "+ m.getId());
    	}
    }

}
