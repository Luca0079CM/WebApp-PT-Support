package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.GymMachine;

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

	@Override
	@Transactional
	public void save(GymMachine m) {
		em.persist(m);
	}

	@Override
	@Transactional
	public GymMachine findById(Long id) {
//		EntityManager em = emf.createEntityManager();
		return em.find(GymMachine.class, id);
	}

	@Override
//	@Transactional
	public List<GymMachine> findAll() {
//		EntityManager em = emf.createEntityManager();
		return em.createQuery("from GymMachine ORDER BY id DESC", GymMachine.class).getResultList();
	}

	@Override
	@Transactional
	public void update(GymMachine m) {
		em.merge(m);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		GymMachine m = findById(id);
		em.remove(em.contains(m) ? m : em.merge(m));
	}

}
