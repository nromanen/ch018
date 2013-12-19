package com.ch018.library.DAO;

import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;

public interface BookDAO {
	void addBook(Book book);

	List<Book> getAllBooks();

	Book getBooksById(int id);

	void deleteBook(Book book);

	List<Book> getBooksByTitle(String title);

	List<Book> getBooksByAuthors(String authors);

	List<Book> getBooksByYear(int year);

	List<Book> getBooksByPerson(Person person);

	void updateBook(Book book);
}
