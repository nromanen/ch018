package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.IConstants;
import com.ch018.library.util.JsonResponse;
import com.ch018.library.validator.PersonValidation;

/**
 * 
 * @author Yurik Mikhaletskiy
 * 
 */
@Secured("ROLE_ADMINISTRATOR")
@Controller
public class AdminController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonValidation personValidation;
	
	/**
	 * 
	 * @param page
	 * @param sort
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET )
	public String adminPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			@RequestParam(value = "isasc", required = false, defaultValue = "false") Boolean isasc,
			Model model, HttpSession session) {
		if (session.getAttribute("isAsc") == null) {
			session.setAttribute("isAsc", isasc);
		}
		Boolean isAsc = (Boolean) session.getAttribute("isAsc");
		if (session.getAttribute("personsort") != null && session.getAttribute("personsort").toString().equals(sort)) {
			isAsc = isasc;
			session.setAttribute("isAsc", isAsc);
		}
		String field =(String) session.getAttribute("personsort");
		if (field == null) {
			session.setAttribute("personsort", sort);
			field =(String) session.getAttribute("personsort");
		}
		if (!sort.equals("id")) {
			session.setAttribute("personsort", sort);
			field =(String) session.getAttribute("personsort");
		}	
		Person person = new Person();
		long count = personService.getCount();
		long pages = (int) Math.ceil(count / (float)IConstants.PAGE_SIZE);
		int currentPos = (page - 1) * IConstants.PAGE_SIZE;
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		person.setEmail("");
		List<String> roles = new ArrayList<>();
		for (Person.Role role : Person.Role.values()) {
			roles.add(role.toString());
		}
		model.addAttribute("role", roles);
		model.addAttribute("persons", personService.getAll(currentPos,IConstants.PAGE_SIZE, field, isAsc));
		model.addAttribute("person", person);
		return "admin";
	}
	
	/**
	 * 
	 * @param person
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse newPerson(@RequestBody @Valid Person person,
			BindingResult result, HttpServletRequest request) {
		personValidation.validate(person, result);
		JsonResponse resp = new JsonResponse();
		if (!result.hasErrors()) {
			resp.setStatus("SUCCESS");
			if (person.getId() == 0) {
				personService.adminSavePerson(person, request);
			} else {
				Person person2 = personService.getById(person.getId());
				personService.adminUpdatePerson(person, person2);
			}
			resp.setResult(person);
		} else {
			resp.setStatus("FAIL");
			resp.setResult(result.getAllErrors());
		}

		return resp;
	}

}
