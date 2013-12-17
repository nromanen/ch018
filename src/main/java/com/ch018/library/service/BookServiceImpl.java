package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDAO;
	

	public void addBook(Book book) {
		// TODO Auto-generated method stub
		bookDAO.addBook(book);
	}


	public void updateBook(int id, Book book) {
		// TODO Auto-generated method stub
		bookDAO.updateBook(id, book);
	}


	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookDAO.getAllBooks();
	}


	public Book getBooksById(int id) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksById(id);
	}


	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		bookDAO.deleteBook(book);
	}


	public List<Book> getBooksByTitle(String title) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksByTitle(title);
	}


	public List<Book> getBooksByAuthors(String authors) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksByAuthors(authors);
	}


	public List<Book> getBooksByYear(int year) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksByYear(year);
	}


	public List<Book> getBooksByPerson(Person person) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksByPerson(person);
	}

}
