package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;

@Component
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger log = LogManager.getLogger(BookDAOImpl.class);

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(book);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(book);
			log.info("Updated book: " + book);
		} catch (Exception e) {
			log.error("Error insert " + e);
		}
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		try {
			books.addAll(sessionFactory.getCurrentSession()
					.createCriteria(Book.class).addOrder(Order.asc("id"))
					.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	@Override
	public Book getBooksById(int id) {
		// TODO Auto-generated method stub
		Book book = null;
		try {
			book = (Book) sessionFactory.getCurrentSession()
					.get(Book.class, id);
		} catch (Exception e) {
			log.error(e);
		}
		return book;
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "B from Book B where lower(B.title) "
									+ "LIKE lower(%:title%)")
					.setString("title", title);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	@Override
	public List<Book> getBooksByAuthors(String authors) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "B from Book B where lower(B.authors) "
									+ "LIKE lower(%:authors%)")
					.setString("authors", authors);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	@Override
	public List<Book> getBooksByYear(int year) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "B from Book B where B.year_public "
									+ "= (:year)").setInteger("year", year);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	@Override
	public int deleteBook(int id) {
		int deleted = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("delete from Book where id=:id")
					.setInteger("id", id);
			deleted = query.executeUpdate();
		} catch (Exception e) {
			log.error(e);
			deleted = 0;
		}
		return deleted;
	}

	@Override
	public List<Book> simpleSearch(String parametr) {
		parametr = "%" + parametr + "%";
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "B from Book B where (lower(B.title) "
									+ "LIKE lower(:parametr)) OR (lower(B.authors) "
									+ "LIKE lower(:parametr)) OR (lower(B.publication) "
									+ "LIKE lower(:parametr)) OR (lower(B.genre.name) "
									+ "LIKE lower(:parametr))")
					.setString("parametr", parametr);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	 @Override
	public List<Book> paramSearch(String field, String parametr) {
		parametr = "%" + parametr + "%";
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "B from Book B where (lower(B."+field+") "
									+ "LIKE lower(:parametr))")
					.setString("parametr", parametr);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

    @Override
    public List<Book> latestArrivals() {
        List<Book> books = new ArrayList<Book>();
        try { 
            
            Query query = sessionFactory.getCurrentSession()
                           .createSQLQuery("Select * From books order by `id` DESC LIMIT 5;");
           // books.addAll(query.list());
            books = (List<Book>)query.list();
        } catch (Exception e) {
            log.error(e);
        }
        return books;
    }

}
