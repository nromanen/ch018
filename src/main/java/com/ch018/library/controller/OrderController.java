/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

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
    
    @RequestMapping(value="/order", method=RequestMethod.GET)
    public ModelAndView createOrder(Model model, 
                                    @RequestParam("book") int bookId, 
                                    @RequestParam("pers") int personId){
        //model.addAllAttributes(wish.getWishesByPerson(id));
      //  List<Orders> uses = new ArrayList<Orders>();
        Book b = null;
        Person p = null;
        int  uses = order.getOrdersByPersonId(personId).size();
        Orders o = new Orders();
        b = book.getBooksById(bookId);
        p = pers.getById(personId);
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
    
    @RequestMapping(value="/userOrder")
    public ModelAndView showOrder(){
        return new ModelAndView("userOrder", "showOrders", order.getOrdersByPersonId(1));
    }
    
    /**
     * Librarian
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String showOrders(@RequestParam("id") Integer id, Model model) {
		List<Orders> orders = (List<Orders>) order
				.getOrdersByBooksId(id);
		model.addAttribute("orders", orders);
		model.addAttribute("book", book.getBooksById(id));

		return "orders";
	}

	@RequestMapping(value = "/orders/delete{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteOrder(@PathVariable Integer id) {
		order.deleteOrder(id);
		return "order";
	}

	@RequestMapping(value = "/orders/issue{id}", method = RequestMethod.GET)
	@ResponseBody
	public String issueOrder(@PathVariable int id) {
		BooksInUse booksInUse = new BooksInUse();
		Orders orders = order.getById(id);
		booksInUse.setBook(orders.getBook());
		booksInUse.setPerson(orders.getPerson());
		booksInUse.setReturnDate(new Date());
		booksInUse.setIssueDate(new Date());
		booksInUse.setTerm(14);
		booksInUseService.addBooksInUse(booksInUse);
		order.deleteOrder(id);
		return "orders";
	}

}
