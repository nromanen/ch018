package com.ch018.library.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.util.CalculateRating;
import com.ch018.library.util.IConstants;

@Service
public class BooksInUseServiceImpl implements BooksInUseService {

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
		
	}
	
	@Override
	@Transactional
	public void returnBook(BooksInUse booksInUse) {
		Person person = booksInUse.getPerson();
		int timely = person.getTimelyReturns();
		int untimely = person.getUntimelyReturns();
		double rating;
		Date now = new Date();
		Date returnDate = booksInUse.getReturnDate();
		if (returnDate.getTime() < now.getTime()) {
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
		// TODO Auto-generated method stub
		return booksInUseDAO.getAllBooksInUse();
	}

	@Override
	@Transactional
	public List<BooksInUse> getByPersonId(int personId) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByPersonId(personId);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByBookId(int bookId) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByBookId(bookId);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByIssueDate(Date issueDate) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByIssueDate(issueDate);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByReturnDate(Date returnDate) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByReturnDate(returnDate);
	}

	@Override
	@Transactional
	public List<BooksInUse> getInUse(boolean inUse) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getInUse(inUse);
	}

	@Override
	@Transactional
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return booksInUseDAO.getAllBooks();
	}
	
	@Override
	@Transactional
	public List<Book> getAllBooks(int currentPos, int pageSize, String sort) {
		if (currentPos > -1) {
			List<Book> books = booksInUseDAO.getAllBooks(currentPos, pageSize, sort);
			for (Book book : books) {
				Genre genre = book.getGenre();
				genre.setName(localizationService.getName(genre.getId(), LocaleContextHolder.getLocale().getLanguage()));
				book.setGenre(genre);
			}
			return books;
		} else {
			return booksInUseDAO.getAllBooks();
		}
		
	}

	@Override
	@Transactional
	public List<Book> getReturnBooksToday() {
		// TODO Auto-generated method stub
		List<Book> books = booksInUseDAO.getReturnBooksToday();
		for (Book book : books) {
			Genre genre = book.getGenre();
			genre.setName(localizationService.getName(genre.getId(), LocaleContextHolder.getLocale().getLanguage()));
			book.setGenre(genre);
		}
		return books;
	}

	@Override
	@Transactional
	public void removeBooksInUse(int id) {
		// TODO Auto-generated method stub
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
    	for (Book book : books) {
			Genre genre = book.getGenre();
			genre.setName(localizationService.getName(genre.getId(), LocaleContextHolder.getLocale().getLanguage()));
			book.setGenre(genre);
		}
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
