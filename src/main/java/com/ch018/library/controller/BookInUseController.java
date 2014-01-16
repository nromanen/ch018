package com.ch018.library.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    private BookService book;
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/usersBooks")
    public ModelAndView showMyBooks(Principal principal) {
        List<BooksInUse> books = new ArrayList<BooksInUse>();
        books = inUse.getByPersonId(persService.getByEmail(principal.getName()).getId());
        return new ModelAndView("usersBooks", "books", books);
    }
    
}
