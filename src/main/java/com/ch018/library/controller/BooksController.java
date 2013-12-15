package com.ch018.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.service.BookService;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */

@Controller
public class BooksController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/books")
	public ModelAndView booksList() {
		return new ModelAndView("books", "books", bookService.getAllBooks());
	}
}
