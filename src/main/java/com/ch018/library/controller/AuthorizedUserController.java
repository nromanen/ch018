// TODO: License, WHAT?

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import com.ch018.library.form.Password;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
import com.ch018.library.util.IConstants;
import com.ch018.library.validator.AccountValidation;
import com.ch018.library.validator.ChangePasswordValid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


// TODO: use only spaces or only tabs, remove trailing spaces, unnecessary double carriage returns in all files
   
 

@Controller
public class AuthorizedUserController {
   
    @Autowired
    private BookService book;
    
    @Autowired
    private PersonService persService; //TODO: replace with meaningful and consistent variable name - book, persService, genreService. Check other files too.
    
    @Autowired
    private GenreService genreService;
    
    @Autowired 
    private WishListService wishListService;
   
    @Autowired 
    private AccountValidation accountValidation;
    
    @Autowired 
    private ChangePasswordValid changePass;
    
    // TODO: add carriage return after parameter list to separate parameters and method body
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welomePage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "show", required = false) String show,
			@RequestParam(value = "genre", required = false) Integer id, Model model, HttpSession session) {
		if (show != null && show.equals("all")){
			session.removeAttribute("indexSearch");
		}
		String search = (String) session.getAttribute("indexSearch");
		long pages = 1;
		
		if (id == null) {
			if (search != null) {
				long count = book.simpleSearchCount(search);
				pages = (int) Math.ceil(count / (float) IConstants.PAGE_SIZE);
				int currentPos = (page - 1) * IConstants.PAGE_SIZE;
				model.addAttribute("latest", book.simpleSearch(search, currentPos, IConstants.PAGE_SIZE, "id"));
			} else {
				long count = book.countBooks();
				pages = (int) Math.ceil(count / (float) IConstants.PAGE_SIZE);
				int currentPos = (page - 1) * IConstants.PAGE_SIZE;
				model.addAttribute("latest", book.getAllBooks(currentPos, IConstants.PAGE_SIZE, "id"));
			}
		} else {
			//session.removeAttribute("indexSearch");
			if (search == null) {
				Genre genre = genreService.getGenreByIdWithBooks(id);
				model.addAttribute("latest", genre.getBooks());
			} else {
				long count = book.countBooksByGenre(search, id);
				pages = (int) Math.ceil(count / (float) IConstants.PAGE_SIZE);
				int currentPos = (page - 1) * IConstants.PAGE_SIZE;
				model.addAttribute("latest", book.getBooksByGenre(search, id, currentPos, IConstants.PAGE_SIZE, "id"));
			}
		}
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(@RequestParam String search, Model model, HttpSession session) {
		List<Book> books = new ArrayList<Book>();
		session.setAttribute("indexSearch", search);
		long count = book.simpleSearchCount(search);
		long pages = (int) Math.ceil(count / (float) IConstants.PAGE_SIZE);
		model.addAttribute("pages", pages);
		model.addAttribute("page", 1);
		books.addAll(book.simpleSearch(search, 0, IConstants.PAGE_SIZE, "id"));
		model.addAttribute("latest", books);
		return "index";
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String bookPage(@PathVariable(value = "id") Integer id,
			Model model) {
		Book books = book.getBooksById(id);
		model.addAttribute("book", books);
		return "book";
	}
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" }) // TODO: these strings are good as constants somewhere
    @RequestMapping(value = "/userAccount", method = RequestMethod.GET)
    public Model viewAccount(Model model, Principal principal) {
     model.addAttribute("person", persService.getByEmail(principal.getName()));
     return model;
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/userAccount", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("person") @Valid Person updtPers, 
                              BindingResult result, Principal principal, HttpServletRequest request) {
        accountValidation.validate(updtPers, result);
        Person person = persService.getByEmail(principal.getName());
        if (result.hasErrors()) {
            return "userAccount";
        }
        
        if(!person.getEmail().equals(updtPers.getEmail())) { 
            person = persService.updateAccProperties(person, updtPers, request);
            persService.update(person);
            return "redirect:/logout"; 
        }
        person = persService.updateAccProperties(person, updtPers, request);
        persService.update(person);
        return "userAccount";
    }
    
	@Secured({ "ROLE_USER", "ROLE_LIBRARIAN" })
	@RequestMapping(value = "/pass", method = RequestMethod.POST)
	public String passwordView(@ModelAttribute("password") Password password,
			BindingResult result, Principal principal) {
	       changePass.validate(password, result);
               if(result.hasErrors()){
                     return "pass";
               } else {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Person person = persService.getByEmail(principal.getName());
			if (BCrypt.checkpw(password.getPassword(), person.getPassword()))
				if (password.getNewPassword().equals(
						password.getConfirmPassword())) {
					person.setPassword(passwordEncoder.encode(password
							.getNewPassword()));
					persService.update(person);
				}
			return "redirect:/logout";
		}
	}
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public void changePassword(@ModelAttribute("password")Password password, 
                               BindingResult result, 
                               Principal principal) {
     
    } 
    
}
