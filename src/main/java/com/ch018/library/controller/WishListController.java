/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.WishList;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WishListController {
    
    @Autowired
    WishListService wish;
    
    @Autowired
    OrdersService orderService;
    
    @Autowired
    BookService bookService;
    
    @Autowired
    PersonService personService;
   /* @RequestMapping(value="/wishList")
    public ModelAndView wishlist(){
       return new ModelAndView("wishList","wish",wish.getAllWishes());
    }*/
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value="/wishList")
    public ModelAndView getWisheByPersonId(Principal principal){
        return new ModelAndView("wishList","wishByPers",wish.getWishesByPerson(personService.getByEmail(principal.getName()).getId()));
    }
    
   /*@RequestMapping(value="/wishList", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("order") Orders order, BindingResult result, Model model){
       // int personId = 
       // orderService.addOrder(order);
        model.addAttribute("order", wish.getWishesByPerson(1));
        return "wishList";
    }*/
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public String deleteWish(@RequestParam("del")Integer id){
      //  WishList w = wish.getWishById(id);
        wish.deleteWishById(id);
        return "redirect:/wishList";
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value="/wishlist", method = RequestMethod.GET)
    public String addWish(@RequestParam("bookId") int bookId, Principal principal){   
      Person person = personService.getByEmail(principal.getName());
      Book book = bookService.getBooksById(bookId);
      int personId = person.getId();
      
      if(wish.bookExistInWishList(bookId, personId)){
             return "redirect:/authorizedUser"; 
      }else{
             WishList newWish = new WishList();
             newWish.setPerson(person);
             newWish.setBook(book);
             wish.addWish(newWish);
             return "redirect:/";
      }
            
    }
    
    
}
