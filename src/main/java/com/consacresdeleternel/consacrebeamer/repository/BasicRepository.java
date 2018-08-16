package com.consacresdeleternel.consacrebeamer.repository;

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
		// try{
		// sessionFactory = new
		// Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		// }catch (Exception e) {
		// e.printStackTrace();// TODO: handle exception
		// }
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("manager");
			entityManager = entityManagerFactory.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Initializing of entitity manager fail: ",e.getMessage());
		}

	}

	public T save(T o) {
		// Session session = null;
		// session = sessionFactory.openSession();
		// try {
		// tx = session.beginTransaction();
		// session.saveOrUpdate(o);
		// tx.commit();
		//
		// return o;
		// } catch (Exception e) {
		// if (tx != null)
		// tx.rollback();
		// e.printStackTrace();
		// LOG.error("Object konnte nicht gespeichert werden: " +
		// e.getMessage());
		// return null;
		// }
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(o);
			entityManager.getTransaction().commit();
			return o;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	public void remove(T o) {
		// Session session = null;
		// session = sessionFactory.openSession();
		// try {
		// tx = session.beginTransaction();
		// session.delete(o);
		// tx.commit();
		//
		// } catch (Exception e) {
		// if (tx != null)
		// tx.rollback();
		//
		// e.printStackTrace();
		// }
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(o);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public T findById(long id) {
		// Session session = null;
		// session = sessionFactory.openSession();
		// try {
		// tx = session.beginTransaction();
		// @SuppressWarnings("unchecked")
		// T entity = (T) session.get(this.clazz, id);
		// tx.commit();
		//
		// return entity;
		//
		// } catch (Exception e) {
		// if (tx != null)
		// tx.rollback();
		//
		// e.printStackTrace();
		// }
		try {
			T result = entityManager.find(clazz, id);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		// Session session = null;
		// session = sessionFactory.openSession();
		// try {
		// tx = session.beginTransaction();
		// String sql = "select b from " + this.clazz.getSimpleName() + " b";
		// Query query = session.createQuery(sql);
		// tx.commit();
		//
		// return query.list();
		//
		// } catch (Exception e) {
		// if (tx != null)
		// tx.rollback();
		// e.printStackTrace();
		// }
		try {
			Query query = entityManager.createQuery("select b from " + this.clazz.getSimpleName()+" b");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
