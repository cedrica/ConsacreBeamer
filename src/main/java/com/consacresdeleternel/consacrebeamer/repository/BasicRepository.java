package com.consacresdeleternel.consacrebeamer.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BasicRepository<T> {
	private static final Logger LOG = Logger.getLogger(BasicRepository.class);
	protected static SessionFactory sessionFactory = null;
	protected Transaction tx = null;
	private Class<T> clazz;
	protected EntityManager entitymanager;

	public BasicRepository(Class<T> clazz) {
		this.clazz = clazz;
		init();
	}

	@SuppressWarnings("deprecation")
	private void init() {
		sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();

	}

	public T save(T o) {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(o);
			tx.commit();
			

			return o;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();

			LOG.error("Object konnte nicht gespeichert werden: " + e.getMessage());
			return null;
		}
		
	}

	public void delete(T o) {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			session.delete(o);
			tx.commit();
			

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();

			e.printStackTrace();
		}
	}

	public T findById(long id) {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			T entity = (T) session.get(this.clazz, id);
			tx.commit();
			
			return entity;

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();

			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			String sql = "select b from " + this.clazz.getSimpleName() + " b";
			Query query = session.createQuery(sql);
			tx.commit();
			
			return query.list();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();

			e.printStackTrace();
		}
		
		return null;
	}

}
