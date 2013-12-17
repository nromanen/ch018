package com.ch018.library.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
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
		/*
		Book b1 = new Book();
		b1.setAuthors("00ghn00");
		b1.setBookcase(1);
		b1.setDescription("afghnsas");
		b1.setPages(5);
		b1.setPublication("asfghmda");
		b1.setShelf(1);
		b1.setTerm(14);
		b1.setTitle("asssfghmss");
		b1.setYear(1999);
		Book b2 = new Book();
		b2.setAuthors("0fghm000");
		b2.setBookcase(1);
		b2.setDescription("asdsffghas");
		b2.setPages(5);
		b2.setPublication("asgfsdgda");
		b2.setShelf(1);
		b2.setTerm(14);
		b2.setTitle("assssgfsfgfgfss");
		b2.setYear(1999);
		Set<Book> books = new HashSet<>();
		books.add(b1);
		books.add(b2);
		
		
		Genre g = new Genre("G6");
		//g.setBooks(books);
		genreService.addGenre(g);
		
		
		//g.setId(3);
		b1.setGenre(g);
		b2.setGenre(g);
		bookService.addBook(b1);
		bookService.addBook(b2);
		
		Person p1 = new Person();
		p1.setCellphone("111111111");
		p1.setConfirm(true);
		p1.setEmail("asda");
		p1.setFailedOrders(0);
		p1.setName("name");
		p1.setPassword("014014");
		p1.setRole("ADMINISTRATOR");
		p1.setSalt("dsd");
		p1.setSms(false);
		p1.setSurname("asddsas");
		
		personService.save(p1);
		
		*/
		Book book = bookService.getBooksById(1);
		/*Person person = personService.getById(1);
		
		BooksInUse biu = new BooksInUse();
		biu.setBook(book);
		biu.setPerson(person);
		biu.setInUse(true);
		biu.setIssueDate(new Date());
		biu.setReturnDate(new Date());
		biu.setTerm(book.getTerm());
		
		
		booksInUseService.addBooksInUse(biu);
		*/
		
		//book.setDescription("new desc");
		
		//bookService.updateBook(book.getId(), book);
		
		return new ModelAndView("books", "books", bookService.getAllBooks());
	}
}
