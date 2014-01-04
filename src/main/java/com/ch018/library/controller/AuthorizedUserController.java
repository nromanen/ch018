/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import java.security.Principal;

import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

   
 

@Controller
public class AuthorizedUserController {
   
    @Autowired
    BookService book;
    
    @Autowired
    PersonService persService;
    
    @RequestMapping("/")
    public String welomePage(Model model){
    	model.addAttribute("latest",book.getAllBooks());
    	return "index"; 
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value="/userAccount", method = RequestMethod.GET)
    public Model viewAccount(Model model, Principal principal){
        model.addAttribute("person", persService.getByEmail(principal.getName()));
        return model;
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value="/showAccount", method=RequestMethod.GET)
    public void editProfile(){
        
    }
}
