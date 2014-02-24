package com.ch018.library.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.DAO.BooksInUseDAO;
import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.util.CalculateRating;
import com.ch018.library.util.Smsc;

@Service
public class ScheduleServise {
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private BooksInUseDAO booksInUseDAO;
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	Smsc sd= new Smsc();
	
	@Scheduled(cron="0 45 20 * * MON-FRI")
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
	
	@Scheduled(cron="0 0 12 * * *")
	@Transactional
	public void checkReturn() {
		Person person = new Person();
		Book book = new Book();
		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.DAY_OF_YEAR, 5);
		String message = "Its 5 days left for books: ";
		List<BooksInUse> booksInUse = booksInUseDAO.getByReturnDate(calendar.getTime());
		for (BooksInUse bookInUse : booksInUse) {
			person = bookInUse.getPerson();
			book = bookInUse.getBook();
			mailService.sendMail(person.getEmail(),	"Library email confirmation", message + bookInUse.getBook().getTitle());
			if(person.getSms()) {
				sd.send_sms("+38" + person.getCellphone(), "Don't forget to return "+book.getTitle()+ " "+bookInUse.getReturnDate()+ "!!!"
						   , 0, "", "", 0, "J Library", "");
			}
		}
	}
}
