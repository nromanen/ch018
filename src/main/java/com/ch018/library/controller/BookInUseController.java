package com.ch018.library.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.History;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.HistoryService;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.JsonResponse;

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
    
    @Autowired
    private HistoryService historyService;
     
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
    	BigDecimal bd = new BigDecimal(rating);
    	bd = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    	book.setNumberOfEvaluations(number);
    	rating = bd.floatValue();
    	book.setRating(rating);
    	bookService.updateBook(book);
    	History history = historyService.getEntry(bookInUse.getPerson(), bookInUse.getBook());
    	history.setMark(rate);
    	historyService.newEntry(history);
    	return "usersBooks";
    }
    
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public String commentIt(@RequestParam String comment, @RequestParam Integer buid) {
    	BooksInUse booksInUse = inUse.getById(buid);
    	History history = historyService.getEntry(booksInUse.getPerson(), booksInUse.getBook());
    	history.setComment(comment);
    	historyService.newEntry(history);
		return "SUCCESS";
    }
    
}
