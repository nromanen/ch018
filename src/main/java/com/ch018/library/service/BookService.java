package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.form.AdvancedSearch;

@Service
public interface BookService {
	void addBook(Book book);
	void updateBook(Book book);
	List<Book> getAllBooks();
	List<Book> getBooks(String search, AdvancedSearch advancedSearch, Integer genre, Integer position);
	Book getBooksById(int id);
	Book getBooksByIdWithUses(int id);
	Book getBooksByIdWithOrders(int id);
	int deleteBook(int id);
    List<Book> getBooksByRating();
    List<Book> getAllBooks(int currentPos, int pageSize, String sort);
	long countBooks();
	long countBooks(String search, AdvancedSearch advancedSearch, Integer genre);
	List<Book> simpleSearch(String parametr, int currentPos, int pageSize, String sort);
	List<Book> paramSearch(String searchField, String search, int currentPos, int pageSize, String sort);
	long simpleSearchCount(String search);
	long countBooksByGenre(String search, Integer id);
	List<Book> getBooksByGenre(String search, Integer id, int currentPos, int pageSize,	String field);
	List<Book> advancedSearch(AdvancedSearch search, int currentPos, int pageSize);
	long advancedSearchCount(AdvancedSearch search);
	long countBooksByGenreWithAdvSearch(AdvancedSearch advancedSearch, Integer id);
	List<Book> getBooksByGenreWithAdvSearch(AdvancedSearch advancedSearch, Integer id, int currentPos, int pageSize);
}
