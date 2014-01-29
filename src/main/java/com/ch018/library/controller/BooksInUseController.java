package com.ch018.library.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.PersonService;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@Controller
public class BooksInUseController {

	@Autowired
	private BooksInUseService booksInUseService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/bookusers/{query}/{id}", method = RequestMethod.GET)
	public String showBookInUse(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "query") String query, Model model) {
		if (query.equals("book")) {
			Book book = bookService.getBooksByIdWithUses(id);
			Set<BooksInUse> booksInUses = book.getBooksinuses();
			model.addAttribute("booksinuse", booksInUses);
			model.addAttribute("book", book);
			return "bookinuse";
		} else if (query.equals("user")) {
			Person person = personService.getByIdWithBooks(id);
			Set<BooksInUse> booksInUses = person.getBooksinuses();
			model.addAttribute("booksinuse", booksInUses);
			model.addAttribute("person", person);
			return "bookinuse";
		}
		
		return "404";
	}
	
	@RequestMapping(value = "/booksinuse/delete{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteInUse(@PathVariable int id) {
		booksInUseService.removeBooksInUse(id);
		return "booksinuse";
	}
	
	@RequestMapping(value = "/booksinuse/return{id}", method = RequestMethod.GET)
	@ResponseBody
	public String returnBook(@PathVariable int id) {
		BooksInUse booksInUse = booksInUseService.getById(id);
		Book book = booksInUse.getBook();
		book.setAvailable(book.getAvailable() + 1);
		bookService.updateBook(book);
		booksInUseService.returnBook(booksInUse);
		return "bookinuse";
	}
}
