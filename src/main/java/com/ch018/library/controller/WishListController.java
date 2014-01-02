/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.WishList;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
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
    
    @RequestMapping(value="/wishList")
    public ModelAndView getWisheByPersonId(){
        return new ModelAndView("wishList","wishByPers",wish.getWishesByPerson(1));
    }
    
   /*@RequestMapping(value="/wishList", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("order") Orders order, BindingResult result, Model model){
       // int personId = 
       // orderService.addOrder(order);
        model.addAttribute("order", wish.getWishesByPerson(1));
        return "wishList";
    }*/
    
    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public String deleteWish(@RequestParam("del")Integer id){
      //  WishList w = wish.getWishById(id);
        wish.deleteWishById(id);
        return "redirect:/wishList";
    }
    
    @RequestMapping(value="/wishlist", method = RequestMethod.GET)
    public String addWish(@RequestParam("bookId") int bookId,
                          @RequestParam("persId") int personId){
      Person person = personService.getById(personId);
      Book book = bookService.getBooksById(bookId);
      WishList newWish = new WishList();
      newWish.setPerson(person);
      newWish.setBook(book);
      wish.addWish(newWish);
      return "redirect:/wishList";      
    }
    
    
}
