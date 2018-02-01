package com.consacresdeleternel.consacrebeamer.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.consacresdeleternel.consacrebeamer.data.Book;

public class BookRepository extends BasicRepository<Book> {
	private static final Logger LOG = Logger.getLogger(BookRepository.class);
	
	public BookRepository() {
		super(Book.class);
	}
	
	public Book findByTitle(String bookTitle) {

		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			Query createQuery = session.createQuery("from Book b where b.title = :bookTitle");
			createQuery.setString("bookTitle", bookTitle);
			Book book = (Book) createQuery.uniqueResult();
			tx.commit();
			return book;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Die suche des Lied via title konnte nicht durchgeführt werden");
			e.printStackTrace();
		}
		return null;
	}

}
