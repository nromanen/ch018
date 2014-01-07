package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Book;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;

/**
 * 
 * @author Yurik Mikhaletskiy
 * 
 */

@Controller
public class BooksController {

	static Logger log = LogManager.getLogger(BooksController.class);

	@Autowired
	BookService bookService;

	@Autowired
	GenreService genreService;

	@Autowired
	PersonService personService;

	@Autowired
	BooksInUseService booksInUseService;

	@Autowired
	OrdersService ordersService;

	/**
	 * Add new book
	 * 
	 * @param book
	 * @param result
	 * @return
	 */
	
	@RequestMapping(value="/book/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Book newBook(@RequestBody Book book) {
		log.info("New book");
		if (book.getId() == 0) {
			bookService.addBook(book);
		} else {
			bookService.updateBook(book);
		}
		return book;
	}

	/**
	 * Show table edit book
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String showBooks(
			@RequestParam(value = "show", required = false) String show,
			Model model) {
		log.info("info book");
		log.debug("debug book");
		log.error("error book");
		log.trace("trace book");
		ordersService.toIssueToday();
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("genre", genreService.getAllGenres());
		List<Book> books = new ArrayList<>();
		if (show == null) {
			books.addAll(bookService.getAllBooks());
		} else {
			switch (show) {
			case "return":
				books.addAll(booksInUseService.getAllBooks());
				break;
			case "returntd":
				books.addAll(booksInUseService.getReturnBooksToday());
				break;
			case "issuetd":
				books.addAll(ordersService.toIssueToday());
				break;
			case "issueph":
				books.addAll(ordersService.toIssuePerHour());
				//books.addAll(bookService.simpleSearch("Programming"));
				break;

			default:
				books.addAll(bookService.getAllBooks());
				break;
			}
		}
		model.addAttribute("books", books);
		return "librarian/books";
	}
	
	@RequestMapping(value = "/book/delete{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteBook(@PathVariable Integer id) {
		return bookService.deleteBook(id);
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String search(@RequestParam String search, 
			@RequestParam String field, 
			Model model)
	{
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		model.addAttribute("book", book);
		if (field.equals("all")) {
			books.addAll(bookService.simpleSearch(search));
		}
		else {
			books.addAll(bookService.paramSearch(field, search));
		}
		model.addAttribute("books", books);
		return "librarian/books";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "tilestest/home";
	}
	
}
