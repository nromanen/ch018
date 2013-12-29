/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author okryvortc
 */
public interface OrdersService {
    public void addOrder(Orders ord);
    public void deleteOrder(Orders ord);
    public Orders deleteOrder(int id);
    public Collection getOrdersByBooksId(int id);
    public Collection getOrdersByPersonId(int id);
    public Collection getAllOrders(); 
    List<Book> getAllBooks();
	public List<Book> toIssueToday();
	public List<Book> toIssuePerHour();
	public Orders getById(int id);
}
