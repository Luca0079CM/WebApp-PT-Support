package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.persistence.PersistenceContext;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;

@RequestScoped
public class PersonalTrainerDao extends BaseDao<PersonalTrainer >{
	
//	@PersistenceContext
//	private EntityManager em;

	@Override
	@Transactional
	public void save(PersonalTrainer pt) {
		em.persist(pt);			
	}

	@Override
	@Transactional
	public PersonalTrainer findById(Long id) {
		return em.find(PersonalTrainer.class, id);
	}
	
	public PersonalTrainer findByEmail(String email) {
		List<PersonalTrainer> results = em.createQuery("select pt from PersonalTrainer pt where pt.email = :email", PersonalTrainer.class).setParameter("email", email).getResultList();
		if(results.isEmpty())
			return null;
		else
			return results.get(0);
	}


	@Override
	public List<PersonalTrainer> findAll() {
		return em.createQuery("Select pt from PersonalTrainer pt ORDER BY id DESC", PersonalTrainer.class).getResultList();
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

}
