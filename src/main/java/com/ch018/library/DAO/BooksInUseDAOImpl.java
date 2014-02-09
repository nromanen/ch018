package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
		try {
			sessionFactory.getCurrentSession().save(booksInUse);
		} catch (Exception e) {
			log.error(e);
		}
	}

	
	@Override
	public void removeBooksInUse(int id) {
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery("deleteBookInUse")
					.setInteger("id", id);
			int g = query.executeUpdate();
		} catch (Exception e) {
			log.error(e);
		}

	}

	@Override
	public List<BooksInUse> getAllBooksInUse() {
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
		final int HOURS_PER_DAY = 23;
		final int MINUTES_PER_HOUR = 59;
		final int SECONDS_PER_MINUTE = 23;
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		startDate.setTime(returnDate);
		endDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		
		endDate.setTime(returnDate);
		endDate.set(Calendar.HOUR_OF_DAY, HOURS_PER_DAY);
		endDate.set(Calendar.MINUTE, MINUTES_PER_HOUR);
		endDate.set(Calendar.SECOND, SECONDS_PER_MINUTE);
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.between("returnDate", startDate.getTime(), endDate.getTime())).list());
		} catch (Exception e) {
			log.error(e);
		}
		return booksInUses;
	}
	
	@Override
	public Date getMinByReturnDate(int bid) {        
                List<Date> dates = new ArrayList<>();
                Date date = new Date();
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("book.id", bid));
                        criteria.setProjection(Projections.min("returnDate"));
                        dates.addAll(criteria.list());
                        date = dates.get(0);
		} catch (Exception e) {
			log.error(e);
		}
		return date;
	}

	@Override
	public List<BooksInUse> getInUse(boolean inUse) {
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
	public List<Book> getAllBooks(int currentPos, int pageSize, String sort) {
		List<Book> books = new ArrayList<>();
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.setProjection(Projections.distinct(Projections.property("book")))
					.addOrder(Order.asc("book." + sort));
			if (pageSize != 0) {
				criteria.setMaxResults(pageSize).setFirstResult(currentPos);
			}		
			books.addAll(criteria.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}
	
	@Override
	public BooksInUse getById(int id) {
		BooksInUse bookInUse = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			bookInUse = (BooksInUse) session.get(BooksInUse.class, id);
		} catch (Exception e) {
			log.error(e);
		}
		session.clear();
		return bookInUse;
	}

    @Override
    public boolean alreadyInUse(int bookId, int personId) {
         boolean exist = true;
        try {
              Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksInUse.class)
                                .add(Restrictions.eq("book.id", bookId))
                                .add(Restrictions.eq("person.id", personId));
            if (criteria.list().isEmpty())
                 exist = false;
        } catch (Exception e) {
            log.error(e);
        }
        return exist;
    }
    
    @Override
    public long countBooksInUse() {
    	long count = 0;
		try {
			count = (long) sessionFactory
					.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.setProjection(
							Projections.countDistinct("book"))
							.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
    }
    
    @Override
    public long countBooksInUseToday() {
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
    	long count = 0;
		try {
			count = (long) sessionFactory
					.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.between("returnDate", startDate.getTime(), endDate.getTime()))
					.setProjection(
							Projections.countDistinct("book"))
							.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
    }
    
    @Override
    public List<Book> getReturnBooksToday(int currentPos, int pageSize,
    		String sort) {
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
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.setProjection(Projections.distinct(Projections.property("book")))
					.addOrder(Order.asc("book." + sort)).add(Restrictions.between("returnDate", startDate.getTime(), endDate.getTime()));
			if (pageSize != 0) {
				criteria.setMaxResults(pageSize).setFirstResult(currentPos);
			}
			books.addAll(criteria.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
    }


	@Override
	public long getCountBooksByPerson(String name) {
		long count = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(*) from BooksInUse B where B.person.email=:name")
					.setString("name", name);
			count = (long) query.uniqueResult();
			
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}


	@Override
	public long getCountReturnBooksBeetweenDates(Date dateFrom, Date dateTo, int BookId) {
		long count = 0;
		try {
			Query query = sessionFactory.getCurrentSession()
					      .createQuery("select count (*) from BooksInUse B where B.book.id=:id AND B.returnDate between :start and :end");
	        query.setParameter("start", dateFrom);
			query.setParameter("end", dateTo);
			query.setParameter("id", BookId);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}


	@Override
	public void updateBooksInUse(BooksInUse inUse) {
		try { 
		     sessionFactory.getCurrentSession().update(inUse);
		     log.info("Updated booksinuse" + inUse);
		} catch (Exception e) {
			log.error(e);
		}
		
	}

}
