package com.ch018.library.service;

import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;

public interface BooksInUseService {
	void addBooksInUse(BooksInUse booksInUse);
	void removeBooksInUse(BooksInUse booksInUse);
	//void updateBooksInUse(int id, Book book);
	List<BooksInUse> getAllBooksInUse();
	List<BooksInUse> getByPersonId(int personId);
	List<BooksInUse> getByBookId(int bookId);
	List<BooksInUse> getByIssueDate(Date issueDate);
	List<BooksInUse> getByReturnDate(Date returnDate);
	List<BooksInUse> getInUse(boolean inUse);
	List<Book> getAllBooks();
}
