package com.ch018.library.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Orders;
import com.ch018.library.service.OrdersService;

public class UserOrderTermCalculate {
    
	 static OrdersService orderService;
		
	public static List<Orders> calculate(Date date){
    	List<Orders> orders = new ArrayList<Orders>();
    	orders = orderService.getAllOrdersAfter(date);
    	return orders;
    }
}
