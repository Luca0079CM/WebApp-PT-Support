package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;

@RequestScoped
public class CustomerDao extends BaseDao<Customer>{

//	@PersistenceContext
//	private EntityManager em;

	@Override
	@Transactional
	public void save(Customer u) {
		em.persist(u);
	}

	@Override
	@Transactional
	public Customer findById(Long id) {
		return em.find(Customer.class, id);
	}
	
	public List<Customer> findByName(String name) {
		name = "%" + name + "%";
		return em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :name", Customer.class).setParameter("name", name).getResultList();
	}
	
	public Customer findByEmail(String email) {
		List<Customer> results = em.createQuery("select c from Customer c where c.email = :email", Customer.class).setParameter("email", email).getResultList();
		if(results.isEmpty())
			return null;
		else
			return results.get(0);
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
