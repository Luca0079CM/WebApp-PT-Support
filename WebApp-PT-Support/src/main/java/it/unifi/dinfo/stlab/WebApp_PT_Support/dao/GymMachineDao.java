package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;

public class GymMachineDao extends BaseDao<GymMachine>{

	public GymMachineDao(EntityManagerFactory emf) {
		super(emf);
	}
	
	public boolean save(GymMachine m) {
		EntityManager em = emf.createEntityManager();
		boolean success = false;

		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(m);
			success = true;
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
				System.out.println(e.toString());
			}
		} finally {
			em.close();
		}
		return success;
	}
	
	public GymMachine findOne(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(GymMachine.class, id);
	}

	
	public List<GymMachine> findAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from GymMachine " + " ORDER BY id DESC", GymMachine.class).getResultList();
	}
	
	public boolean update(GymMachine m) {
		boolean success = false;

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			// persist se l'oggetto è già presente genera un eccezione; merge invece restituisce l'oggetto se esso è
			// già presente nel db
			em.merge(m);
			success = true;

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		return success;
	}

	public boolean delete(GymMachine m) {
		boolean success = false;

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			// se u è già persistente viene tolta, altrimenti viene prima salvata e poi tolta
			em.remove(em.contains(m) ? m : em.merge(m)); 
			success = true;

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		return success;
	}
	
	public boolean deleteById(int id) {
		boolean success = false;

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();

			GymMachine m = findOne(id);
			em.remove(em.contains(m) ? m : em.merge(m));
			success = true;

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		return success;
	}
}
