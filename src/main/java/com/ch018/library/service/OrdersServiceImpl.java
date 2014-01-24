/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author okryvortc
 */

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private OrdersDAO ordDAO;
    
    @Transactional
    public void addOrder(Orders ord) {
       ordDAO.addOrder(ord); 
    }

    @Transactional
    public void deleteOrder(Orders ord) {
        ordDAO.deleteOrder(ord);
    }

    @Transactional
    public Collection getOrdersByBooksId(int id) {
        return ordDAO.getOrdersByBooksId(id);
    }

    @Transactional
    public Collection getOrdersByPersonId(int id) {
        return ordDAO.getOrdersByPersonId(id);
    }

    @Transactional
    public Collection getAllOrders() {
        return ordDAO.getAllOrders();
    }

	@Transactional
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return ordDAO.getAllBooks();
	}

	@Transactional
	public List<Book> toIssueToday() {
		// TODO Auto-generated method stub
		return ordDAO.toIssueToday();
	}

	@Transactional
	public List<Book> toIssuePerHour() {
		// TODO Auto-generated method stub
		return ordDAO.toIssuePerHour();
	}

	@Transactional
	public Orders getById(int id) {
		// TODO Auto-generated method stub
		return ordDAO.getById(id);
	}

	@Transactional
	public Orders deleteOrder(int id) {
		return ordDAO.deleteOrder(id);
		
	}

    @Override
    @Transactional
    public boolean orderExist(int personId, int bookId) {
        return ordDAO.orderExist(personId, bookId);
    }

 
    @Override
    @Transactional
    public Date minOrderDateOf(int bookId) {
        return ordDAO.minOrderDateOf(bookId);
    }

	@Override
	@Transactional
	public void updateOrder(Orders ord) {
		   ordDAO.updateOrder(ord);
		
	}

	@Override
	@Transactional
	public long getCountOrdersByPerson(String name) {
		return ordDAO.getCountOrdersByPerson(name);
	}
    
}
