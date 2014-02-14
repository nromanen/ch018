package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.form.AdvancedSearch;

// TODO: use NamedQuery somewhere
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
	public List<Book> getAllBooks(int currentPos, int pageSize, String sort, boolean isAsc) {
		List<Book> books = new ArrayList<>();
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(Book.class);
			if (sort != null && isAsc) {
				criteria.addOrder(Order.asc(sort));
			}
			if (sort != null && !isAsc) {
				criteria.addOrder(Order.desc(sort));
			}
			if (pageSize != 0) {
				criteria.setFirstResult(currentPos);
				criteria.setMaxResults(pageSize);
			}
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
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			book = (Book) session.get(Book.class, id);
		} catch (Exception e) {
			log.error("Error get book: " + e);
		}
		session.clear();
		return book;
	}

	@Override
	public Book getBooksByIdWithUses(int id) {
		Book book = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			book = (Book) session.get(Book.class, id);
			
		} catch (Exception e) {
			log.error("Error get book: " + e);
		}
		Hibernate.initialize(book.getBooksinuses());
		session.clear();
		return book;
	}

	@Override
	public Book getBooksByIdWithOrders(int id) {
		Book book = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			book = (Book) session.get(Book.class, id);
		} catch (Exception e) {
			log.error("Error get book: " + e);
		}
		Hibernate.initialize(book.getOrders());
		session.clear();
		return book;
	}

	@Override
	public int deleteBook(int id) {
		int deleted = 0;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery("deleteBook")
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
	public List<Book> simpleSearch(String parametr, int currentPos,
			int pageSize, String sort, boolean isAsc) {
		parametr = "%" + parametr + "%";
		List<Book> books = new ArrayList<Book>();
		try {
			Criteria criteria = sessionFactory
					.getCurrentSession().createCriteria(Book.class);
			criteria.add(Restrictions.or(Restrictions.like("title", parametr).ignoreCase(),
					Restrictions.like("authors", parametr).ignoreCase(),
					Restrictions.like("publication", parametr).ignoreCase()));
			if (sort != null && isAsc) {
				criteria.addOrder(Order.asc(sort));
			}
			if (sort != null && !isAsc) {
				criteria.addOrder(Order.desc(sort));
			}
			if (pageSize != 0) {
				criteria.setFirstResult(currentPos);
				criteria.setMaxResults(pageSize);
			}
			books.addAll(criteria.list());
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
		List<Book> books = new ArrayList<Book>();
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
			criteria.add(Restrictions.like("title", title).ignoreCase());
			criteria.add(Restrictions.like("authors", authors).ignoreCase());
			criteria.add(Restrictions.like("publication", publication));
			if (search.getAvailable()) {
				criteria.add(Restrictions.gt("available", 0));
			}
			if (search.getYear() != null) {
				criteria.add(Restrictions.eq("year", search.getYear()));
			}
			if (search.getGenre() != 0) {
				criteria.add(Restrictions.eq("genre.id", search.getGenre()));
			}
			if (pageSize != 0) {
				criteria.setFirstResult(currentPos);
				criteria.setMaxResults(pageSize);
			}
			books.addAll(criteria.list());
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
		long count = 0;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
			criteria.add(Restrictions.like("title", title).ignoreCase());
			criteria.add(Restrictions.like("authors", authors).ignoreCase());
			criteria.add(Restrictions.like("publication", publication));
			if (search.getAvailable()) {
				criteria.add(Restrictions.gt("available", 0));
			}
			if (search.getYear() != null) {
				criteria.add(Restrictions.eq("year", search.getYear()));
			}
			if (search.getGenre() != 0) {
				criteria.add(Restrictions.eq("genre.id", search.getGenre()));
			}
			criteria.setProjection(Projections.rowCount());
			count = (long) criteria.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
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
}
