package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.ch018.library.entity.Orders;
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
		if (book.getId() == 0) {
			bookService.addBook(book);
		} else {
			bookService.updateBook(book);
		}

		return "redirect:/books";
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
				break;

			default:
				books.addAll(bookService.getAllBooks());
				break;
			}
		}
		model.addAttribute("books", books);
		return "books";
	}



	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String showOrders(@RequestParam("id") Integer id, Model model) {
		List<Orders> orders = (List<Orders>) ordersService
				.getOrdersByBooksId(id);
		model.addAttribute("orders", orders);
		model.addAttribute("book", bookService.getBooksById(id));

		return "orders";
	}

	@RequestMapping(value = "/orders/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteOrder(@PathVariable int id) {
		ordersService.deleteOrder(id);
		return "orders";
	}

	@RequestMapping(value = "/orders/issue/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String issueOrder(@PathVariable int id) {
		BooksInUse booksInUse = new BooksInUse();
		Orders order = ordersService.getById(id);
		booksInUse.setBook(order.getBook());
		booksInUse.setPerson(order.getPerson());
		booksInUse.setReturnDate(new Date());
		booksInUse.setIssueDate(new Date());
		booksInUse.setTerm(14);
		booksInUseService.addBooksInUse(booksInUse);
		ordersService.deleteOrder(id);
		return "orders";
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
		model.addAttribute("book", bookService.getBooksById(id));
		return "bookinuse";
	}
	
	@RequestMapping(value = "/booksinuse/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteInUse(@PathVariable int id) {
		booksInUseService.removeBooksInUse(id);
		return "bookinuse";
	}
	
	@RequestMapping(value = "/booksinuse/return/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String returnBook(@PathVariable int id) {
		booksInUseService.removeBooksInUse(id);
		return "bookinuse";
	}
}
