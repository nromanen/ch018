package com.ch018.library.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.form.Registration;
import com.ch018.library.service.PersonService;
import com.ch018.library.validator.RegistrationValidation;

/**
 * 
 * @author Yurik Mikhaletskiy
 * 
 */

@Controller
public class SecurityController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private RegistrationValidation registrationValidation;
	
	
	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView("login");
	}

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String regForm(Model model) {
		model.addAttribute("registration", new Registration());
		return "registration";
	}

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String processRegistration(@Valid Registration registration, BindingResult result) {
		registrationValidation.validate(registration, result);
		if (result.hasErrors()) {
			return "registration";
		}
		personService.registrate(registration);
		return "redirect:/";
	}

}
