package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;

public class WorkoutProgramDao extends BaseDao<WorkoutProgram> {
	
	@PersistenceContext
	private EntityManager em;
	
//	public WorkoutProgramDao(EntityManagerFactory emf) {
//		super(emf);
//	}

	@Override
	@Transactional
	public void save(WorkoutProgram wp) {
		em.persist(wp);
	}

	@Override
	@Transactional
	public WorkoutProgram findById(Long id) {
//		EntityManager em = emf.createEntityManager();
		return em.find(WorkoutProgram.class, id);
	}


	@Override
	public List<WorkoutProgram> findAll() {
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from WorkoutProgram " + " ORDER BY id DESC", WorkoutProgram.class).getResultList();
	}

	@Override
	@Transactional
	public void update(WorkoutProgram wp) {
		em.merge(wp);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		WorkoutProgram wp = findById(id);
		em.remove(em.contains(wp) ? wp : em.merge(wp));
	}
}
