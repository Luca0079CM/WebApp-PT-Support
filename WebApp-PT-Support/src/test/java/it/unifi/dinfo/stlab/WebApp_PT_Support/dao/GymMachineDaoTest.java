package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;

import org.apache.commons.lang3.reflect.FieldUtils;
import java.util.Random;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.InitializationError;

import org.junit.jupiter.api.Assertions;


public class GymMachineDaoTest extends JPATest {
	
	private GymMachineDao gymMachineDao;
	private GymMachine gymMachine;

	//se voglio popolare tabelle lo faccio in questo metodo 
	@Override
	protected void init() throws InitializationError {
		gymMachine = new GymMachine();
		gymMachine.setId(new Random().nextLong());
		gymMachine.setName("Lat Machine");
		gymMachine.setDescription("Serve per allenarsi");
		em.persist(gymMachine);
		
		gymMachineDao = new GymMachineDao();
		try {
			FieldUtils.writeField(gymMachineDao, "em", em, true);
			System.out.println("BBBBB: ");
		}
		catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testSave() {
		GymMachine newGymMachine = new GymMachine();
		newGymMachine.setId(Long.valueOf(21));
		newGymMachine.setName("Lat Machine");
		newGymMachine.setDescription("Serve per allenarsi");
		gymMachineDao.save(newGymMachine);
		GymMachine retrievedGymMachine = em.createQuery("from GymMachine where id=:id", GymMachine.class)
				.setParameter("id", newGymMachine.getId()).getSingleResult();
		
		Assertions.assertEquals(newGymMachine, retrievedGymMachine);
	}
	
	@Test
	public void testFindById() {
//		GymMachine gm = new GymMachine();
//		gm.setId(Long.valueOf(22));
//		gm.setName("bibo");
//		gm.setDescription("Serve per allenarsi");
//		em.persist(gm);
//		GymMachine result = gymMachineDao.findById(gm.getId());
		GymMachine result = gymMachineDao.findById(gymMachine.getId());
		
		Assertions.assertEquals(result, gymMachine);
		Assertions.assertEquals(result.getId(), gymMachine.getId());
		Assertions.assertEquals(result.getName(), gymMachine.getName());
		Assertions.assertEquals(result.getDescription(), gymMachine.getDescription());
	}
	
	@Test
	public void testFindAll() {
		List<GymMachine> retrievedList = em.createQuery("from GymMachine", GymMachine.class).getResultList();
		List<GymMachine> resultList = gymMachineDao.findAll();
		
		Assertions.assertEquals(retrievedList.size(), resultList.size());
		Assertions.assertTrue(retrievedList.containsAll(resultList));
	}
	
	@Test
	public void testUpdate() {
		gymMachine.setName("Leg press");
		gymMachineDao.update(gymMachine);
		GymMachine result = em.createQuery("from GymMachine where name=:name", GymMachine.class).setParameter("name", "Leg press").getSingleResult();
		Assertions.assertEquals(gymMachine.getName(), result.getName());
	}

}
