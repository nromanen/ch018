package com.ch018.library.DAO;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

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
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			book.setId(id);			
			session.update(book);
			session.getTransaction().commit();
			log.info("Updated book: " + book);
		} catch (Exception e) {
			log.error("Error insert " + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
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
		Session session = null;
        Book book = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            book = (Book) session.get(Book.class, id);
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return book;
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		Session session = null;
	    try {
	    	session = HibernateUtil.getSessionFactory().openSession();
	    	session.beginTransaction();
	    	session.delete(book);
	    	session.getTransaction().commit();
	    	log.info("Deleted book: " + book);
	    } catch (Exception e) {
	    	log.error(e);
	    } finally {
	      if (session != null && session.isOpen()) {
	        session.close();
	      }
	    }
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		// TODO Auto-generated method stub
		Session session = null;
		List<Book> books = new ArrayList<Book>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("select "
					+ "B from Book B where lower(B.title) "
					+ "LIKE lower(:title)").setString("title", title);
			books.addAll(query.list());
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return books;
	}

	@Override
	public List<Book> getBooksByAuthors(String authors) {
		// TODO Auto-generated method stub
		Session session = null;
		List<Book> books = new ArrayList<Book>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("select "
					+ "B from Book B where lower(B.authors) "
					+ "LIKE lower(:authors)").setString("authors", authors);
			books.addAll(query.list());
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return books;
	}

	@Override
	public List<Book> getBooksByYear(int year) {
		// TODO Auto-generated method stub
		Session session = null;
		List<Book> books = new ArrayList<Book>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("select "
					+ "B from Book B where B.year_public "
					+ "= (:year)").setInteger("year", year);
			books.addAll(query.list());
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return books;
	}

	@Override
	public List<Book> getBooksByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
