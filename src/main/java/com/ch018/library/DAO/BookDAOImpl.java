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
import com.ch018.library.entity.Person;
import com.ch018.library.util.HibernateUtil;

@Component
public class BookDAOImpl implements BookDAO {


	
	static Logger log = LogManager.getLogger(BookDAOImpl.class);

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(book);
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
	public void updateBook(int id, Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            books.addAll(session.createCriteria(Book.class).list());
        } catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
		return books;
	}

	@Override
	public Book getBooksById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByAuthors(String authors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentCount(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

}
