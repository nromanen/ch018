package com.ch018.library.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.PersonService;

/**
 *
 * @author okryvortc
 */

@Controller
public class BookInUseController {
    
    @Autowired
    private BooksInUseService inUse;
    
    @Autowired
    private PersonService persService;
    
    @Autowired 
    private BookService bookService;
     
    @RequestMapping(value = "/usersBooks")
    public ModelAndView showMyBooks(Principal principal) {
        List<BooksInUse> books = new ArrayList<BooksInUse>();
        books = inUse.getByPersonId(persService.getByEmail(principal.getName()).getId());
        return new ModelAndView("usersBooks", "books", books);
    }
    
    @RequestMapping(value = "/vote/{rate}/{bookID}/{buID}", method = RequestMethod.GET)
    public String voteRating(@PathVariable float rate, @PathVariable int bookID, @PathVariable int buID){
    	BooksInUse bookInUse = inUse.getById(buID);
    	bookInUse.setMark(rate);
    	inUse.updateBooksInUse(bookInUse);
    	Book book = bookService.getBooksById(bookID);
    	float rating = book.getNumberOfEvaluations() * book.getRating();
    	int number = book.getNumberOfEvaluations();
    	number++;
    	rating+=rate;
    	rating = rating/number;
    	book.setNumberOfEvaluations(number);
    	book.setRating(rating);
    	bookService.updateBook(book);
    	return "usersBooks";
    }
    
}
