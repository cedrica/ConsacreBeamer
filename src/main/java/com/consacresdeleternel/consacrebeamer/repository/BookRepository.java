package com.consacresdeleternel.consacrebeamer.repository;

import javax.inject.Singleton;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consacresdeleternel.consacrebeamer.data.Book;


@Singleton
public class BookRepository extends BasicRepository<Book> {
	private static final Logger LOG = LoggerFactory.getLogger(BookRepository.class);

	public BookRepository() {
		super(Book.class);
	}

	public Book findByTitle(String bookTitle) {
//		Session session = null;
//		session = sessionFactory.openSession();
//		try {
//			tx = session.beginTransaction();
//			Query createQuery = session.createQuery("from Book b where b.title =:bookTitle");
//			createQuery.setString("bookTitle", bookTitle);
//			Book book = (Book) createQuery.uniqueResult();
//			tx.commit();
//			
//			return book;
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//
//			LOG.error("Die suche des Lied via title konnte nicht durchgef�hrt werden");
//			e.printStackTrace();
//		}
		try {
			Query createQuery = entityManager.createQuery("select from Book b where b.title =:bookTitle");
			createQuery.setParameter("bookTitle", bookTitle);
			Book book = (Book) createQuery.getSingleResult();
			return book;
		} catch (Exception e) {
			LOG.error("Die suche des Lied via title konnte nicht durchgef�hrt werden");
			e.printStackTrace();
		}
		return null;
	}

	public void removeById(Long bookId) {
//		Session session = null;
//		session = sessionFactory.openSession();
//		try {
//			tx = session.beginTransaction();
//			Query createQuery = session.createQuery("delete from Song s where bookId = :bookId");
//			createQuery.setParameter("bookId", bookId);
//			createQuery.executeUpdate();
//			
//			Query createQuery2 = session.createQuery("delete from Book b where b.id = :bookId");
//			createQuery2.setParameter("bookId", bookId);
//			createQuery2.executeUpdate();
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//		}
		
		
		try {
			Book book = entityManager.find(Book.class, bookId);
			entityManager.remove(book);
		} catch (Exception e) {
			LOG.error("Buch "+bookId+" konnte nicht gel�scht werden");
			e.printStackTrace();
		}
	}

	public void updateName(Long bookId, String title) {
//		Session session = null;
//		session = sessionFactory.openSession();
//		try {
//			tx = session.beginTransaction();
//			Query createQuery = session.createQuery("update Book set title = :title where id = :bookId");
//			createQuery.setParameter("title", title);
//			createQuery.setParameter("bookId", bookId);
//			createQuery.executeUpdate();
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//		}
		try {
			entityManager.getTransaction().begin();
			Query createQuery = entityManager.createQuery("update Book set title = :title where id = :bookId");
			createQuery.setParameter("title", title);
			createQuery.setParameter("bookId", bookId);
			createQuery.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.getTransaction() != null)
				entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}
}
