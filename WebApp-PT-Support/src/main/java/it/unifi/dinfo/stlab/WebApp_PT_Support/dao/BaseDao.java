package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import javax.ejb.Stateless;

//@Stateless
public abstract class BaseDao<T> {
	
//	@PersistenceContext
//	public EntityManager em;

	public EntityManagerFactory emf;
	
	public BaseDao() {
		
	}
	
	public BaseDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public abstract void save(T entity);
	public abstract T findById(Long id);
	public abstract List<T> findAll();
	public abstract void update(T entity);
	public abstract void deleteById(Long id);
	
}
