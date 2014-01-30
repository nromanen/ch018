package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.form.AdvancedSearch;

// TODO: use NamedQuery somewhere
// TODO: change line length limit to 120 or 150 characters to avoid poor formatting
@Component
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger log = LogManager.getLogger(BookDAOImpl.class);

	@Override
	public void addBook(Book book) {
		try {
			sessionFactory.getCurrentSession().save(book);
		} catch (Exception e) {
			log.error("Error insert book: " + e);
		}
	}

	@Override
	public void updateBook(Book book) {
		try {
			sessionFactory.getCurrentSession().update(book);
			log.info("Updated book: " + book);
		} catch (Exception e) {
			log.error("Error insert " + e);
		}
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		try {
			books.addAll(sessionFactory.getCurrentSession()
					.createCriteria(Book.class).addOrder(Order.asc("id"))
					.list());
		} catch (Exception e) {
			log.error("Error getting all books: " + e.getMessage());
		}
		return books;
	}

	@Override
	public List<Book> getAllBooks(int currentPos, int pageSize, String sort) {
		List<Book> books = new ArrayList<>();
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(Book.class);
			criteria.addOrder(Order.asc(sort));
			criteria.setMaxResults(pageSize);
			criteria.setFirstResult(currentPos);
			books.addAll(criteria.list());
		} catch (Exception e) {
			log.error("Error getting all books: " + e.getMessage());
		}
		for (Book book2 : books) {
			Hibernate.initialize(book2.getGenre());
		}
		return books;
	}

	@Override
	public long countBooks() {
		long count = 0;
		try {
			count = (long) sessionFactory.getCurrentSession()
					.createCriteria(Book.class)
					.setProjection(Projections.rowCount()).uniqueResult();
		} catch (Exception e) {
			log.error("Error getting count of books: " + e.getMessage());
		}
		return count;
	}

	@Override
	public Book getBooksById(int id) {
		Book book = null;
		try {
			book = (Book) sessionFactory.getCurrentSession()
					.get(Book.class, id);
		} catch (Exception e) {
			log.error("Error get book: " + e);
		}
		return book;
	}

	@Override
	public Book getBooksByIdWithUses(int id) {
		Book book = null;
		try {
			book = (Book) sessionFactory.getCurrentSession()
					.get(Book.class, id);
		} catch (Exception e) {
			log.error("Error get book: " + e);
		}
		Hibernate.initialize(book.getBooksinuses());
		return book;
	}

	@Override
	public Book getBooksByIdWithOrders(int id) {
		Book book = null;
		try {
			book = (Book) sessionFactory.getCurrentSession()
					.get(Book.class, id);
		} catch (Exception e) {
			log.error("Error get book: " + e);
		}
		Hibernate.initialize(book.getOrders());
		return book;
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
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
			log.error("Error get Books By Title: " + e);
		}
		return books;
	}

	@Override
	public List<Book> getBooksByAuthors(String authors) {
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
			log.error("Error get Books By Author: " + e);
		}
		return books;
	}

	@Override
	public List<Book> getBooksByYear(int year) {
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "B from Book B where B.year_public "
									+ "= (:year)").setInteger("year", year);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error("Error get Books By year: " + e);
		}
		return books;
	}

	@Override
	public int deleteBook(int id) {
		int deleted = 0;
		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("delete from Book where id=:id")
					.setInteger("id", id);
			deleted = query.executeUpdate();
		} catch (Exception e) {
			log.error("Error delete Book: " + e);
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
							"select "
									+ "B from Book B where (lower(B.title) "
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
							"select " + "B from Book B where (lower(B." + field
									+ ") " + "LIKE lower(:parametr))")
					.setString("parametr", parametr);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	@Override
	public List<Book> simpleSearch(String parametr, int currentPos,
			int pageSize, String sort) {
		parametr = "%" + parametr + "%";
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select B from Book B where (lower(B.title) "
									+ "LIKE lower(:parametr)) OR (lower(B.authors) "
									+ "LIKE lower(:parametr)) OR (lower(B.publication) "
									+ "LIKE lower(:parametr)) order by B."
									+ sort + " asc")
					.setString("parametr", parametr).setMaxResults(pageSize)
					.setFirstResult(currentPos);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}
	
	@Override
	public List<Book> advancedSearch(AdvancedSearch search, int currentPos,
			int pageSize) {
		String title = "%" + search.getTitle() + "%";
		String authors = "%" + search.getAuthors() + "%";
		String publication = "%" + search.getPublication() + "%";
		int year = search.getYear();
		String qyear = "";
		if (year > 0) {
			qyear += " AND ((B.year) = ("+year+"))";
		}
		boolean available = search.getAvailable();
		
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select B from Book B where (lower(B.title) LIKE lower(:title)) "
							+ "AND (lower(B.authors) LIKE lower(:authors)) "
							+ "AND (lower(B.publication) LIKE lower(:publication))"
							+ qyear
							+ " order by B." + search.getSortby() + " asc").setString("title", title).setString("authors", authors).setString("publication", publication).setMaxResults(pageSize).setFirstResult(currentPos);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}
	
	@Override
	public long advancedSearchCount(AdvancedSearch search) {
		String title = "%" + search.getTitle() + "%";
		String authors = "%" + search.getAuthors() + "%";
		String publication = "%" + search.getPublication() + "%";
		int year = search.getYear();
		String qyear = "";
		String available = "";
		if (year > 0) {
			qyear += " AND ((B.year) = ("+year+"))";
		}
		if (search.getAvailable()) {
			available += " AND ((B.available) > 0)";
		}
		
		long count = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(B) from Book B where (lower(B.title) LIKE lower(:title)) "
							+ "AND (lower(B.authors) LIKE lower(:authors)) "
							+ "AND (lower(B.publication) LIKE lower(:publication))"
							+ qyear
							+ available
							+ " order by B." + search.getSortby() + " asc").setString("title", title).setString("authors", authors).setString("publication", publication);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}

	@Override
	public List<Book> paramSearch(String searchField, String search,
			int currentPos, int pageSize, String sort) {
		search = "%" + search + "%";
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "B from Book B where (lower(B."
									+ searchField + ") "
									+ "LIKE lower(:search)) order by B." + sort
									+ " asc").setString("search", search)
					.setMaxResults(pageSize).setFirstResult(currentPos);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	@Override
	public long simpleSearchCount(String parametr) {
		parametr = "%" + parametr + "%";
		long count = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(B) from Book B where (lower(B.title) "
									+ "LIKE lower(:parametr)) OR (lower(B.authors) "
									+ "LIKE lower(:parametr)) OR (lower(B.publication) "
									+ "LIKE lower(:parametr))")
					.setString("parametr", parametr);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}

	@Override
	public long paramSearchCount(String field, String parametr) {
		parametr = "%" + parametr + "%";
		long count = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select " + "B from Book B where (lower(B." + field
									+ ") " + "LIKE lower(:parametr))")
					.setString("parametr", parametr);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}
	
	@Override
	public long countBooksByGenre(String search, Integer id) {
		search = "%" + search + "%";
		long count = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select B from Book B where ((lower(B.title) LIKE lower(:parametr)) OR (lower(B.authors) LIKE lower(:parametr)) OR (lower(B.publication) LIKE lower(:parametr))) AND gid=:id ")
					.setInteger("id", id).setString("parametr", search);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}
	
	@Override
	public List<Book> getBooksByGenre(String search, Integer id, int currentPos, int pageSize,	String field) {
		search = "%" + search + "%";
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select B from Book B where ((lower(B.title) "
									+ "LIKE lower(:parametr)) OR (lower(B.authors) "
									+ "LIKE lower(:parametr)) OR (lower(B.publication) "
									// +
									// "LIKE lower(:parametr)) OR (lower(B.genre.name) "
									+ "LIKE lower(:parametr))) AND gid=:id order by B."
									+ field + " asc")
					.setString("parametr", search).setInteger("id", id).setMaxResults(pageSize)
					.setFirstResult(currentPos);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}


	@Override
	public List<Book> getBooksByRating() {
		List<Book> books = new ArrayList<Book>();
		 try {
			   Query query = sessionFactory.getCurrentSession()
					   .createQuery("Select B from Book B order by B.rating desc");
		       query.setMaxResults(5);
		       books.addAll(query.list());
		 } catch (Exception e){
			 log.error(e);
		 }
		return books;
	}
	
	@Override
	public long countBooksByGenreWithAdvSearch(AdvancedSearch search,
			Integer id) {
		String title = "%" + search.getTitle() + "%";
		String authors = "%" + search.getAuthors() + "%";
		String publication = "%" + search.getPublication() + "%";
		int year = search.getYear();
		String qyear = "";
		String available = "";
		if (year > 0) {
			qyear += " AND ((B.year) = ("+year+"))";
		}
		if (search.getAvailable()) {
			available += " AND ((B.available) > 0)";
		}
		
		long count = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(B) from Book B where (lower(B.title) LIKE lower(:title)) "
							+ "AND (lower(B.authors) LIKE lower(:authors)) "
							+ "AND (lower(B.publication) LIKE lower(:publication)) "
							+ "AND gid=:id"
							+ qyear
							+ available
							+ " order by B." + search.getSortby() + " asc").setString("title", title).setString("authors", authors).setString("publication", publication).setInteger("id", id);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}
	
	@Override
	public List<Book> getBooksByGenreWithAdvSearch(
			AdvancedSearch search, Integer id, int currentPos,
			int pageSize) {
		String title = "%" + search.getTitle() + "%";
		String authors = "%" + search.getAuthors() + "%";
		String publication = "%" + search.getPublication() + "%";
		int year = search.getYear();
		String qyear = "";
		if (year > 0) {
			qyear += " AND ((B.year) = ("+year+"))";
		}
		boolean available = search.getAvailable();
		
		List<Book> books = new ArrayList<Book>();
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select B from Book B where (lower(B.title) LIKE lower(:title)) "
							+ "AND (lower(B.authors) LIKE lower(:authors)) "
							+ "AND (lower(B.publication) LIKE lower(:publication))"
							+ "AND gid=:id"
							+ qyear
							+ " order by B." + search.getSortby() + " asc").setInteger("id", id).setString("title", title).setString("authors", authors).setString("publication", publication).setMaxResults(pageSize).setFirstResult(currentPos);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}
}
