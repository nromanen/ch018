package com.ch018.library.DAO;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;

public interface OrdersDAO {
	 Orders getById(int id);
	 void addOrder(Orders ord);
	 void deleteOrder(Orders ord);
	 void updateOrder(Orders ord);
	 Orders deleteOrder(int id);
	 Collection getOrdersByBooksId(int id);
	 Collection getOrdersByPersonId(int id);
	 Collection getAllOrders();
	 List<Book> getAllBooks(); 
	 List<Book> toIssueToday();
	 List<Orders> todayOrders();
	 List<Book> toIssuePerHour(); 
     boolean orderExist(int personId, int bookId);
     Date minOrderDateOf(int bookId);
     long countOrdersToday();
	 List<Book> toIssueToday(int currentPos, int pageSize, String sort);
	 long countOrdersPerHour();
	 List<Book> toIssuePerHour(int currentPos, int pageSize, String sort);
     long getCountOrdersByPerson(String name);
     long getCountOrdersBookBeetweenDates(Date dateFrom, Date dateTo , int BookId);
}
