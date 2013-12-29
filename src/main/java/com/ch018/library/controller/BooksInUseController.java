package com.ch018.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;

@Controller
public class BooksInUseController {

	@Autowired
	BooksInUseService booksInUseService;
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/bookusers", method = RequestMethod.GET)
	public String showBookInUse(@RequestParam("id") Integer id, Model model) {
		List<BooksInUse> booksInUses = booksInUseService.getByBookId(id);
		model.addAttribute("booksinuse", booksInUses);
		model.addAttribute("book", bookService.getBooksById(id));
		return "bookinuse";
	}
	
	@RequestMapping(value = "/booksinuse/delete{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteInUse(@PathVariable int id) {
		booksInUseService.removeBooksInUse(id);
		return "booksinuse";
	}
	
	@RequestMapping(value = "/booksinuse/return{id}", method = RequestMethod.GET)
	@ResponseBody
	public String returnBook(@PathVariable int id) {
		booksInUseService.removeBooksInUse(id);
		return "bookinuse";
	}
}
