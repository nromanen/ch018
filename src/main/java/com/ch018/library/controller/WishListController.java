package com.ch018.library.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
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
       
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/wishList")
    public ModelAndView getWisheByPersonId(Principal principal) {
        return new ModelAndView("wishList", "wishByPers", wish.getWishesByPerson(personService.getByEmail(principal.getName()).getId()));
    }
    
   /* @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteWish(@RequestParam("del")Integer id) {
        wish.deleteWishById(id);
        return "redirect:/wishList";
    } */
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public int deleteWish2(@PathVariable int id){
    	wish.deleteWishById(id);
    	return 0;
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/wishlist/{id}", method = RequestMethod.GET)
    @ResponseBody
    public int addWish(@PathVariable int id,
                          Principal principal) {   
      Person person = personService.getByEmail(principal.getName());
      Book book = bookService.getBooksById(id);
      int personId = person.getId();
      
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
