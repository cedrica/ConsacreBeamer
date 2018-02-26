package com.consacresdeleternel.consacrebeamer.repository;

import java.util.List;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.consacresdeleternel.consacrebeamer.data.Song;

@Singleton
public class SongRepository extends BasicRepository<Song> {

	private static final Logger LOG = Logger.getLogger(SongRepository.class);

	public SongRepository() {
		super(Song.class);
	}

	public Song findByTitle(String songTitle) {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			Query createQuery = session.createQuery("from Song s where s.songTitle = :songTitle");
			createQuery.setString("songTitle", songTitle);
			Song song = (Song) createQuery.uniqueResult();
			tx.commit();
			
			return song;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Die suche des Lied via title konnte nicht durchgeführt werden");
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Song> findByBookId(Long songId) {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			Query createQuery = session.createQuery("from Song s where s.songId = :songId");
			createQuery.setParameter("songId", songId);
			List<Song> list = createQuery.list();
			tx.commit();
			
			return list;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public void removeById(Long songId) {
		Session session = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			Query createQuery = session.createQuery("delete from Song s where s.id = :songId");
			createQuery.setParameter("songId", songId);
			createQuery.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}
	
}
