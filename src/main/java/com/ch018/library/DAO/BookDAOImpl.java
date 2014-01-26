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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;

// TODO: remove comments if not necessary: TODO Auto-generated method stub
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
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Book.class);	
			/*criteria.setProjection(Projections.rowCount());
		    Long resultCount = (Long)criteria.uniqueResult();
			criteria.setProjection(null);*/
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
			count = (long) sessionFactory.getCurrentSession().createCriteria(Book.class).setProjection(Projections.rowCount()).uniqueResult();
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
			Query query = sessionFactory
					.getCurrentSession()
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
            
            // TODO: bad practice to use SQL query, add comment or replace with HQL
            Query query = sessionFactory.getCurrentSession()
                           .createSQLQuery("Select * From books order by `id` DESC LIMIT 5;");
           // books.addAll(query.list());
            books = (List<Book>)query.list();
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
									+ "LIKE lower(:parametr)) OR (lower(B.genre.name) "
									+ "LIKE lower(:parametr)) order by B."+ sort +" asc")
					.setString("parametr", parametr).setMaxResults(pageSize).setFirstResult(currentPos);
			books.addAll(query.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
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
							"select " + "B from Book B where (lower(B."+searchField+") "
									+ "LIKE lower(:search)) order by B."+ sort +" asc")
					.setString("search", search).setMaxResults(pageSize).setFirstResult(currentPos);
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
									+ "LIKE lower(:parametr)) OR (lower(B.genre.name) "
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
							"select " + "B from Book B where (lower(B."+field+") "
									+ "LIKE lower(:parametr))")
					.setString("parametr", parametr);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}
}
