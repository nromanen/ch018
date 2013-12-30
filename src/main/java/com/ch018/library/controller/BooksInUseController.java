package com.ch018.library.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.PersonService;

@Controller
public class BooksInUseController {

	@Autowired
	BooksInUseService booksInUseService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "/bookusers", method = RequestMethod.GET)
	public String showBookInUse(@RequestParam("id") Integer id, Model model) {
		List<BooksInUse> booksInUses = booksInUseService.getByBookId(id);
		model.addAttribute("booksinuse", booksInUses);
		model.addAttribute("book", bookService.getBooksById(id));
		return "librarian/bookinuse";
	}
	
	@RequestMapping(value = "/booksinuse/delete{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteInUse(@PathVariable int id) {
		booksInUseService.removeBooksInUse(id);
		return "booksinuse";
	}
	
	@RequestMapping(value = "/booksinuse/return{id}", method = RequestMethod.GET)
	@ResponseBody
	public String returnBook(@PathVariable int id) {
		BooksInUse booksInUse = booksInUseService.getById(id);
		Person person = booksInUse.getPerson();
		int timely = person.getTimelyReturns();
		int untimely = person.getUntimelyReturns();
		
		Date now = new Date();
		Date returnDate = booksInUse.getReturnDate();
		
		if (returnDate.getTime() < now.getTime()) {
			untimely++;
		}
		else {
			timely++;
		}
		
		person.setTimelyReturns(timely);
		person.setUntimelyReturns(untimely);
				
		personService.update(person);
		
		booksInUseService.removeBooksInUse(id);
		return "bookinuse";
	}
}