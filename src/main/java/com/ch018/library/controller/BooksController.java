package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.DAO.BookDAOImpl;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
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
	 * Show table with books
	 * 
	 * @param model
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/books") public String showBooks(Model model) {
	 * Book book = new Book(); model.addAttribute("books",
	 * bookService.getAllBooks()); model.addAttribute("book", book);
	 * model.addAttribute("genre", genreService.getAllGenres()); return "books";
	 * }
	 */

	/**
	 * Add new book
	 * 
	 * @param book
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") Book book,
			BindingResult result) {
		int genreId = book.getGenre().getId();
		Genre genre = genreService.getGenreById(genreId);
		book.setGenre(genre);
		bookService.addBook(book);
		return "books";
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
			case "issuetd":
				books.addAll(ordersService.getAllBooks());
				break;

			default:
				books.addAll(bookService.getAllBooks());
				break;
			}
		}
		model.addAttribute("books", books);
		return "books";
	}

	/**
	 * Show table edit book
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
	public String showEditBook(@PathVariable Integer id, Model model) {
		model.addAttribute("book", bookService.getBooksById(id));
		model.addAttribute("genre", genreService.getAllGenres());
		return "books";
	}

	/**
	 * Edited book to DB
	 * 
	 * @param book
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/books/edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String editBook(@PathVariable int id, @RequestBody Book book) {
		book.setId(id);
		int genreId = book.getGenre().getId();
		Genre genre = genreService.getGenreById(genreId);
		book.setGenre(genre);
		bookService.updateBook(book);
		return "redirect:/books";
	}

	/**
	 * Show Book in users table
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bookusers", method = RequestMethod.GET)
	public String showBookInUse(@RequestParam("id") Integer id, Model model) {
		List<BooksInUse> booksInUses = booksInUseService.getByBookId(id);
		model.addAttribute("booksinuse", booksInUses);

		return "bookinuse";
	}
}
