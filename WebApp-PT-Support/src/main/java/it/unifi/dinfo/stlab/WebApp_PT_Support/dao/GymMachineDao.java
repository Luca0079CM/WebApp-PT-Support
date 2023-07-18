package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import javax.enterprise.context.RequestScoped;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;

import javax.ejb.Stateless;

//@Stateless
@RequestScoped
public class GymMachineDao extends BaseDao<GymMachine> {
	
	@PersistenceContext
	private EntityManager em;
	
//	@PersistenceUnit
//	EntityManagerFactory entityManagerFactory;

//	public GymMachineDao(EntityManagerFactory emf) {
//		super(emf);
//	}
	
	@Transactional
	public void save(GymMachine m) {
		System.out.println("AAAA: ");
		em.persist(m);
	}
	
	@Transactional
	public GymMachine findById(Long id) {
//		EntityManager em = emf.createEntityManager();
		return em.find(GymMachine.class, id);
	}

	@Transactional
	public List<GymMachine> findAll() {
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from GymMachine ORDER BY id DESC", GymMachine.class).getResultList();
	}
	
	@Transactional
	public void update(GymMachine m) {
		em.merge(m);
	}
	
	@Transactional
	public void deleteById(Long id) {
		GymMachine m = findById(id);
		em.remove(em.contains(m) ? m : em.merge(m));
	}

}
