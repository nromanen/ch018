package com.ch018.library.DAO;

import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.form.AdvancedSearch;

public interface BookDAO {
	void addBook(Book book);
	List<Book> getAllBooks(int currentPos, int pageSize, String sort, boolean isAsc);
	Book getBooksById(int id);
	Book getBooksByIdWithUses(int id);
	Book getBooksByIdWithOrders(int id);
	int deleteBook(int id);
	void updateBook(Book book);
	List<Book> simpleSearch(String parametr);
    List<Book> getBooksByRating();
    long countBooks();
	List<Book> simpleSearch(String parametr, int currentPos, int pageSize, String sort, boolean isAsc);
	long simpleSearchCount(String parametr);
	List<Book> advancedSearch(AdvancedSearch search, int currentPos, int pageSize);
	long advancedSearchCount(AdvancedSearch search);
	List<Book> getMostRatedByGenre(Genre genre);
}
