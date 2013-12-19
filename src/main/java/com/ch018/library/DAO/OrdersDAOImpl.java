/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Orders;
import com.ch018.library.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Session session = null;
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
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from orders where Books_id=:id");
			query.setParameter("id", id);
			ordList.addAll(query.list());
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

}
