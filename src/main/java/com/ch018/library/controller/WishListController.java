package com.ch018.library.controller;

import java.security.Principal;

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

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;


@Controller
public class WishListController {
    
    @Autowired
    private WishListService wish;
    
    @Autowired
    private OrdersService orderService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private BooksInUseService bookInUseService;
    
    @RequestMapping(value = "/wishList")
    public Model getWisheByPersonId(Principal principal, Model model) {
        String email = principal.getName();
        Person person = personService.getByEmail(email);
        int pId = person.getId();
        model.addAttribute("wishByPers", wish.getWishesByPerson(pId));
        return model;
    	//return new ModelAndView("wishList", "wishByPers", wish.getWishesByPerson(personService.getByEmail(principal.getName()).getId()));
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public int deleteWish2(@PathVariable int id){
    	wish.deleteWishById(id);
    	return 0;
    }
    
    @RequestMapping(value = "/wishlist/{id}", method = RequestMethod.GET)
    @ResponseBody
    public int addWish(@PathVariable int id,
                          Principal principal) {   
      Person person = personService.getByEmail(principal.getName());
      Book book = bookService.getBooksById(id);
      int personId = person.getId();
      
      if (bookInUseService.alreadyInUse(id, personId)){
    	  return 2;
      }
      
      if (wish.bookExistInWishList(id, personId)) {
             return 0; 
      } else {
             WishList newWish = new WishList();
             newWish.setPerson(person);
             newWish.setBook(book);
             wish.addWish(newWish);
             return 1;
        }
      
       }
    
    
}
