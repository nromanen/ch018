/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import com.ch018.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

   
 

@Controller
public class AuthorizedUserController {
   
    @Autowired
    BookService book;
    
    @RequestMapping(value="/authorizedUser")
    public ModelAndView welomePage(){
       return new ModelAndView("authorizedUser","latest",book.getBooksById(1)); 
    }
    
    
}
