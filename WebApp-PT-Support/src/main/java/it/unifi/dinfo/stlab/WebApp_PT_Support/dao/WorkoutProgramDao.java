package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Exercise;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgram;

public class WorkoutProgramDao extends BaseDao<WorkoutProgram> {
	
//	@PersistenceContext
//	private EntityManager em;

	@Override
	@Transactional
	public void save(WorkoutProgram wp) {
		em.persist(wp);
	}

	@Override
	@Transactional
	public WorkoutProgram findById(Long id) {
		return em.find(WorkoutProgram.class, id);
	}


	@Override
	public List<WorkoutProgram> findAll() {
		return em.createQuery("SELECT wp FROM WorkoutProgram wp ORDER BY id DESC", WorkoutProgram.class).getResultList();
	}
	
	public List<WorkoutProgram> findByContainName(String name) {
		name = "%" + name + "%";
		return em.createQuery("SELECT wp FROM WorkoutProgram wp WHERE wp.name LIKE :name", WorkoutProgram.class).setParameter("name", name).getResultList();
	}
	
	public WorkoutProgram findByName(String name) {
		return em.createQuery("SELECT wp FROM WorkoutProgram wp WHERE wp.name = :name", WorkoutProgram.class).setParameter("name", name).getSingleResult();
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
