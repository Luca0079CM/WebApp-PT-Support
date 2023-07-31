package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;

@RequestScoped
public class CustomerDao extends BaseDao<Customer>{

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Customer u) {
		em.persist(u);
	}

	@Override
	public Customer findById(Long id) {
		return em.find(Customer.class, id);
	}

	@Override
	public List<Customer> findAll() {
		return em.createQuery("from Customer " + " ORDER BY id DESC", Customer.class).getResultList();
	}

	@Override
	@Transactional
	public void update(Customer u) {
		em.merge(u);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Customer user = findById(id);
		em.remove(em.contains(user) ? user : em.merge(user));
	}
}
