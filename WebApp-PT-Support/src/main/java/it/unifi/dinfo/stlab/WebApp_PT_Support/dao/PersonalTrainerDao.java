package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;

public class PersonalTrainerDao extends BaseDao<PersonalTrainer >{
	
	@PersistenceContext
	private EntityManager em;

//	public PersonalTrainerDao(EntityManagerFactory emf) {
//		super(emf);
//	}

	@Override
	@Transactional
	public void save(PersonalTrainer pt) {
		em.persist(pt);			
	}

	@Override
	@Transactional
	public PersonalTrainer findById(Long id) {
//		EntityManager em = emf.createEntityManager();
		return em.find(PersonalTrainer.class, id);
	}


	@Override
	public List<PersonalTrainer> findAll() {
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from PersonalTrainer " + " ORDER BY id DESC", PersonalTrainer.class).getResultList();
	}

	@Override
	@Transactional
	public void update(PersonalTrainer pt) {
		em.merge(pt);
	}

	@Override
	public void deleteById(Long id) {
		PersonalTrainer pt = findById(id);
		em.remove(em.contains(pt) ? pt : em.merge(pt));
	}

	public List<Customer> findCustomersById(Long ptId){
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Customer where personalTrainer_id = :myId", Customer.class)
				 .setParameter("myId", ptId).getResultList();
	}

}
