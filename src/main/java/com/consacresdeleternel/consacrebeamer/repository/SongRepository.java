package com.consacresdeleternel.consacrebeamer.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consacresdeleternel.consacrebeamer.data.Schedule;
import com.consacresdeleternel.consacrebeamer.data.Song;

@Singleton
public class SongRepository extends BasicRepository<Song> {

	private static final Logger LOG = LoggerFactory.getLogger(SongRepository.class);

	public SongRepository() {
		super(Song.class);
	}

	/**
	 * Never use cascade.ALL to remove to-many list. take a look
	 * here(https://www.thoughts-on-java.org/avoid-cascadetype-delete-many-assocations/)
	 * and read carefully to understand why
	 */
	public void remove(Song song) {
		try {

			entityManager.getTransaction().begin();
			List<Schedule> schedules = song.getSchedules();
			String sql = schedules.toString().replace("[", "").replace("]", "");
			if (!sql.trim().isEmpty()) {
				LOG.info("Schedule list: "+schedules.toString());
				Query query = entityManager
						.createNativeQuery("delete from schedule_song where scheduleId in (" + sql + ")");
				query.executeUpdate();
			}
			entityManager.remove(entityManager.contains(song) ? song : entityManager.merge(song));
			LOG.info("Song removed");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public Song findByTitle(String songTitle) {
		// Session session = null;
		// session = sessionFactory.openSession();
		// try {
		// tx = session.beginTransaction();
		// Query createQuery = session.createQuery("from Song s where
		// s.songTitle = :songTitle");
		// createQuery.setString("songTitle", songTitle);
		// Song song = (Song) createQuery.uniqueResult();
		// tx.commit();
		//
		// return song;
		// } catch (Exception e) {
		// if (tx != null)
		// tx.rollback();
		// LOG.error("Die suche des Lied via title konnte nicht durchgef�hrt
		// werden");
		// e.printStackTrace();
		// }
		//

		try {
			Query createQuery = entityManager.createQuery("select s from Song s where s.songTitle = :songTitle");
			createQuery.setParameter("songTitle", songTitle);
			List songs = createQuery.getResultList();
			if (!songs.isEmpty()) {

				return (Song) songs.get(0);
			}

		} catch (Exception e) {
			LOG.error("Die suche des Lied via title konnte nicht durchgef�hrt werden");
			e.printStackTrace();
		}

		return null;
	}

	public Song update(Song song) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(song);
			entityManager.getTransaction().commit();
			return song;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null)
				entityManager.getTransaction().rollback();
			LOG.error("Die suche des Lied via title konnte nicht durchgef�hrt werden");
			e.printStackTrace();
		}
		return null;
	}

}
