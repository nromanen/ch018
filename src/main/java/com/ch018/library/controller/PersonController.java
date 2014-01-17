package com.ch018.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
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

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showUsers(Model model) {
		Person person = new Person();
		model.addAttribute("persons", personService.getAll());
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
	public Person newPerson(@RequestBody @Valid Person person, HttpServletRequest request) {
		if (person.getId() == 0) {
			personService.librarianSavePerson(person, request);
		} else {
			Person person2 = personService.getById(person.getId());
			personService.librarianUpdatePerson(person, person2);
		}
		return person;
	}

}
