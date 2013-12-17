package com.ch018.library.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */

@Controller
public class BooksController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	GenreService genreService;
	
	@RequestMapping(value = "/books")
	public ModelAndView booksList() {
		/*
		Book b1 = new Book();
		b1.setAuthors("0000");
		b1.setBookcase(1);
		b1.setDescription("asas");
		b1.setPages(5);
		b1.setPublication("asda");
		b1.setShelf(1);
		b1.setTerm(14);
		b1.setTitle("asssss");
		b1.setYear(1999);
		Book b2 = new Book();
		b2.setAuthors("0000");
		b2.setBookcase(1);
		b2.setDescription("asdfghas");
		b2.setPages(5);
		b2.setPublication("asdgda");
		b2.setShelf(1);
		b2.setTerm(14);
		b2.setTitle("asssfgfss");
		b2.setYear(1999);
		Set<Book> books = new HashSet<>();
		books.add(b1);
		books.add(b2);
		
		
		Genre g = new Genre("G1");
		//g.setBooks(books);
		
		g.setId(3);
		b1.setGenre(g);
		*/
		//bookService.addBook(b1);
		
		return new ModelAndView("books", "books", bookService.getAllBooks());
	}
}
