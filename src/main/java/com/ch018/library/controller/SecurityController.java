package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.Person;
import com.ch018.library.entity.Person.Role;
import com.ch018.library.form.Registration;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.CalculateRating;

/**
 * 
 * @author Yurik Mikhaletskiy
 * 
 */

@Controller
public class SecurityController {

	@Autowired
	PersonService personService;

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView("login");
	}

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
	public ModelAndView invalidLogin() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("error", true);
		return modelAndView;
	}

	/*
	 * @RequestMapping(value="/success-login", method=RequestMethod.GET) public
	 * ModelAndView successLogin() { return new ModelAndView("success-login"); }
	 */

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String regForm(Model model) {
		model.addAttribute("registration", new Registration());
		return "registration";
	}

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String processRegistration(@Valid Registration registration, BindingResult result) {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Person person = personService
				.getByEmail(registration.getEmail().trim());
		if (person == null
				&& registration.getPassword().equals(
						registration.getConfirmPassword())) {
			person = new Person();
			person.setEmail(registration.getEmail().trim());
			person.setPassword(passwordEncoder.encode(registration
					.getPassword()));
			person.setRole(Role.ROLE_USER.toString());
			person.setConfirm(false);
			person.setRating(CalculateRating.getRating());
			person.setMultibookAllowed(10);
			personService.save(person);
		} else
			return "registration";

		return "redirect:/";
	}

}
