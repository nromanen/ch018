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
	private BookDAO bookDAO;
	
	@Transactional
	public void addBook(Book book) {
		bookDAO.addBook(book);
	}

	@Transactional
	public void updateBook(Book book) {
		bookDAO.updateBook(book);
	}


	@Transactional
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();
	}


	@Transactional
	public Book getBooksById(int id) {
		return bookDAO.getBooksById(id);
	}


	@Transactional
	public List<Book> getBooksByTitle(String title) {
		return bookDAO.getBooksByTitle(title);
	}


	@Transactional
	public List<Book> getBooksByAuthors(String authors) {
		return bookDAO.getBooksByAuthors(authors);
	}


	@Transactional
	public List<Book> getBooksByYear(int year) {
		return bookDAO.getBooksByYear(year);
	}

	@Transactional
	public int deleteBook(int id) {
		return bookDAO.deleteBook(id);
	}

	@Transactional
	public List<Book> simpleSearch(String parametr) {
		return bookDAO.simpleSearch(parametr);
	}

	@Transactional
	public List<Book> paramSearch(String field, String parametr) {
		return bookDAO.paramSearch(field, parametr);
	}

         @Transactional
         public List<Book> latestArrivals() {
               return bookDAO.latestArrivals();
         }

}
