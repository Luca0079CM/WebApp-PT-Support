package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.enterprise.context.RequestScoped;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;

@RequestScoped
public class ExerciseDao extends BaseDao<Exercise> {
	
	@PersistenceContext
	private EntityManager em;
	
//	public ExerciseDao(EntityManagerFactory emf) {
//		super(emf);
//	}
	
	@Transactional
	public void save(Exercise e) {
		em.persist(e);		
	}
	
	public Exercise findById(Long id) {
//		EntityManager em = emf.createEntityManager();
		return em.find(Exercise.class, id);
	}

	
	public List<Exercise> findAll() {
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Exercise " + " ORDER BY id DESC", Exercise.class).getResultList();
	}
	
	public void update(Exercise e) {
		em.merge(e);
	}
	
	public void deleteById(Long id) {
		Exercise exercise = findById(id);
		em.remove(em.contains(exercise) ? exercise : em.merge(exercise));
	}
}
