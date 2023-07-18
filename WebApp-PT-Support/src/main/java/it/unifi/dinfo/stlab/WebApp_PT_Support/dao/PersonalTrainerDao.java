package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;

public class PersonalTrainerDao extends BaseDao<PersonalTrainer>{
	
//	public PersonalTrainerDao(EntityManagerFactory emf) {
//		super(emf);
//	}
	
	public boolean save(PersonalTrainer pt) {
//		EntityManager em = emf.createEntityManager();
		boolean success = false;

		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(pt);
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
	
	public PersonalTrainer findById(Long id) {
//		EntityManager em = emf.createEntityManager();
		return em.find(PersonalTrainer.class, id);
	}

	
	public List<PersonalTrainer> findAll() {
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from PersonalTrainer " + " ORDER BY id DESC", PersonalTrainer.class).getResultList();
	}
	
	public boolean update(PersonalTrainer pt) {
		boolean success = false;

//		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			// persist se l'oggetto è già presente genera un eccezione; merge invece restituisce l'oggetto se esso è
			// già presente nel db
			em.merge(pt);
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
	
	public boolean deleteById(Long id) {
		boolean success = false;

//		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();

			PersonalTrainer pt = findById(id);
			em.remove(em.contains(pt) ? pt : em.merge(pt));
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
	
	public List<Customer> findCustomersById(Long ptId){
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Customer where personalTrainer_id = :myId", Customer.class)
				 .setParameter("myId", ptId).getResultList();
	}
	
}
