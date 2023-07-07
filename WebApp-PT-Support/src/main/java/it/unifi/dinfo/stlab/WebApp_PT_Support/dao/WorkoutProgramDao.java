package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;

public class WorkoutProgramDao extends BaseDao<WorkoutProgram>{
	public WorkoutProgramDao(EntityManagerFactory emf) {
		super(emf);
	}
	
	public boolean save(WorkoutProgram wp) {
		EntityManager em = emf.createEntityManager();
		boolean success = false;

		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(wp);
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
	
	public WorkoutProgram findOne(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(WorkoutProgram.class, id);
	}

	
	public List<WorkoutProgram> findAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from WorkoutProgram " + " ORDER BY id DESC", WorkoutProgram.class).getResultList();
	}
	
	public boolean update(WorkoutProgram wp) {
		boolean success = false;

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			// persist se l'oggetto è già presente genera un eccezione; merge invece restituisce l'oggetto se esso è
			// già presente nel db
			em.merge(wp);
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

	public boolean delete(WorkoutProgram u) {
		boolean success = false;

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			// se u è già persistente viene tolta, altrimenti viene prima salvata e poi tolta
			em.remove(em.contains(u) ? u : em.merge(u)); 
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

			WorkoutProgram wp = findOne(id);
			em.remove(em.contains(wp) ? wp : em.merge(wp));
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
