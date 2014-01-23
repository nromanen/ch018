package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ch018.library.domain.JsonResponse;
import com.ch018.library.entity.Book;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.IConstants;

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
	
	@Autowired 
	private MessageSource messageSource;

	/**
	 * Add new book
	 * 
	 * @param book
	 * @param result
	 * @return
	 */

	@RequestMapping(value = "/book/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse newOrUpdateBook(@RequestBody @Valid Book book,
			BindingResult result) {
		JsonResponse resp = new JsonResponse();
		if (!result.hasErrors()) {
			resp.setStatus("SUCCESS");
			if (book.getId() == 0) {
				bookService.addBook(book);
			} else {
				book.setImage(bookService.getBooksById(book.getId()).getImage());
				bookService.updateBook(book);
			}
			resp.setResult(book);
		} else {
			resp.setStatus("FAIL");
			resp.setResult(result.getAllErrors());
		}
		return resp;
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
			//@RequestParam(value = "show", required = false) String show,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			Model model, HttpSession session) {
		String sess =(String) session.getAttribute("sort");
		if (sess == null) {
			session.setAttribute("sort", sort);
			sess =(String) session.getAttribute("sort");
		}
		if (!sort.equals("id")) {
			session.setAttribute("sort", sort);
			sess =(String) session.getAttribute("sort");
		}	
		/*if (show != null) {
			session.setAttribute("SHOW", show);
		} else {
			show = (String) session.getAttribute("SHOW");
		}*/
		Book book = new Book();
		long count;
		long pages = 1;
		int currentPos;
		model.addAttribute("book", book);
		model.addAttribute("genre", genreService.getAllGenres());
		List<Book> books = new ArrayList<>();

		// if (show == null) {
		count = bookService.countBooks();
		pages = (int) Math.ceil(count / (float) IConstants.PAGE_SIZE);
		currentPos = (page - 1) * IConstants.PAGE_SIZE;
		books.addAll(bookService.getAllBooks(currentPos, IConstants.PAGE_SIZE, sess));
		/*} else {
			switch (show) {
			case "return":
				count = booksInUseService.countBooksInUse();
				pages = (int) Math.ceil(count / (float)IConstants.PAGE_SIZE);
				currentPos = (page - 1) * IConstants.PAGE_SIZE;
				books.addAll(booksInUseService.getAllBooks(currentPos,IConstants.PAGE_SIZE, sess));
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
				session.setAttribute("SHOW", null);
				count = bookService.countBooks();
				pages = (int) Math.ceil(count / (float)IConstants.PAGE_SIZE);
				currentPos = (page - 1) * IConstants.PAGE_SIZE;
				books.addAll(bookService.getAllBooks(currentPos,IConstants.PAGE_SIZE, sess));
				break;
			}
		}*/
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		model.addAttribute("books", books);
		return "books";
	}
	
	@RequestMapping(value = "/books/{show}", method = RequestMethod.GET)
	public String showBooksFilters(
			@PathVariable(value = "show") String show,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			Model model, HttpSession session) {
		String sess = (String) session.getAttribute("sort");
		if (sess == null) {
			session.setAttribute("sort", sort);
			sess = (String) session.getAttribute("sort");
		}
		if (!sort.equals("id")) {
			session.setAttribute("sort", sort);
			sess = (String) session.getAttribute("sort");
		}	
		Book book = new Book();
		long count;
		long pages = 1;
		int currentPos;
		model.addAttribute("book", book);
		model.addAttribute("genre", genreService.getAllGenres());
		List<Book> books = new ArrayList<>();
		
		switch (show) {
			case "return":
				count = booksInUseService.countBooksInUse();
				pages = (int) Math.ceil(count / (float)IConstants.PAGE_SIZE);
				currentPos = (page - 1) * IConstants.PAGE_SIZE;
				books.addAll(booksInUseService.getAllBooks(currentPos,IConstants.PAGE_SIZE, "id"));
				break;
			case "returntd":
				count = booksInUseService.countBooksInUseToday();
				pages = (int) Math.ceil(count / (float)IConstants.PAGE_SIZE);
				currentPos = (page - 1) * IConstants.PAGE_SIZE;
				books.addAll(booksInUseService.getReturnBooksToday(currentPos,IConstants.PAGE_SIZE, "id"));
				break;
			case "issuetd":
				count = ordersService.countOrdersToday();
				pages = (int) Math.ceil(count / (float)IConstants.PAGE_SIZE);
				currentPos = (page - 1) * IConstants.PAGE_SIZE;
				books.addAll(ordersService.toIssueToday(currentPos,IConstants.PAGE_SIZE, "id"));
				break;
			case "issueph":
				count = ordersService.countOrdersPerHour();
				pages = (int) Math.ceil(count / (float)IConstants.PAGE_SIZE);
				currentPos = (page - 1) * IConstants.PAGE_SIZE;
				books.addAll(ordersService.toIssuePerHour(currentPos,IConstants.PAGE_SIZE, "id"));
				break;
			default:
				session.setAttribute("SHOW", null);
				count = bookService.countBooks();
				pages = (int) Math.ceil(count / (float)IConstants.PAGE_SIZE);
				currentPos = (page - 1) * IConstants.PAGE_SIZE;
				books.addAll(bookService.getAllBooks(currentPos,IConstants.PAGE_SIZE, sess));
				break;
		}
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
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
			@RequestParam String field, Model model, HttpSession session) {
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		session.setAttribute("search", search);
		model.addAttribute("book", book);
		model.addAttribute("genre", genreService.getAllGenres());
		if (field.equals("all")) {
			books.addAll(bookService.simpleSearch(search));
		} else {
			books.addAll(bookService.paramSearch(field, search));
		}
		model.addAttribute("books", books);
		return "books";
	}

}
