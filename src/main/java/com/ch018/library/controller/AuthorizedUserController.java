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
import com.ch018.library.form.Password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

   
 

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
    @RequestMapping(value="/userAccount", method=RequestMethod.POST)
    public String editProfile(@ModelAttribute("person")Person updtPers, 
                              @ModelAttribute("password")Object password, BindingResult result, Principal principal){
        Person person = persService.getByEmail(principal.getName());
        person = persService.updateAccProperties(person, updtPers);
        persService.update(person);
        return "redirect:/userAccount";
    }
    
    @Secured({"ROLE_USER","ROLE_LIBRARIAN"})
    @RequestMapping(value="/pass", method = RequestMethod.POST)
    public String passwordView(@ModelAttribute("password")Password password, BindingResult result, Principal principal) {
       if(password==null)
       return null; 
       else{
              PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	      Person person = persService.getByEmail(principal.getName());
              if(BCrypt.checkpw(password.getPassword(), person.getPassword()))
                     if(password.getNewPassword().equals(password.getConfirmPassword()))
                  {
                      person.setPassword(passwordEncoder.encode(password.getNewPassword()));
                      persService.update(person);
                  }
       return "redirect:/logout";
       }
    }
    
    @Secured({"ROLE_USER","ROLE_LIBRARIAN"})
    @RequestMapping(value="/pass", method = RequestMethod.GET)
    public void changePassword(@ModelAttribute("password")Password password, BindingResult result, Principal principal){
     
    } 
}
