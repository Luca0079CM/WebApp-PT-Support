package it.unifi.dinfo.stlab.WebApp_PT_Support.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runners.model.InitializationError;

import com.influxdb.client.InfluxDBClient;

public abstract class JPATest {

	private static EntityManagerFactory emf;
    protected EntityManager em;
    protected InfluxDBClient influxClient;

    @BeforeAll
    public static void setupEM() {
        System.out.println("Creazione EntityManagerFactory");
        emf = Persistence.createEntityManagerFactory("test");
    }

    
    @BeforeEach
    public void setup() throws InitializationError, IOException, ParseException {
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

    
    protected abstract void init() throws InitializationError, FileNotFoundException, IOException, ParseException;

}
