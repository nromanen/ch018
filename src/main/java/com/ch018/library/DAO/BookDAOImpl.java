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
import com.ch018.library.entity.Person;

@Component
public class BookDAOImpl implements BookDAO {

	@Autowired
	SessionFactory sessionFactory;

	static Logger log = LogManager.getLogger(BookDAOImpl.class);

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
			System.out.println(e);
			log.error(e);
		}
		return book;
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(book);
			log.info("Deleted book: " + book);
		} catch (Exception e) {
			log.error(e);
		}
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
									+ "LIKE lower(:title)")
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
									+ "LIKE lower(:authors)")
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
	public List<Book> getBooksByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
