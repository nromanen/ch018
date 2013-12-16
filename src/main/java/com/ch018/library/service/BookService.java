package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;

@Service
public interface BookService {
	void addBook(Book book);
	void updateBook(int id, Book book);
	List<Book> getAllBooks();
	Book getBooksById(int id);
	void deleteBook(Book book);
	List<Book> getBooksByTitle(String title);
	List<Book> getBooksByAuthors(String authors);
	List<Book> getBooksByYear(int year);
	List<Book> getBooksByPerson(Person person);
	int getCount(Book book);
	int getCurrentCount(Book book);
}
