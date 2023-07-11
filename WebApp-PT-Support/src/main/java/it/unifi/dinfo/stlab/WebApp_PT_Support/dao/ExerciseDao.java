package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;

public class ExerciseDao extends BaseDao<Exercise>{
	
	public ExerciseDao(EntityManagerFactory emf) {
		super(emf);
	}
	
	public boolean save(Exercise e) {
		EntityManager em = emf.createEntityManager();
		boolean success = false;

		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(e);
			em.flush();
			success = true;
			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		return success;
	}
	
	public Exercise findOne(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Exercise.class, id);
	}

	
	public List<Exercise> findAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Exercise " + " ORDER BY id DESC", Exercise.class).getResultList();
	}
	
	public boolean update(Exercise e) {
		boolean success = false;

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			// persist se l'oggetto è già presente genera un eccezione; merge invece restituisce l'oggetto se esso è
			// già presente nel db
			em.merge(e);
			success = true;

			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		return success;
	}

	public boolean delete(Exercise e) {
		boolean success = false;

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			// se u è già persistente viene tolta, altrimenti viene prima salvata e poi tolta
			em.remove(em.contains(e) ? e : em.merge(e)); 
			success = true;

			tx.commit();
		} catch (Exception ex) {
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

			Exercise exercise = findOne(id);
			em.remove(em.contains(exercise) ? exercise : em.merge(exercise));
			success = true;

			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		return success;
	}
}
