package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CustomerDao extends BaseDao<Customer>{
	
	public CustomerDao(EntityManagerFactory emf) {
		super(emf);
	}
	
	public boolean save(Customer u) {
		EntityManager em = emf.createEntityManager();
		boolean success = false;

		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(u);
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
	
	public Customer findById(Long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Customer.class, id);
	}

	
	public List<Customer> findAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Customer " + " ORDER BY id DESC", Customer.class).getResultList();
	}
	
	public boolean update(Customer u) {
		boolean success = false;

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			// persist se l'oggetto è già presente genera un eccezione; merge invece restituisce l'oggetto se esso è
			// già presente nel db
			em.merge(u);
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

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();

			Customer user = findById(id);
			em.remove(em.contains(user) ? user : em.merge(user));
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
