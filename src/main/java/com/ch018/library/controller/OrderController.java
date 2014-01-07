/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author win7
 */
@Controller
public class OrderController {
    
    @Autowired
    OrdersService order;
    
    @Autowired
    WishListService wish;
    
    @Autowired
    PersonService pers;
    
    @Autowired
    BookService book;
    
    @Autowired
    BooksInUseService booksInUseService;

    @Autowired
	PersonService personService;
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value="/order", method=RequestMethod.GET)
    public ModelAndView createOrder(Model model, 
                                    @RequestParam("book") int bookId, Principal principal){
        //model.addAllAttributes(wish.getWishesByPerson(id));
      //  List<Orders> uses = new ArrayList<Orders>();
        Book b = book.getBooksById(bookId);
        Person p = pers.getByEmail(principal.getName());
        int personId = p.getId();
        int  uses = order.getOrdersByPersonId(personId).size();
        Orders o = new Orders();
        
        int j = p.getMultibookAllowed();
        String fail = "You exceed your limit at the same time to take the book";
        if (j==uses){
             return new ModelAndView("wishList", "fail", fail);
        }else {
                o.setPerson(p);
                o.setBook(b);
                o.setDate(new java.util.Date());
                order.addOrder(o);}
        return new ModelAndView("order", "newOrder", wish.getWishesByPerson(personId));
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value="/userOrder")
    public ModelAndView showOrder(Principal principal){
        return new ModelAndView("userOrder", "showOrders", order.getOrdersByPersonId(personService.getByEmail(principal.getName()).getId()));
    }
    
    /**
     * Librarian
     */
    @Secured("ROLE_LIBRARIAN")
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String showOrders(@RequestParam("id") Integer id, Model model) {
		List<Orders> orders = (List<Orders>) order.getOrdersByBooksId(id);
		model.addAttribute("orders", orders);
		model.addAttribute("book", book.getBooksById(id));

		return "librarian/orders";
	}

	@Secured("ROLE_LIBRARIAN")
	@RequestMapping(value = "/orders/delete{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteOrder(@PathVariable Integer id) {
		order.deleteOrder(id);
		return "order";
	}

	@Secured("ROLE_LIBRARIAN")
	@RequestMapping(value = "/orders/issue{id}/{days}", method = RequestMethod.GET)
	@ResponseBody
	public String issueOrder(@PathVariable int id, @PathVariable int days) {
		Calendar issueDate = Calendar.getInstance();
		Calendar returnDate = Calendar.getInstance();
		returnDate.add(Calendar.DATE, days);

		BooksInUse booksInUse = new BooksInUse();
		Orders orders = order.getById(id);
		booksInUse.setBook(orders.getBook());
		Person person = orders.getPerson();
		person.setConfirm(true);
		booksInUse.setPerson(person);
		booksInUse.setReturnDate(returnDate.getTime());
		booksInUse.setIssueDate(issueDate.getTime());
		booksInUse.setTerm(days);
		booksInUseService.addBooksInUse(booksInUse);
		order.deleteOrder(id);
		return "librarian/orders";
	}

}
