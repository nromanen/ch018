package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.form.AdvancedSearch;

@Service
public interface BookService {
	void addBook(Book book);
	void updateBook(Book book);
	Book getBooksById(int id);
	Book getBooksByIdWithUses(int id);
	Book getBooksByIdWithOrders(int id);
	int deleteBook(int id);
    List<Book> getBooksByRating();
    List<Book> getAllBooks(int currentPos, int pageSize, String sort, boolean isAsc);
	long countBooks();
	List<Book> simpleSearch(String parametr, int currentPos, int pageSize, String sort, boolean isAsc);	
	long simpleSearchCount(String search);	
	List<Book> advancedSearch(AdvancedSearch search, int currentPos, int pageSize);
	long advancedSearchCount(AdvancedSearch search);
}
