package com.ch018.library.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.DAO.BooksInUseDAO;
import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.util.CalculateRating;
import com.ch018.library.util.IConstants;
import com.ch018.library.util.Smsc;

@Service
public class BooksInUseServiceImpl implements BooksInUseService {
	
	private static Logger log = LogManager.getLogger(BooksInUseServiceImpl.class);
	
	Smsc sd= new Smsc();

	@Autowired
	private BooksInUseDAO booksInUseDAO;
	
	@Autowired 
	private PersonDao personDao;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private LocalizationService localizationService;
	
	@Override
	@Transactional
	public void addBooksInUse(int days, int orderId) {
		Calendar issueDate = Calendar.getInstance();
		Calendar returnDate = Calendar.getInstance();
		issueDate.set(Calendar.MINUTE, 0);
		issueDate.set(Calendar.SECOND, 0);
		issueDate.set(Calendar.MILLISECOND, 0);
		returnDate.setTime(issueDate.getTime());
		returnDate.add(Calendar.DATE, days);
		BooksInUse booksInUse = new BooksInUse();
		Orders orders = ordersDAO.getById(orderId);
		Book book = orders.getBook();
		book.setAvailable(book.getAvailable() - 1);
		booksInUse.setBook(book);
		Person person = orders.getPerson();
		person.setConfirm(true);
		booksInUse.setPerson(person);
		booksInUse.setReturnDate(returnDate.getTime());
		booksInUse.setIssueDate(issueDate.getTime());
		booksInUse.setTerm(days);
		bookDAO.updateBook(book);
		booksInUseDAO.addBooksInUse(booksInUse);
		ordersDAO.deleteOrder(orderId);
		log.info("Issue book {}", orders);
		if (person.getSms()) {
			log.info("Sending sms");
			String[] send = sd.send_sms("+38" + person.getCellphone(), book.getTitle(), 0, "", "", 0, "JLibrary", "");
			log.info(send);
		}
	}
	
	@Override
	@Transactional
	public void returnBook(BooksInUse booksInUse) {
		Person person = booksInUse.getPerson();
		int timely = person.getTimelyReturns();
		int untimely = person.getUntimelyReturns();
		double rating;
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 23);
		today.set(Calendar.MINUTE, 59);
		
		Date returnDate = booksInUse.getReturnDate();
		if (returnDate.getTime() < today.getTime().getTime()) {
			untimely++;
		}
		else {
			timely++;
		}
		person.setTimelyReturns(timely);
		person.setUntimelyReturns(untimely);
		rating = CalculateRating.getRating(person.getFailedOrders(), person.getUntimelyReturns(), person.getTimelyReturns());
		person.setRating(rating);
		person.setMultibookAllowed((int) (IConstants.MAX_MULTIBOOK_ALLOWED * rating));
		personDao.update(person);
		booksInUseDAO.removeBooksInUse(booksInUse.getBuid());
	}

	@Override
	@Transactional
	public List<BooksInUse> getAllBooksInUse() {
		return booksInUseDAO.getAllBooksInUse();
	}

	@Override
	@Transactional
	public List<BooksInUse> getByPersonId(int personId) {
		return booksInUseDAO.getByPersonId(personId);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByBookId(int bookId) {
		return booksInUseDAO.getByBookId(bookId);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByIssueDate(Date issueDate) {
		return booksInUseDAO.getByIssueDate(issueDate);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByReturnDate(Date returnDate) {
		return booksInUseDAO.getByReturnDate(returnDate);
	}

	@Override
	@Transactional
	public List<BooksInUse> getInUse(boolean inUse) {
		return booksInUseDAO.getInUse(inUse);
	}

	@Override
	@Transactional
	public List<Book> getAllBooks() {
		return booksInUseDAO.getAllBooks();
	}
	
	@Override
	@Transactional
	public List<Book> getAllBooks(int currentPos, int pageSize, String sort) {
		if (currentPos > -1) {
			return booksInUseDAO.getAllBooks(currentPos, pageSize, sort);
		} else {
			return booksInUseDAO.getAllBooks();
		}
		
	}

	@Override
	@Transactional
	public List<Book> getReturnBooksToday() {
		List<Book> books = booksInUseDAO.getReturnBooksToday();
		return books;
	}

	@Override
	@Transactional
	public void removeBooksInUse(int id) {
		booksInUseDAO.removeBooksInUse(id);
	}

	@Override
	@Transactional
	public BooksInUse getById(int id) {
		return booksInUseDAO.getById(id);
	}

    @Override
    @Transactional
    public Date getMinByReturnDate(int bid) {
        return booksInUseDAO.getMinByReturnDate(bid); 
    }

    @Override
    @Transactional
    public boolean alreadyInUse(int bookId, int personId) {
        return booksInUseDAO.alreadyInUse(bookId, personId);
    }
    
    @Override
    @Transactional
    public long countBooksInUse() {
    	return booksInUseDAO.countBooksInUse();
    }
    
    @Override
    @Transactional
    public long countBooksInUseToday() {
    	return booksInUseDAO.countBooksInUseToday();
    }
    
    @Override
    @Transactional
    public List<Book> getReturnBooksToday(int currentPos, int pageSize,
    		String sort) {
    	List<Book> books = booksInUseDAO.getReturnBooksToday(currentPos, pageSize, sort);
    	return books;
    }

	@Override
	@Transactional
	public long getCountBooksByPerson(String name) {
		return booksInUseDAO.getCountBooksByPerson(name);
	}

	@Override
	@Transactional
	public long getCountReturnBooksBeetweenDates(Date dateFrom, Date dateTo, int BookId) {
		return booksInUseDAO.getCountReturnBooksBeetweenDates(dateFrom, dateTo, BookId);
	}

	@Override
	@Transactional
	public void updateBooksInUse(BooksInUse inUse) {
		booksInUseDAO.updateBooksInUse(inUse);
		
	}

}
