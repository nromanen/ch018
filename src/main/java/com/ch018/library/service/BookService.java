package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;

@Service
public interface BookService {
	void addBook(Book book);
	void updateBook(Book book);
	List<Book> getAllBooks();
	Book getBooksById(int id);
	Book getBooksByIdWithUses(int id);
	Book getBooksByIdWithOrders(int id);
	int deleteBook(int id);
	List<Book> getBooksByTitle(String title);
	List<Book> getBooksByAuthors(String authors);
	List<Book> getBooksByYear(int year);
	List<Book> simpleSearch(String parametr);
	List<Book> paramSearch(String field, String parametr);
        List<Book> latestArrivals();
}
