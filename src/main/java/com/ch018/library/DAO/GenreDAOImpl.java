package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Genre;

@Component
public class GenreDAOImpl implements GenreDAO {

	static Logger log = LogManager.getLogger(GenreDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addGenre(Genre genre) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(genre);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public void updateGenre(int id, Genre genre) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Genre> getAllGenres() {
		// TODO Auto-generated method stub
		List<Genre> bookcase = new ArrayList<>();
		try {
			bookcase.addAll(sessionFactory.getCurrentSession()
					.createCriteria(Genre.class).list());
		} catch (Exception e) {
			log.error(e);
			System.out.println(e);
		}
		return bookcase;
	}

	@Override
	public Genre getGenreById(int id) {
		// TODO Auto-generated method stub
		Genre genre = null;
		try {
			genre = (Genre) sessionFactory.getCurrentSession().get(Genre.class,
					id);
		} catch (Exception e) {
			System.out.println(e);
			log.error(e);
		}
		return genre;
	}

	@Override
	public Genre getGenreByName(String name) {
		// TODO Auto-generated method stub
		Genre genre = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "G from Genre G where lower(G.name) "
									+ "LIKE lower(:name)")
					.setString("name", name);
			genre = (Genre) query;
		} catch (Exception e) {
			System.out.println(e);
			log.error(e);
		}
		return genre;
	}

	@Override
	public void deleteGenre(Genre genre) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
