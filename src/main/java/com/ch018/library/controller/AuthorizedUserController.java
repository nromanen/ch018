/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @RequestMapping(value="/authorizedUser")
    public ModelAndView welomePage(){
       return new ModelAndView("authorizedUser","latest",book.getAllBooks()); 
    }
    
    @RequestMapping(value="/userAccount")
    public void viewAccount(@ModelAttribute("person") Person person, BindingResult result){
        
    }
    
    @RequestMapping(value="/showAccount", method=RequestMethod.GET)
    public String editProfile(@RequestParam("id") int id, Model model){
        model.addAttribute("person", persService.getById(id));
        return "redirect:/userAccount";
    }
}
