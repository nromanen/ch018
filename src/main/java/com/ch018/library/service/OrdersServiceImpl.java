package com.ch018.library.service;

import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author okryvortc
 */

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private OrdersDAO ordDAO;
    
    @Autowired
    private LocalizationService localizationService;
    
    @Autowired
    private BooksInUseService booksInUseService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private WishListService wishListService;
    
    @Transactional
    public void addOrder(Orders ord) {
       ordDAO.addOrder(ord); 
    }

    @Transactional
    public void deleteOrder(Orders ord) {
        ordDAO.deleteOrder(ord);
    }

    @Transactional
    public Collection getOrdersByBooksId(int id) {
        return ordDAO.getOrdersByBooksId(id);
    }

    @Transactional
    public Collection getOrdersByPersonId(int id) {
        return ordDAO.getOrdersByPersonId(id);
    }

    @Transactional
    public Collection getAllOrders() {
        return ordDAO.getAllOrders();
    }

	@Transactional
	public List<Book> getAllBooks() {
		return ordDAO.getAllBooks();
	}

	@Transactional
	public List<Book> toIssueToday() {
		return ordDAO.toIssueToday();
	}
	
	@Transactional
	public List<Orders> failedOrders() {
		return ordDAO.failedOrders();
	}

	@Transactional
	public List<Book> toIssuePerHour() {
		return ordDAO.toIssuePerHour();
	}

	@Transactional
	public Orders getById(int id) {
		return ordDAO.getById(id);
	}

	@Transactional
	public Orders deleteOrder(int id) {
		return ordDAO.deleteOrder(id);
		
	}

    @Override
    @Transactional
    public boolean orderExist(int personId, int bookId) {
        return ordDAO.orderExist(personId, bookId);
    }

 
    @Override
    @Transactional
    public Date minOrderDateOf(int bookId) {
        return ordDAO.minOrderDateOf(bookId);
    }

	@Override
	@Transactional
	public void updateOrder(Orders ord) {
		   ordDAO.updateOrder(ord);
		
	}

	@Override
	@Transactional
	public long getCountOrdersByPerson(String name) {
		return ordDAO.getCountOrdersByPerson(name);
	}
    
    @Override
    @Transactional
    public long countOrdersToday() {
    	return ordDAO.countOrdersToday();
    }
    
    @Override
    @Transactional
    public List<Book> toIssueToday(int currentPos, int pageSize, String sort) {
    	List<Book> books = ordDAO.toIssueToday(currentPos, pageSize, sort);
    	return books;
    }
    
    @Override
    @Transactional
    public long countOrdersPerHour() {
    	return ordDAO.countOrdersPerHour();
    }
    
    @Override
    @Transactional
    public List<Book> toIssuePerHour(int currentPos, int pageSize, String string) {
    	List<Book> books = ordDAO.toIssuePerHour(currentPos, pageSize, string);
    	return books;
    }

	@Override
	@Transactional
	public long getCountOrdersBookBeetweenDates(Date dateFrom, Date dateTo,
			int BookId) {
		return ordDAO.getCountOrdersBookBeetweenDates(dateFrom, dateTo, BookId);
	}

	@Override
	@Transactional
	public int updateissueDate(int id, String issueDate) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	Date newIssueDate = format.parse(issueDate);
       	Orders updateOrder = ordDAO.getById(id);
       	int available = updateOrder.getBook().getAvailable();
       	long  a = booksInUseService.getCountReturnBooksBeetweenDates(updateOrder.getIssueDate(), 
       			                                                    newIssueDate, 
       			                                                    updateOrder.getBook().getId());
       	long b = ordDAO.getCountOrdersBookBeetweenDates(updateOrder.getIssueDate(), 
       			                                                    newIssueDate, 
       			                                                    updateOrder.getBook().getId()) - 1;
       	int expectedAvailable = (int) ((int) a - b);
       	expectedAvailable = expectedAvailable + available;
        if (expectedAvailable > 1 ) {
        	updateOrder.setIssueDate(newIssueDate);
            ordDAO.updateOrder(updateOrder);
            return 1;
        } else {
                return 0;
        }
	}

	@Override
	@Transactional
	public int prepareOrder(int bookID, Person pers) {
		if (booksInUseService.alreadyInUse(bookID, pers.getId())) {
            return 0;
        }
        int personId = pers.getId();
        int  uses = ordDAO.getOrdersByPersonId(personId).size();
        uses += booksInUseService.getByPersonId(personId).size();
        int j = pers.getMultibookAllowed();
        if (j == uses) {
            return 2;
        } 
        if (ordDAO.orderExist(personId, bookID)) {
              return 3;
          }
        return 1;
	}

	@Override
	@Transactional
	public String createOrder(int bookId, int personId, Orders newOrder) {
        Calendar calendar = Calendar.getInstance();
        newOrder.setBook(bookService.getBooksById(newOrder.getBook().getId()));
        newOrder.setPerson(personService.getById(newOrder.getPerson().getId()));
        newOrder.setDate(calendar.getTime());
        ordDAO.addOrder(newOrder);
        if (wishListService.bookExistInWishList(newOrder.getBook().getId(), newOrder.getPerson().getId())) {
                    int id = wishListService.getWishWithoutId(bookId, personId).getId();
					wishListService.deleteWishById(id);
				}
        return "redirect:/userOrder"; 
	
    
    } 
	
}
