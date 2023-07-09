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
	private int id1;
	private Customer deletingUser;
	private PersonalTrainer deletingPersonalTrainer;

    @PostConstruct
    @Transactional
    public void init() {
    	entityManagerFactory = Persistence.createEntityManagerFactory("WebApp-PT-Support");
    	
    	// Popola Tabelle
    	populateGymMachine();
    	populateExercise();
    	populateWorkoutProgram();
    	populateCustomer();
    	populatePersonalTrainer();
    	    	
    	// Prendi elementi ed elimina
    	retrieveUser();
    	deleteUser();
    	retrieveUser();
    	retrievePersonalTrainer();
    	deletePersonalTrainer();
    	retrievePersonalTrainer();
    	retrieveWorkoutProgram();
    	retrieveGymMachine();
    	retrieveExercise();
    }
    
    private void populateCustomer() {
    	System.out.println("Provo a salvare un utente");
    	CustomerDao userDao = new CustomerDao(entityManagerFactory);
    	Customer c = new Customer();
    	Random random = new Random();
    	id1 = random.nextInt(1000);
    	
    	WorkoutProgramDao wpDao = new WorkoutProgramDao(entityManagerFactory);
    	PersonalTrainerDao ptDao = new PersonalTrainerDao(entityManagerFactory);
    	List<WorkoutProgram> wpList = wpDao.findAll();
    	List<PersonalTrainer> ptList = ptDao.findAll();

    	c.setId(id1);
    	c.setName("Luca");
    	c.setSurname("Leuter");
    	c.setEmail("luca0079@hotmail.it");
    	c.setDateOfBirth(1997, 3, 8);
    	c.setPassword("password");
    	c.setPersonalTrainer(ptList.get(0));
    	c.setWorkoutProgramList(wpList);
  
    	userDao.save(c);
    	int id;
    	for(int i = 0; i<4; i++) {
    		id = random.nextInt(2000);
    		Customer c1 = new Customer();
    		c1.setId(id);
    		c1.setName("Name-"+id);
    		c1.setSurname("Surname-"+id);
    		c1.setPassword("password");
    		c1.setEmail("user"+id+"@gmail.com");
    		c1.setDateOfBirth(1997, id%12+1, id%30+1);
    		c1.setPersonalTrainer(ptList.get(0));
        	c1.setWorkoutProgramList(wpList);
    		boolean esito = userDao.save(c1);
    		if (esito)
    			System.out.println("Funziona");
    		else
    			System.out.println("Porca madonna");
    		if (i==3)
    			deletingUser = c1;
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveUser() {
    	CustomerDao userDao = new CustomerDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo utente");
    	System.out.println(id1);
    	Customer u1 = userDao.findOne(id1);
    	if (u1 != null)
    		System.out.println("Utente: " + u1.getName());
    	else
    		System.out.println("Utente non trovato");
    	System.out.println("Provo a prenderli tutti");
    	List<Customer> userList = userDao.findAll();
    	for(Customer u : userList) {
    		System.out.println("Utente: "+u.getName());
    	}
    }

    private void deleteUser() {
    	CustomerDao userDao = new CustomerDao(entityManagerFactory);
    	System.out.println("Provo ad eliminare l'utente con id "+ id1 + " (Luca)");
    	userDao.deleteById(id1);
    	System.out.println("Successo");
    	System.out.println("Ora provo ad eliminare l'utente che mi sono salvato per ultimo durante la creazione");
    	userDao.delete(deletingUser);
    	System.out.println("Successo");
    }
    
    private void populatePersonalTrainer() {
    	System.out.println("Provo a salvare un personal trainer");
    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
    	PersonalTrainer pt = new PersonalTrainer();
    	Random random = new Random();
    	id1 = random.nextInt(1000);
    	
    	CustomerDao cDao = new CustomerDao(entityManagerFactory);
    	WorkoutProgramDao wpDao = new WorkoutProgramDao(entityManagerFactory);
    	List<Customer> cList = cDao.findAll();
    	List<WorkoutProgram> wpList = wpDao.findAll();
    	
    	pt.setId(id1);
    	pt.setName("Luca");
    	pt.setSurname("Leuter - versione PT");
    	pt.setEmail("luca0079@hotmail.it");
    	pt.setDateOfBirth(1997, 3, 8);
    	pt.setPassword("password");
    	pt.setCustomersList(cList);
    	pt.setWorkoutProgramList(wpList);
    	personalTrainerDao.save(pt);
    	
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
    		if (i==3)
    			deletingPersonalTrainer = pt1;
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrievePersonalTrainer() {
    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo PersonalTrainer");
    	System.out.println(id1);
    	PersonalTrainer pt1 = personalTrainerDao.findOne(id1);
    	if (pt1 != null)
    		System.out.println("Personal Trainer: " + pt1.getName());
    	else
    		System.out.println("Personal Trainer non trovato");
    	System.out.println("Provo a prenderli tutti");
    	List<PersonalTrainer> personalTrainerList = personalTrainerDao.findAll();
    	for(PersonalTrainer pt : personalTrainerList) {
    		System.out.println("Personal Trainer: "+pt.getName());
    	}
    }
    
    private void deletePersonalTrainer() {
    	PersonalTrainerDao personalTrainerDao = new PersonalTrainerDao(entityManagerFactory);
    	System.out.println("Provo ad eliminare il personal trainer con id "+ id1 + " (Luca)");
    	personalTrainerDao.deleteById(id1);
    	System.out.println("Successo");
    	System.out.println("Ora provo ad eliminare il personal trainer che mi sono salvato per ultimo durante la creazione");
    	personalTrainerDao.delete(deletingPersonalTrainer);
    	System.out.println("Successo");
    }

    private void populateWorkoutProgram() {
    	System.out.println("Provo a salvare un workout program");
    	WorkoutProgramDao workoutProgramDao = new WorkoutProgramDao(entityManagerFactory);
    	WorkoutProgram wp = new WorkoutProgram();
    	Random random = new Random();
    	id1 = random.nextInt(1000);
    	
    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
    	List<Exercise> eList = exerciseDao.findAll();
    	
    	wp.setId(id1);
    	wp.setDifficultyLevel(2);
    	wp.setEstimatedDuration(60);
    	wp.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
    	wp.setExerciseList(eList);

    	workoutProgramDao.save(wp);
    	int id;
    	for(int i = 0; i<4; i++) {
    		id = random.nextInt(2000);
    		WorkoutProgram wp1 = new WorkoutProgram();
    		wp1.setId(id);
    		wp1.setDifficultyLevel(id%10+1);
    		wp1.setEstimatedDuration(id%60+20);
    		wp1.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
    		wp1.setExerciseList(eList);
    		workoutProgramDao.save(wp1);
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveWorkoutProgram() {
    	WorkoutProgramDao workoutProgramDao = new WorkoutProgramDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo Workout Program");
    	System.out.println(id1);
    	WorkoutProgram wp1 = workoutProgramDao.findOne(id1);
    	if (wp1 != null)
    		System.out.println("Workout Program: " + wp1.getId());
    	else
    		System.out.println("Workout Program non trovato");
    	System.out.println("Provo a prenderli tutti");
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
    		exerciseDao.save(e);
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveExercise() {
    	ExerciseDao exerciseDao = new ExerciseDao(entityManagerFactory);
    	System.out.println("Provo a prenderli tutti");
    	List<Exercise> exerciseList = exerciseDao.findAll();
    	for(Exercise e1 : exerciseList) {
    		System.out.println("Esercizio: "+ e1.getId());
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
    		gymMachineDao.save(m);
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveGymMachine() {
    	GymMachineDao gymMachineDao = new GymMachineDao(entityManagerFactory);
    	System.out.println("Provo a prenderli tutti");
    	List<GymMachine> gymMachineList = gymMachineDao.findAll();
    	for(GymMachine m : gymMachineList) {
    		System.out.println("Macchinario: "+ m.getId());
    	}
    }

}
