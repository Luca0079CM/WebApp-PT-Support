package it.unifi.dinfo.stlab.WebApp_PT_Support.app;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.User;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.UserDao;
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
	private User deletingUser;
	private PersonalTrainer deletingPersonalTrainer;

    @PostConstruct
    @Transactional
    public void init() {
    	entityManagerFactory = Persistence.createEntityManagerFactory("WebApp-PT-Support");
    	populateUser();
    	retrieveUser();
    	deleteUser();
    	retrieveUser();
    	populatePersonalTrainer();
    	retrievePersonalTrainer();
    	deletePersonalTrainer();
    	retrievePersonalTrainer();
    	populateWorkoutProgram();
    	retrieveWorkoutProgram();
    	populateGymMachine();
    	retrieveGymMachine();
    	populateExercise();
    	retrieveExercise();
    }
    
    private void populateUser() {
    	System.out.println("Provo a salvare un utente");
    	UserDao userDao = new UserDao(entityManagerFactory);
    	User u = new User();
    	Random random = new Random();
    	id1 = random.nextInt(1000);
    	System.out.println(id1);
    	u.setId(id1);
    	u.setName("Luca");
    	u.setSurname("Leuter");
    	u.setEmail("luca0079@hotmail.it");
    	u.setDateOfBirth(1997, 3, 8);
    	u.setPassword("password");
    	/*/
    	u.setPersonalTrainer(null);
    	u.setWorkout(null);
    	u.setWorkoutProgramList(null);
    	/*/
    	userDao.save(u);
    	int id;
    	for(int i = 0; i<4; i++) {
    		id = random.nextInt(2000);
    		User u1 = new User();
    		u1.setId(id);
    		u1.setName("Name-"+id);
    		u1.setSurname("Surname-"+id);
    		u1.setPassword("password");
    		u1.setEmail("user"+id+"@gmail.com");
    		u1.setDateOfBirth(1997, id%12+1, id%30+1);
    		boolean esito = userDao.save(u1);
    		if (esito)
    			System.out.println("Funziona");
    		else
    			System.out.println("Porca madonna");
    		if (i==3)
    			deletingUser = u1;
    	}
    	System.out.println("Salvataggio riuscito");
    }
    
    private void retrieveUser() {
    	UserDao userDao = new UserDao(entityManagerFactory);
    	System.out.println("Provo a prendere il primo utente");
    	System.out.println(id1);
    	User u1 = userDao.findOne(id1);
    	if (u1 != null)
    		System.out.println("Utente: " + u1.getName());
    	else
    		System.out.println("Utente non trovato");
    	System.out.println("Provo a prenderli tutti");
    	List<User> userList = userDao.findAll();
    	for(User u : userList) {
    		System.out.println("Utente: "+u.getName());
    	}
    }

    private void deleteUser() {
    	UserDao userDao = new UserDao(entityManagerFactory);
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
    	System.out.println(id1);
    	pt.setId(id1);
    	pt.setName("Luca");
    	pt.setSurname("Leuter - versione PT");
    	pt.setEmail("luca0079@hotmail.it");
    	pt.setDateOfBirth(1997, 3, 8);
    	pt.setPassword("password");
    	/*/
    	altri setter 
    	/*/
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
    	System.out.println(id1);
    	wp.setId(id1);
    	wp.setDifficultyLevel(2);
    	wp.setEstimatedDuration(60);
    	wp.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
    	/*/
    	u.setPersonalTrainer(null);
    	u.setWorkout(null);
    	u.setWorkoutProgramList(null);
    	/*/
    	workoutProgramDao.save(wp);
    	int id;
    	for(int i = 0; i<4; i++) {
    		id = random.nextInt(2000);
    		WorkoutProgram wp1 = new WorkoutProgram();
    		wp1.setId(id);
    		wp1.setDifficultyLevel(id%10+1);
    		wp1.setEstimatedDuration(id%60+20);
    		wp1.setWorkoutProgramType(WorkoutProgramType.CALISTHENICS);
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
