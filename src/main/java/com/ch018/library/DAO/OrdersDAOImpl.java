/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author okryvortc
 */

@Component
public class OrdersDAOImpl implements OrdersDAO {

	static Logger log = LogManager.getLogger(GenreDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

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
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from orders where Person_id=:id");
			query.setParameter("id", id);
			result.addAll(query.list());
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

	@Override
	public List<Book> toIssueToday() {
		Date startDate = new Date();
		Date endDate = new Date();
		
		startDate.setHours(0);
		startDate.setMinutes(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		
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
	public List<Book> toIssuePerHour() {
		Date startDate = new Date();
		Date endDate = new Date();
		
		endDate.setHours(startDate.getHours()+1);
		
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

}
