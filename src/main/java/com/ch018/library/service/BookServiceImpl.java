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
	
	@Transactional
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		bookDAO.addBook(book);
	}

	@Transactional
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		bookDAO.updateBook(book);
	}


	@Transactional
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookDAO.getAllBooks();
	}


	@Transactional
	public Book getBooksById(int id) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksById(id);
	}


	@Transactional
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		bookDAO.deleteBook(book);
	}


	@Transactional
	public List<Book> getBooksByTitle(String title) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksByTitle(title);
	}


	@Transactional
	public List<Book> getBooksByAuthors(String authors) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksByAuthors(authors);
	}


	@Transactional
	public List<Book> getBooksByYear(int year) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksByYear(year);
	}


	@Transactional
	public List<Book> getBooksByPerson(Person person) {
		// TODO Auto-generated method stub
		return bookDAO.getBooksByPerson(person);
	}

}
