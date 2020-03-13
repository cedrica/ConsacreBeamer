package com.consacresdeleternel.consacrebeamer.repository;

import java.rmi.ServerException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasicRepository<T> {
	private static final Logger LOG = LoggerFactory.getLogger(BasicRepository.class);
	protected static SessionFactory sessionFactory = null;
	protected Transaction tx = null;
	private Class<T> clazz;

	public BasicRepository(Class<T> clazz) {
		this.clazz = clazz;
		init();
	}

	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	private void init() {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("manager");
			entityManager = entityManagerFactory.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Initializing of entitity manager fail: ", e.getMessage());
		}

	}
	public T save(T o) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(o);
			entityManager.flush();
			entityManager.getTransaction().commit();
			return o;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new InternalError(e.getMessage(), e);
		}
	}

	public void remove(T o){
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(o);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new InternalError(e.getMessage(), e);
		}
	}

	public T findById(long id)  {
		try {
			T result = entityManager.find(clazz, id);
			return result;
		} catch (Exception e) {
			throw new InternalError(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try {
			Query query = entityManager.createQuery("select b from " + this.clazz.getSimpleName() + " b");
			if(query.getResultList() != null) {
				for (Object o : query.getResultList()) {
					entityManager.refresh(o);
				}
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new InternalError(e.getMessage(), e);
		}
	}

}
