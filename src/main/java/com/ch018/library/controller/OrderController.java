/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(value="/order", method=RequestMethod.GET)
    public ModelAndView createOrder(Model model, 
                                    @RequestParam("book") int bookId, 
                                    @RequestParam("pers") int personId){
        //model.addAllAttributes(wish.getWishesByPerson(id));
        Book b = null;
        Person p = null;
        Orders o = new Orders();
        b = book.getBooksById(bookId);
        p = pers.getById(personId);
        o.setPerson(p);
        o.setBook(b);
        o.setDate(new java.util.Date());
        order.addOrder(o);
        return new ModelAndView("order", "newOrder", wish.getWishesByPerson(personId));
    }
    
    @RequestMapping(value="/userOrder")
    public ModelAndView showOrder(){
        return new ModelAndView("userOrder", "showOrders", order.getOrdersByPersonId(1));
    }

}
