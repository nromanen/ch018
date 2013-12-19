package com.ch018.library.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.DAO.BookDAOImpl;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
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
	
	@RequestMapping(value = "/books")
	public ModelAndView booksList() {
		
		/*Book b = bookService.getBooksById(1);
		Genre g = genreService.getGenreById(2);
		
		b.setGenre(g);
		
		bookService.updateBook(b);
		*/
		return new ModelAndView("books", "books", bookService.getAllBooks());
	}
	
	@RequestMapping(value = "/addbook")
	public String showAddBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("genre", genreService.getAllGenres());
		return "book";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") Book book, BindingResult result) {
		int genreId = Integer.parseInt(book.getGenre().getName());
		Genre genre = genreService.getGenreById(genreId);
		bookService.addBook(book, genre);
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.GET)
	public String showEditBook(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("book", bookService.getBooksById(id));
		model.addAttribute("genre", genreService.getAllGenres());
		return "book";
		
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public String editBook(@ModelAttribute("book") Book book, BindingResult result) {
		int genreId = Integer.parseInt(book.getGenre().getName());
		Genre genre = genreService.getGenreById(genreId);
		bookService.updateBook(book, genre);
		return "redirect:/books";
	}
}
