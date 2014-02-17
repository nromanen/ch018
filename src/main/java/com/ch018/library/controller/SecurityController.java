package com.ch018.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Person;
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
	
	@Autowired 
	private MessageSource messageSource;
		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(@RequestParam(required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", "person.loginerror");
		} else {
			model.addAttribute("error", "person.loginnull");
		}
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String regForm(Model model) {
		model.addAttribute("registration", new Registration());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String processRegistration(@Valid final Registration registration,
			BindingResult result, HttpServletRequest request) {
		registrationValidation.validate(registration, result);
		if (result.hasErrors()) {
			return "registration";
		}
		String message = messageSource.getMessage("message.confirm.registration", new Object[]{}, LocaleContextHolder.getLocale());
		personService.registrate(registration, message, request);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
	public String confirmEmail(Model model, @RequestParam("key") String key) {
		Person person =	personService.getByKey(key);
		if ((person != null) && (person.getEmailConfirmed() == false)) {
			person.setEmailConfirmed(true);
			personService.update(person);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/profile-email/confirm", method = RequestMethod.GET)
	public String updateEmail(Model model, @RequestParam("key") String key, 
			                               @RequestParam("email") String email, HttpServletRequest request) {
		Person person =	personService.getByKey(key);
		if ((person != null) && (person.getEmailConfirmed() == false)) {
			person.setEmail(email);
			person.setConfirm(true);
			person.setEmailConfirmed(true);
			person.setEmailConfirmed(true);
			personService.update(person);
	    }
		return "redirect:/logout";
   }
}
