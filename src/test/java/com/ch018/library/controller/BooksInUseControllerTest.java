package com.ch018.library.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.IConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class BooksInUseControllerTest {
	
	private Book book = new Book();
	private Person person = new Person();
	private BooksInUse booksInUse = new BooksInUse();
	
	private MockMvc mockMvc;
	
	@Autowired
	private BooksInUseService booksInUseService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Mockito.reset(personService);
		Mockito.reset(bookService);
		Mockito.reset(booksInUseService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		book.setAuthors("Authors");
		book.setAvailable(1);
		book.setBookcase(1);
		book.setCount(1);
		book.setDescription("desc");
		book.setId(1);
		book.setPages(200);
		book.setPublication("public");
		book.setRating(1);
		book.setShelf(1);
		book.setTerm(14);
		book.setTitle("title");
		book.setYear(2000);
		book.setImage("img");
		
		person.setId(1);
		person.setEmail("test@test.com");
		person.setCellphone("0000000000");
		person.setPassword("0000");
		
		booksInUse.setBook(book);
		booksInUse.setBuid(1);
		booksInUse.setPerson(person);
		
		book.setBooksinuses(new HashSet<>(Arrays.asList(booksInUse)));
		person.setBooksinuses(new HashSet<>(Arrays.asList(booksInUse)));
		
		when(booksInUseService.getById(1)).thenReturn(booksInUse);
		when(bookService.getBooksByIdWithUses(1)).thenReturn(book);
		when(personService.getByIdWithBooks(1)).thenReturn(person);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShowBookInUseByBook() throws Exception {
		mockMvc.perform(get("/bookusers/{query}/{id}", "book", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("bookinuse"))
				//.andExpect(model().attribute("book", hasItem(book)))
				.andExpect(model().attribute("booksinuse", hasItem(booksInUse)));
		
		verify(bookService, times(1)).getBooksByIdWithUses(1);
		verifyNoMoreInteractions(bookService);
	}
	
	@Test
	public void testShowBookInUseByUser() throws Exception {
		mockMvc.perform(get("/bookusers/{query}/{id}", "user", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("bookinuse"))
				//.andExpect(model().attribute("person", hasItem(person)))
				.andExpect(model().attribute("booksinuse", hasItem(booksInUse)));
		verify(personService, times(1)).getByIdWithBooks(1);
		verifyNoMoreInteractions(personService);
	}

	@Test
	public void testDeleteInUse() throws Exception {
		mockMvc.perform(delete("/booksinuse/delete{id}", 1))
				.andExpect(status().isOk());
	}

	@Test
	public void testReturnBook() throws Exception {
		mockMvc.perform(get("/booksinuse/return{id}", 1))
				.andExpect(status().isOk());
	}

}
