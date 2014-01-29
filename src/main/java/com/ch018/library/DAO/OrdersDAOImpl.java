package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;

/**
 * 
 * @author okryvortc
 */

@Component
public class OrdersDAOImpl implements OrdersDAO {

	private static Logger log = LogManager.getLogger(GenreDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void addOrder(Orders ord) {
		try {
			sessionFactory.getCurrentSession().save(ord);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void deleteOrder(Orders ord) {
		try {
			sessionFactory.getCurrentSession().delete(ord);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public Collection getOrdersByBooksId(int id) {
		ArrayList<Orders> ordList = new ArrayList<Orders>();
		try {
			ordList.addAll(sessionFactory.getCurrentSession()
						.createCriteria(Orders.class)
						.add(Restrictions.eq("book.id", id)).list());
		} catch (Exception e) {
			log.error(e);
		}

		return ordList;
	}

	public Collection getOrdersByPersonId(int id) {
		ArrayList<Orders> result = new ArrayList<Orders>();
		try {
			result.addAll(sessionFactory.getCurrentSession().createCriteria(Orders.class)
                                      .add(Restrictions.eq("person.id", id)).list());
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	public Collection getAllOrders() {
		ArrayList<Orders> result = new ArrayList<Orders>();
		try {
			result.addAll(sessionFactory.getCurrentSession()
					.createCriteria(Orders.class).list());
		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		try {
			books.addAll(sessionFactory
					.getCurrentSession()
					.createCriteria(Orders.class)
					.setProjection(
							Projections.distinct(Projections.property("book")))
					.list());
		} catch (Exception e) {
			log.error(e);
		}
		return books;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Book> toIssueToday() {
		//Date startDate = new Date();
		//Date endDate = new Date();
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		
		
		
		List<Book> books = new ArrayList<Book>();
		try {
			books.addAll(sessionFactory
					.getCurrentSession()
					.createCriteria(Orders.class)
					.add(Expression.ge("issueDate",startDate.getTime()))
					.add(Expression.le("issueDate",endDate.getTime()))
					.setProjection(Projections.distinct(Projections.property("book")))
					.list());
		} catch (Exception e) {
			log.error(e);
		}

		return books;
	}

	@Override
	public List<Book> toIssuePerHour() {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		

		endDate.add(Calendar.HOUR_OF_DAY, 1);
		
		List<Book> books = new ArrayList<Book>();
		try {
			books.addAll(sessionFactory
					.getCurrentSession()
					.createCriteria(Orders.class)
					.add(Expression.ge("issueDate",startDate))
					.add(Expression.le("issueDate",endDate))
					.setProjection(Projections.distinct(Projections.property("book")))
					.list());
		} catch (Exception e) {
			log.error(e);
		}

		return books;
	}

	@Override
	public Orders getById(int id) {
		Orders orders = null;
		try {
			orders = (Orders) sessionFactory.getCurrentSession()
					.get(Orders.class, id);
		} catch (Exception e) {
			System.out.println(e);
			log.error(e);
		}
		return orders;
	}

	@Override
	public Orders deleteOrder(int id) {
		Orders order = getById(id);
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("delete from Orders where id=:id")
					.setInteger("id", id);
			int g = query.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
		return order;
	}

    @Override
    public boolean orderExist(int personId, int bookId) {
        boolean exist = true;
        try {
            Query query = sessionFactory.getCurrentSession()
                          .createSQLQuery("SELECT * FROM orders WHERE Person_id=:person And Books_id=:book")
                          .setParameter("person", personId)
                          .setParameter("book", bookId);
            if (query.list().isEmpty())
                 exist = false;
        } catch (Exception e) {
            log.error(e);
        }
        return exist;
    }

    
    @Override
    public Date minOrderDateOf(int bookId) {
       List<Date> dates = new ArrayList<>();
                Date date = new Date();
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(Orders.class)
					.add(Restrictions.eq("book.id", bookId));
                        criteria.setProjection(Projections.min("issueDate"));
                        dates.addAll(criteria.list());
                        date = dates.get(0);
		} catch (Exception e) {
			log.error(e);
		}
		return date;
    }
    
    @Override
    public long countOrdersToday() {
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
					.createCriteria(Orders.class)
					.add(Restrictions.between("issueDate", startDate.getTime(), endDate.getTime()))
					.setProjection(
							Projections.distinct(Projections.property("book")))
							.setProjection(Projections.rowCount())
							.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
    }
    
    @Override
    public List<Book> toIssueToday(int currentPos, int pageSize, String sort) {
    	Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);

		List<Book> books = new ArrayList<Book>();
		try {
			books.addAll(sessionFactory
					.getCurrentSession()
					.createCriteria(Orders.class)
					.add(Restrictions.between("issueDate", startDate.getTime(), endDate.getTime()))
					.setProjection(Projections.distinct(Projections.property("book")))
					.setMaxResults(pageSize).setFirstResult(currentPos).addOrder(Order.asc("book." + sort))
					.list());
		} catch (Exception e) {
			log.error(e);
		}

		return books;
    }
    
    @Override
    public long countOrdersPerHour() {
    	Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.HOUR_OF_DAY, 1);
		long count = 0;
		try {
			count = (long) sessionFactory
					.getCurrentSession()
					.createCriteria(Orders.class)
					.add(Restrictions.between("issueDate", startDate.getTime(), endDate.getTime()))
					.setProjection(
							Projections.distinct(Projections.property("book")))
							.setProjection(Projections.rowCount())
							.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
    }
    
    @Override
    public List<Book> toIssuePerHour(int currentPos, int pageSize, String sort) {
    	Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.HOUR_OF_DAY, 1);
		List<Book> books = new ArrayList<Book>();
		try {
			books.addAll(sessionFactory
					.getCurrentSession()
					.createCriteria(Orders.class)
					.add(Restrictions.between("issueDate", startDate.getTime(), endDate.getTime()))
					.setProjection(Projections.distinct(Projections.property("book")))
					.list());
		} catch (Exception e) {
			log.error(e);
		}

		return books;
    }

	@Override
	public void updateOrder(Orders ord) {
		try {
			sessionFactory.getCurrentSession().update(ord);		
			log.info("Updated order: " + ord);
		} catch(Exception e){
		    log.error("Error insert " + e);
		}
		
	}

	@Override
	public long getCountOrdersByPerson(String name) {
		long count = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(*) from Orders O where O.person.email=:name")
					.setString("name", name);
			count = (long) query.uniqueResult();
		} catch(Exception e){
			log.error(e);
		}
		return count;
	}

	@Override
	public long getCountOrdersBookBeetweenDates(Date dateFrom, Date dateTo,
			int BookId) {
		long count = 0;
		try {
			Query query = sessionFactory.getCurrentSession()
					      .createQuery("select count (*) from Orders O where O.book.id=:id AND O.issueDate between :start and :end");
	        query.setParameter("start", dateFrom);
			query.setParameter("end", dateTo);
			query.setParameter("id", BookId);
			count = (long) query.uniqueResult();
		} catch (Exception e) {
			log.error(e);
		}
		return count;
			}

}
