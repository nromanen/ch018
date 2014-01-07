/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;

import java.util.Collection;
import java.util.List;

public interface OrdersDAO {
	public Orders getById(int id);
	
	public void addOrder(Orders ord);

	public void deleteOrder(Orders ord);
	
	public Orders deleteOrder(int id);

	public Collection getOrdersByBooksId(int id);

	public Collection getOrdersByPersonId(int id);

	public Collection getAllOrders();
        
	public List<Book> getAllBooks(); 
  
	public List<Book> toIssueToday();
	
	public List<Book> toIssuePerHour();
        
        public boolean orderExist(int personId, int bookId);
}
