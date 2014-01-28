package com.ch018.library.DAO;

import java.util.List;

import com.ch018.library.entity.Book;

public interface BookDAO {
	void addBook(Book book);
	List<Book> getAllBooks();
	List<Book> getAllBooks(int currentPos, int pageSize, String sort);
	Book getBooksById(int id);
	Book getBooksByIdWithUses(int id);
	Book getBooksByIdWithOrders(int id);
	int deleteBook(int id);
	List<Book> getBooksByTitle(String title);
	List<Book> getBooksByAuthors(String authors);
	List<Book> getBooksByYear(int year);
	void updateBook(Book book);
	List<Book> simpleSearch(String parametr);
	List<Book> paramSearch(String field, String parametr);
    long countBooks();
	List<Book> simpleSearch(String parametr, int currentPos, int pageSize,
			String sort);
	List<Book> paramSearch(String searchField, String search, int currentPos,
			int pageSize, String sort);
	long simpleSearchCount(String parametr);
	long paramSearchCount(String field, String parametr);
	long countBooksByGenre(String search, Integer id);
	List<Book> getBooksByGenre(String search, Integer id, int currentPos, int pageSize,
			String field);
}
