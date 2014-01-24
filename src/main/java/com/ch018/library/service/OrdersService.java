/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author okryvortc
 */
public interface OrdersService {
     void addOrder(Orders ord);
     void deleteOrder(Orders ord);
     void updateOrder(Orders ord);
     Orders deleteOrder(int id);
     Collection getOrdersByBooksId(int id);
     Collection getOrdersByPersonId(int id);
     Collection getAllOrders(); 
     List<Book> getAllBooks();
     List<Book> toIssueToday();
     List<Book> toIssuePerHour();
     Orders getById(int id);
     boolean orderExist(int personId, int bookId);
     Date minOrderDateOf(int bookId);
     long getCountOrdersByPerson(String name);
}