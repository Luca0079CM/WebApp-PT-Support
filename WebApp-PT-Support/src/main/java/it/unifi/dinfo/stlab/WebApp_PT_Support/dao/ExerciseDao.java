package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;

@RequestScoped
public class ExerciseDao extends BaseDao<Exercise> {

//	@PersistenceContext
//	private EntityManager em;

	@Override
	@Transactional
	public void save(Exercise e) {
		em.persist(e);
	}

	@Override
	@Transactional
	public Exercise findById(Long id) {
//		EntityManager em = emf.createEntityManager();
		return em.find(Exercise.class, id);
	}


	@Override
	public List<Exercise> findAll() {
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Exercise " + " ORDER BY id DESC", Exercise.class).getResultList();
	}

	@Override
	@Transactional
	public void update(Exercise e) {
		em.merge(e);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Exercise exercise = findById(id);
		em.remove(em.contains(exercise) ? exercise : em.merge(exercise));
	}
	
//	public GymMachine getAssociatedGymMachine(Long id) {
//			
//	}
}
