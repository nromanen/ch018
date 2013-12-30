package com.ch018.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.service.PersonService;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */

@Controller
public class SecurityController {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/login-error", method=RequestMethod.GET)  
    public ModelAndView invalidLogin() {  
        ModelAndView modelAndView = new ModelAndView("login");  
        modelAndView.addObject("error", true);  
        return modelAndView;  
    }  
      /*
    @RequestMapping(value="/success-login", method=RequestMethod.GET)  
    public ModelAndView successLogin() {  
        return new ModelAndView("success-login");  
    }*/
	
	
}