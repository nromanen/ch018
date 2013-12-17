package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.util.HibernateUtil;

@Component
public class GenreDAOImpl implements GenreDAO {
	
	static Logger log = LogManager.getLogger(GenreDAOImpl.class);
	
	@Override
	public void addGenre(Genre genre) {
		// TODO Auto-generated method stub
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(genre);
            session.getTransaction().commit();
        } catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
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
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            bookcase.addAll(session.createCriteria(Genre.class).list());
        } catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
		return bookcase;
	}

	@Override
	public Genre getGenreById(int id) {
		// TODO Auto-generated method stub
		return null;
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
