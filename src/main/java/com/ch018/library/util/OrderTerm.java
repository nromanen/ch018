package com.ch018.library.util;

import java.util.Calendar;
import java.util.Set;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;

public class OrderTerm {
	public static int calculate(Book book) {
		int available = book.getAvailable();
		Set<Orders> orders = book.getOrders();
		int ordersCount = orders.size();
		Calendar today = Calendar.getInstance();
		Calendar maxOrderDate = Calendar.getInstance();
		int term = 90;
		if (available < ordersCount) {
			for (Orders order : orders) {
				if (order.getIssueDate().after(maxOrderDate.getTime())) {
					maxOrderDate.setTime(order.getIssueDate());
				}
			}
			term = maxOrderDate.get(Calendar.DATE) - today.get(Calendar.DATE) - 1;
		} 
		return term;
	}
}
