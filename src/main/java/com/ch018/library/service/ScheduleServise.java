package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.util.CalculateRating;

@Service
public class ScheduleServise {
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private PersonDao personDao;
	
	@Scheduled(cron="0 0 22 * * MON-FRI")
	@Transactional
	public void setFailedOrder() {
		Person person = new Person();
		int i = 0;
		List<Orders> orders = ordersDAO.failedOrders();
		for (Orders order : orders) {
			person = order.getPerson();
			i = person.getFailedOrders();
			i++;
			person.setFailedOrders(i);
			person.setRating(CalculateRating.getRating(i, person.getUntimelyReturns(), person.getTimelyReturns()));
			personDao.update(person);
			ordersDAO.deleteOrder(order.getId());
		}
	}
}
