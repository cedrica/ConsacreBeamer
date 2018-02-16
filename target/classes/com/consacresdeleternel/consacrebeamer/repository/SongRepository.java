package com.consacresdeleternel.consacrebeamer.repository;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.consacresdeleternel.consacrebeamer.data.Song;

public class SongRepository extends BasicRepository<Song> {

	private static final Logger LOG = Logger.getLogger(SongRepository.class);

	public SongRepository() {
		super(Song.class);
	}

	public Song findByTitle(String songTitle) {

		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			Query createQuery = session.createQuery("from Song s where s.songTitle = :songTitle");
			createQuery.setString("songTitle", songTitle);
			Song song = (Song) createQuery.uniqueResult();
			tx.commit();
			
			return song;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Die suche des Lied via title konnte nicht durchgeführt werden");
			e.printStackTrace();
		}
		return null;
	}
}
