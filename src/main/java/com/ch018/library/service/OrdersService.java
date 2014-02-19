package com.ch018.library.service;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

/**
 * 
 * @author okryvortc
 */
public interface OrdersService {
	void addOrder(Orders ord);
	void deleteOrder(Orders ord);
	Orders deleteOrder(int id);
	Collection<Orders> getOrdersByBooksId(int id);
	Collection<Orders> getOrdersByPersonId(int id);
	Collection<Orders> getAllOrders();
	List<Book> getAllBooks();
	List<Book> toIssueToday(int currentPos, int pageSize, String sort);
	List<Book> toIssuePerHour();
	List<Orders> failedOrders();
	Orders getById(int id);
	boolean orderExist(int personId, int bookId);
	Date minOrderDateOf(int bookId);
	long countOrdersToday();
	long countOrdersPerHour();
	List<Book> toIssuePerHour(int currentPos, int pageSize,
			String string);
    void updateOrder(Orders ord);
    long getCountOrdersByPerson(String name);
    long getCountOrdersBookBeetweenDates(Date dateFrom, Date dateTo , int BookId);
    int updateissueDate(int id, String issueDate) throws ParseException;
    int prepareOrder(int bookID, Person pers);
    String createOrder(int bookId, int personId, Orders newOrder);
    List<Orders> getAllOrdersAfter(Date date, int bookId);
}