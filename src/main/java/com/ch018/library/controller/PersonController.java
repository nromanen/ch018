package com.ch018.library.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@Controller
public class PersonController {

	static Logger log = LogManager.getLogger(BooksController.class);

	@Autowired
	BookService bookService;

	@Autowired
	GenreService genreService;

	@Autowired
	PersonService personService;

	@Autowired
	BooksInUseService booksInUseService;

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

	@RequestMapping(value = "/person/update", method = RequestMethod.POST)
	@ResponseBody
	public Person newPerson(@RequestBody Person person) {

		if (person.getId() == 0) {
			personService.save(person);
		} else {
			Person p = new Person();
			p = personService.getById(person.getId());
			person.setPassword(p.getPassword());
			person.setRole(p.getRole());
			person.setRating(p.getRating());
			personService.update(person);
		}
		return person;
	}

}
