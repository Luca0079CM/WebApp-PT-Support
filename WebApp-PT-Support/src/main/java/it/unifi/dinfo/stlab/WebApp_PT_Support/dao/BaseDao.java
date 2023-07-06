package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

public abstract class BaseDao<T> {
	public EntityManagerFactory emf;
	
	public BaseDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public abstract boolean save(T entity);
	public abstract T findOne(int id);
	public abstract List<T> findAll();
	public abstract boolean update(T entity);
	public abstract boolean delete(T entity);
	public abstract boolean deleteById(int id);
	
}
