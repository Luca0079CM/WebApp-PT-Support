package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

//@Stateless
public abstract class BaseDao<T> {

	@PersistenceContext
	protected EntityManager em;

//	public EntityManagerFactory emf;

//	public BaseDao() {
//
//	}

//	public BaseDao(EntityManagerFactory emf) {
//		this.emf = emf;
//	}

	public abstract void save(T entity);
	public abstract T findById(Long id);
	public abstract List<T> findAll();
	public abstract void update(T entity);
	public abstract void deleteById(Long id);

}
