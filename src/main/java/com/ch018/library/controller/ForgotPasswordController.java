package com.ch018.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Person;
import com.ch018.library.form.ResetPassword;
import com.ch018.library.service.PersonService;
import com.ch018.library.validator.ResetPwdValidation;

@Controller
public class ForgotPasswordController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private ResetPwdValidation resetPwdValidation;

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/remind", method = RequestMethod.GET)
	public String remindForm(Model model,
			@RequestParam(value = "error", required = false) String error) {
		if (error != null) {
			model.addAttribute("message", "wrong email!");
			return "remind";
		}
		model.addAttribute("message", "");
		return "remind";
	}

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/remind", method = RequestMethod.POST)
	public String passwordRestore(@RequestParam String email,
			HttpServletRequest request) {
		Person person = personService.getByEmail(email);
		if (person == null) {
			return "redirect:/remind?error=mail";
		}
		personService.remindPasswoed(person, request);
		return "redirect:/";
	}
	
	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/remind/pass", method = RequestMethod.GET)
	public String resetPassForm(@RequestParam String key, Model model) {
		ResetPassword resetPassword = new ResetPassword();
		resetPassword.setKey(key);
		model.addAttribute("resetPassword", resetPassword);
		return "restore";
	}
	
	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/remind/pass", method = RequestMethod.POST)
	public String passwordRestore(@ModelAttribute @Valid ResetPassword resetPassword) {
		Person person = personService.getByKey(resetPassword.getKey());
		personService.restorePassword(person, resetPassword);
		return "redirect:/";
	}
}
