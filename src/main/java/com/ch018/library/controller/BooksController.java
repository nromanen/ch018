package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

@Secured("ROLE_LIBRARIAN")
@Controller
public class BooksController {

	@Autowired
	private BookService bookService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private PersonService personService;

	@Autowired
	private BooksInUseService booksInUseService;

	@Autowired
	private OrdersService ordersService;

	/**
	 * Add new book
	 * 
	 * @param book
	 * @param result
	 * @return
	 */

	@RequestMapping(value = "/book/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Book newOrUpdateBook(@RequestBody @Valid Book book,
			BindingResult result) {
		if (result.hasErrors()) {
			return book;
		}
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
				break;
			default:
				books.addAll(bookService.getAllBooks());
				break;
			}
		}
		model.addAttribute("books", books);
		return "books";
	}

	@RequestMapping(value = "/book/delete{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteBook(@PathVariable Integer id) {
		return bookService.deleteBook(id);
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String search(@RequestParam String search,
			@RequestParam String field, Model model) {
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		model.addAttribute("book", book);
		if (field.equals("all")) {
			books.addAll(bookService.simpleSearch(search));
		} else {
			books.addAll(bookService.paramSearch(field, search));
		}
		model.addAttribute("books", books);
		return "books";
	}

}
