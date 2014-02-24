package com.ch018.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.DAO.BooksInUseDAO;
import com.ch018.library.DAO.HistoryDAO;
import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.DAO.WishListDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.History;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.WishList;
import com.ch018.library.form.AdvancedSearch;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private WishListDAO wishListDAO;
	
	@Autowired
	private BooksInUseDAO booksInUseDAO;
	
	@Autowired
	private HistoryDAO historyDAO;
	
	@Override
	@Transactional
	public void addBook(Book book) {
		bookDAO.addBook(book);
	}

	@Override
	@Transactional
	public void updateBook(Book book) {
		bookDAO.updateBook(book);
	}	
	
	/**
	 * Get all books
	 * 
	 * @param currentPos - current pos for pagination
	 * @param pageSize - page size for pagination (if 0 then no pagination)
	 * @param sort - field to sort by
	 */
	@Override
	@Transactional
	public List<Book> getAllBooks(int currentPos, int pageSize, String sort, boolean isAsc) {
		List<Book> books = bookDAO.getAllBooks(currentPos, pageSize, sort, isAsc);
		return books;
	}
	
	@Override
	@Transactional
	public long countBooks() {
		return bookDAO.countBooks();
	}

	@Override
	@Transactional
	public Book getBooksById(int id) {
		return bookDAO.getBooksById(id);
	}
	
	@Override
	@Transactional
	public Book getBooksByIdWithUses(int id) {
		return bookDAO.getBooksByIdWithUses(id);
	}
	
	@Override
	@Transactional
	public Book getBooksByIdWithOrders(int id) {
		return bookDAO.getBooksByIdWithOrders(id);
	}

	@Override
	@Transactional
	public int deleteBook(int id) {
		return bookDAO.deleteBook(id);
	}
	
	@Override
	@Transactional
	public int deleteBookAll(Integer id) {
		Book book = bookDAO.getBooksByIdAll(id);
		List<WishList> wishList = new ArrayList<>(book.getWishList());
		for (WishList wish : wishList) {
			wishListDAO.deleteWish(wish);
		}
		List<Orders> orders = new ArrayList<>(book.getOrders());
		for (Orders order : orders) {
			ordersDAO.deleteOrder(order);
		}
		List<BooksInUse> booksInUses = new ArrayList<>(book.getBooksinuses());
		for (BooksInUse booksInUse : booksInUses) {
			booksInUseDAO.removeBooksInUse(booksInUse.getBuid());
		}
		List<History> histories = new ArrayList<>(book.getHistories());
		for (History history : histories) {
			historyDAO.removeHistory(history);
		}
		book.setBooksinuses(null);
		book.setOrders(null);
		book.setWishList(null);
		book.setHistories(null);
		return bookDAO.deleteBook(id);
	}
	
	@Override
	@Transactional
	public List<Book> simpleSearch(String parametr, int currentPos, int pageSize, String sort, boolean isAsc) {
		List<Book> books = bookDAO.simpleSearch(parametr, currentPos, pageSize, sort, isAsc);
		return books;
	}

    @Override
    @Transactional
    public long simpleSearchCount(String search) {
    	return bookDAO.simpleSearchCount(search);
    }
    
    @Override
    @Transactional
    public List<Book> advancedSearch(AdvancedSearch search, int currentPos,	int pageSize) {
    	List<Book> books = bookDAO.advancedSearch(search, currentPos, pageSize);
		return books;
    }
    
    @Override
    @Transactional
    public long advancedSearchCount(AdvancedSearch search) {
    	return bookDAO.advancedSearchCount(search);
    }

	@Override
	@Transactional
	public List<Book> getBooksByRating() {
		return bookDAO.getBooksByRating();
	}
	
	@Override
	@Transactional
	public List<Book> getMostRatedByGenre(Genre genre) {
		return bookDAO.getMostRatedByGenre(genre);
	}

}
