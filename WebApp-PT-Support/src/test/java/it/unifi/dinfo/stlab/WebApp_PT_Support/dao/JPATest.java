package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.runners.model.InitializationError;

public abstract class JPATest {
	
	private static EntityManagerFactory emf;
    protected EntityManager em;
    
    @BeforeAll
    public static void setupEM() {
        System.out.println("Creazione EntityManagerFactory");
        emf = Persistence.createEntityManagerFactory("test");
    }
    
    @BeforeEach
    public void setup() throws InitializationError {
        System.out.println("Creazione EntityManager");
        em = emf.createEntityManager(); 										
//        em.getTransaction().begin();          										
//        em.createNativeQuery("TRUNCATE SCHEMA public AND COMMIT").executeUpdate();   
//        em.getTransaction().commit();        										
        em.getTransaction().begin();         										
        System.out.println("invocazione metodo custom di init");
        init();                                         										
        em.getTransaction().commit();        										
        em.clear();                          										
        em.getTransaction().begin();  
        System.out.println("setup completato");
    }
    
    @AfterEach
    public void close(){
        if(em.getTransaction().isActive()) {
            em.getTransaction().rollback();  		
        }
        System.out.println("Chiusura EntityManager");
        em.close();                          		
    }
    
    @AfterAll
    public static void tearDownDB() {
        System.out.println("Chiusura EntityManagerFactory");
        emf.close();                              		
    }

    protected abstract void init() throws InitializationError;

}
