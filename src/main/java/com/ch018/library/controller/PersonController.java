package com.ch018.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.domain.JsonResponse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.IConstants;
import com.ch018.library.validator.PersonValidation;
/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@Controller
public class PersonController {

	@Autowired
	private BookService bookService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private PersonService personService;

	@Autowired
	private BooksInUseService booksInUseService;
	
	@Autowired
	private PersonValidation personValidation;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showUsers(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			Model model, HttpSession session) {
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
		model.addAttribute("persons", personService.getAll(currentPos,IConstants.PAGE_SIZE, field));
		model.addAttribute("person", person);
		return "users";
	}

	@RequestMapping(value = "/user/delete{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteUser(@PathVariable Integer id) {
		return personService.delete(id);
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse newPerson(@RequestBody @Valid Person person,
			BindingResult result, HttpServletRequest request) {
		personValidation.validate(person, result);
		JsonResponse resp = new JsonResponse();
		if (!result.hasErrors()) {
			resp.setStatus("SUCCESS");
			if (person.getId() == 0) {
				personService.librarianSavePerson(person, request);
			} else {
				Person person2 = personService.getById(person.getId());
				personService.librarianUpdatePerson(person, person2);
			}
			resp.setResult(person);
		} else {
			resp.setStatus("FAIL");
			resp.setResult(result.getAllErrors());
		}

		return resp;
	}

}
