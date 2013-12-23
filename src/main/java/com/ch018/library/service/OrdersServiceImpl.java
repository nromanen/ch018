/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;

import java.util.Collection;
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

	@Override
	public List<Orders> toIssueToday() {
		// TODO Auto-generated method stub
		return ordDAO.toIssueToday();
	}

	@Override
	public List<Orders> toIssuePerHour() {
		// TODO Auto-generated method stub
		return ordDAO.toIssuePerHour();
	}
    
}
