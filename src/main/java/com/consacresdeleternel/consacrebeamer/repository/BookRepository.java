package com.consacresdeleternel.consacrebeamer.repository;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.consacresdeleternel.consacrebeamer.data.Book;

@Singleton
public class BookRepository extends BasicRepository<Book> {
	private static final Logger LOG = Logger.getLogger(BookRepository.class);

	public BookRepository() {
		super(Book.class);
	}

	public Book findByTitle(String bookTitle) {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			Query createQuery = session.createQuery("from Book b where b.title =:bookTitle");
			createQuery.setString("bookTitle", bookTitle);
			Book book = (Book) createQuery.uniqueResult();
			tx.commit();
			
			return book;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();

			LOG.error("Die suche des Lied via title konnte nicht durchgeführt werden");
			e.printStackTrace();
		}

		return null;
	}

	public void removeById(Long bookId) {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			Query createQuery = session.createQuery("delete from Song s where bookId = :bookId");
			createQuery.setParameter("bookId", bookId);
			createQuery.executeUpdate();
			
			Query createQuery2 = session.createQuery("delete from Book b where b.id = :bookId");
			createQuery2.setParameter("bookId", bookId);
			createQuery2.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}
}
