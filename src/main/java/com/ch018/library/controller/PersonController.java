package com.ch018.library.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;

@Controller
public class PersonController {
	
	static Logger log = LogManager.getLogger(BooksController.class);
	
	@Autowired
	BookService bookService;
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	PersonService personService;
	 
	@Autowired
	BooksInUseService booksInUseService;

	
	@RequestMapping(value = "/users")
	public String showAddBook(Model model) {
		Person person = new Person();
		model.addAttribute("persons", personService.getAll());
		model.addAttribute("person", person);
		return "users";
	}

}
