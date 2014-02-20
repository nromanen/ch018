package com.ch018.library.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Orders;
import com.ch018.library.service.OrdersService;
import com.ch018.library.form.OrderResult;

public class UserOrderTermCalculate {
    
	 static OrdersService orderService;
		
	public static List<Orders> calculate(Date date, int term){
    	List<Orders> orders = new ArrayList<Orders>();
    	List<OrderResult> result = new ArrayList<OrderResult>();
    	orders = orderService.getAllOrdersAfter(date, term);
    	/* Calculating order return date*/
    	Calendar c = Calendar.getInstance();
        Calendar d = Calendar.getInstance();
        c.setTime(date);
       // d.setTime(orders.get(0).getIssueDate());
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.DATE, term);
        Date expectedReturnDate = c.getTime();
    	for (Orders order: orders) {
    		if (order.getIssueDate().after(expectedReturnDate)) {
    			
    		}
    		
    	}
    	
    	return orders;
    }
}
