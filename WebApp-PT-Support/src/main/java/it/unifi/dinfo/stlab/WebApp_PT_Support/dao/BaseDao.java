package it.unifi.dinfo.stlab.WebApp_PT_Support.dao;

import java.util.List;

//@Stateless
public abstract class BaseDao<T> {

//	@PersistenceContext
//	public EntityManager em;

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
