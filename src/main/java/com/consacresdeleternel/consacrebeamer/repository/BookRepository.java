package com.consacresdeleternel.consacrebeamer.repository;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.exceptions.BookNotFoundException;

public class BookRepository extends BasicRepository<Book> {
	private static final Logger LOG = LoggerFactory.getLogger(BookRepository.class);

	public BookRepository() {
		super(Book.class);
	}

	public Book findByTitle(String bookTitle) {
		try {
			Query createQuery = entityManager.createQuery("select b from Book b where b.title =:bookTitle");
			createQuery.setParameter("bookTitle", bookTitle);
			List<Book> books = createQuery.getResultList();
			if(books != null || books.size() > 0) {
				return books.get(0);
			}
			return null;
		} catch (Exception e) {
			LOG.error("Die suche des Lied via "+bookTitle +" konnte nicht durchgef�hrt werden");
			throw new InternalError(e.getMessage());
		}
	}

	public void removeById(Long bookId) {
		try {
			entityManager.getTransaction().begin();
			Book book = entityManager.find(Book.class, bookId);
			if(book == null) {
				throw new BookNotFoundException("Book: id " + bookId + " not Found");
			}
			entityManager.remove(book);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOG.error("Buch "+bookId+" konnte nicht gel�scht werden");
			throw new InternalError(e.getMessage());
		}
	}

	public void updateName(Long bookId, String title) {
		try {
			entityManager.getTransaction().begin();
			Query createQuery = entityManager.createQuery("update Book b set b.title = :title where b.id = :bookId");
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
