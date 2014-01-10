package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;

@Component
public class BooksInUseDAOImpl implements BooksInUseDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger log = LogManager.getLogger(BooksInUseDAOImpl.class);

	@Override
	public void addBooksInUse(BooksInUse booksInUse) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(booksInUse);
		} catch (Exception e) {
			log.error(e);
		}
	}

	
	@Override
	public void removeBooksInUse(int id) {
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("delete from BooksInUse where buid=:id")
					.setInteger("id", id);
			int g = query.executeUpdate();
		} catch (Exception e) {
			log.error(e);
		}

	}

	@Override
	public List<BooksInUse> getAllBooksInUse() {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class).list());
		} catch (Exception e) {
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByPersonId(int personId) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("person.id", personId)).list());
		} catch (Exception e) {
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByBookId(int bookId) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("book.id", bookId)).list());
		} catch (Exception e) {
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByIssueDate(Date issueDate) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("issue_date", issueDate)).list());
		} catch (Exception e) {
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByReturnDate(Date returnDate) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("return_date", returnDate)).list());
		} catch (Exception e) {
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getInUse(boolean inUse) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("inUse", inUse)).list());
		} catch (Exception e) {
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		try {
			books.addAll(sessionFactory
					.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.setProjection(
							Projections.distinct(Projections.property("book")))
					.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}
	
	@Override
	public List<Book> getReturnBooksToday() {
		final int HOURS_PER_DAY = 23;
		final int MINUTES_PER_HOUR = 59;
		final int SECONDS_PER_MINUTE = 23;
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		
		endDate.set(Calendar.HOUR_OF_DAY, HOURS_PER_DAY);
		endDate.set(Calendar.MINUTE, MINUTES_PER_HOUR);
		endDate.set(Calendar.SECOND, SECONDS_PER_MINUTE);
		
		List<Book> books = new ArrayList<>();
		try {
			books.addAll(sessionFactory
					.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Expression.ge("returnDate", startDate.getTime()))
					.add(Expression.le("returnDate", endDate.getTime()))
					.setProjection(
							Projections.distinct(Projections.property("book")))
					.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	@Override
	public BooksInUse getById(int id) {
		BooksInUse bookInUse = null;
		try {
			bookInUse = (BooksInUse) sessionFactory.getCurrentSession()
					.get(BooksInUse.class, id);
		} catch (Exception e) {
			log.error(e);
		}
		return bookInUse;
	}

}
