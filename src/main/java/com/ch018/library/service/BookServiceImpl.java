package com.ch018.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.util.IConstants;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private LocalizationService localizationService;
	
	@Transactional
	public void addBook(Book book) {
		// TODO: isn't this string default for image url string?
		if (book.getImage() == null)
			book.setImage("http://placehold.it/120x150");
		bookDAO.addBook(book);
	}

	@Transactional
	public void updateBook(Book book) {
		if (book.getImage() == null)
			book.setImage("http://placehold.it/120x150");
		bookDAO.updateBook(book);
	}


	@Transactional
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();
	}
	
	@Override
	@Transactional
	public List<Book> getAllBooks(int currentPos, int pageSize, String sort) {
		if (currentPos > -1) {
			List<Book> books = bookDAO.getAllBooks(currentPos, pageSize, sort);
			//List<Genre> genres = genreService.getAllGenres("en");
			for (Book book : books) {
				Genre genre = book.getGenre();
				genre.setName(localizationService.getName(genre.getId(), LocaleContextHolder.getLocale().getLanguage()));
				book.setGenre(genre);
			//	genre.setName(localizationService.getName(genre.getId(), language));
			}
			return books;
		} else {
			return bookDAO.getAllBooks();
		}
		
	}
	
	@Override
	@Transactional
	public long countBooks() {
		return bookDAO.countBooks();
	}


	@Transactional
	public Book getBooksById(int id) {
		return bookDAO.getBooksById(id);
	}
	
	@Transactional
	public Book getBooksByIdWithUses(int id) {
		return bookDAO.getBooksByIdWithUses(id);
	}
	
	@Transactional
	public Book getBooksByIdWithOrders(int id) {
		return bookDAO.getBooksByIdWithOrders(id);
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
	public List<Book> simpleSearch(String parametr, int currentPos, int pageSize, String sort) {
		List<Book> books = bookDAO.simpleSearch(parametr, currentPos, pageSize, sort);
		for (Book book : books) {
			Genre genre = book.getGenre();
			genre.setName(localizationService.getName(genre.getId(), LocaleContextHolder.getLocale().getLanguage()));
			book.setGenre(genre);
		}
		return books;
	}
	
	@Transactional
	public List<Book> paramSearch(String searchField, String search,
			int currentPos, int pageSize, String sort) {
		List<Book> books = bookDAO.paramSearch(searchField, search, currentPos, pageSize, sort);
		for (Book book : books) {
			Genre genre = book.getGenre();
			genre.setName(localizationService.getName(genre.getId(), LocaleContextHolder.getLocale().getLanguage()));
			book.setGenre(genre);
		}
		return books;
	}

	@Transactional
	public List<Book> paramSearch(String field, String parametr) {
		return bookDAO.paramSearch(field, parametr);
	}

    
    @Override
    @Transactional
    public long simpleSearchCount(String search) {
    	return bookDAO.simpleSearchCount(search);
    }

}
