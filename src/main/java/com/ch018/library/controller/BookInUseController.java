/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.PersonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author okryvortc
 */

@Controller
public class BookInUseController {
    
    @Autowired
    BooksInUseService inUse;
    
    @Autowired
    PersonService pers;
    
    @Autowired 
    BookService book;
    
    @RequestMapping(value = "/usersBooks")
    public ModelAndView showMyBooks(){
        List<BooksInUse> books = new ArrayList<BooksInUse>();
        books = inUse.getByPersonId(1);
       return new ModelAndView("usersBooks","books",books);
    }
    
}
