package com.ch018.library.service;

import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;

public interface BooksInUseService {
	void addBooksInUse(int days, Orders orders);
	void updateBooksInUse(BooksInUse inUse);
	void returnBook(BooksInUse booksInUse);
	List<BooksInUse> getAllBooksInUse();
	List<BooksInUse> getByPersonId(int personId);
	List<BooksInUse> getByBookId(int bookId);
	List<BooksInUse> getByIssueDate(Date issueDate);
	List<BooksInUse> getByReturnDate(Date returnDate);
	List<BooksInUse> getInUse(boolean inUse);
	void removeBooksInUse(int id);
	BooksInUse getById(int id);
    Date getMinByReturnDate(int bid);
    boolean alreadyInUse(int bookId, int personId);
	List<Book> getAllBooks(int currentPos, int pageSize, String sort);
	long countBooksInUse();
	long countBooksInUseToday();
	List<Book> getReturnBooksToday(int currentPos, int pageSize, String sort);
    long getCountBooksByPerson(String name);
    long getCountReturnBooksBeetweenDates(Date dateFrom, Date dateTo, int BookId);

}
