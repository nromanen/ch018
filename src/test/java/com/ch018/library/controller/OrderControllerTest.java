package com.ch018.library.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class OrderControllerTest {

	private Book book = new Book();
	private Person person = new Person();
	private Orders order = new Orders();
	
	private MockMvc mockMvc;
	
	@Autowired
	private BooksInUseService booksInUseService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() throws Exception {
		Mockito.reset(personService);
		Mockito.reset(bookService);
		Mockito.reset(ordersService);
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
		
		order.setBook(book);
		order.setId(1);
		order.setPerson(person);
		
		book.setOrders(new HashSet<>(Arrays.asList(order)));
		person.setOrders(new HashSet<>(Arrays.asList(order)));
		
		when(ordersService.getById(1)).thenReturn(order);
		when(bookService.getBooksByIdWithOrders(1)).thenReturn(book);
		when(personService.getByIdWithOrders(1)).thenReturn(person);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFail() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrepareOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditIssueDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteOrderInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowOrdersByBook() throws Exception {
		mockMvc.perform(get("/orders/{query}/{id}", "book", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("orders"))
				.andExpect(model().attribute("book", book))
				.andExpect(model().attribute("orders", hasItem(order)));
		
		verify(bookService, times(1)).getBooksByIdWithOrders(1);
		verifyNoMoreInteractions(bookService);
	}
	
	@Test
	public void testShowOrdersByUser() throws Exception {
		mockMvc.perform(get("/orders/{query}/{id}", "user", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("orders"))
				.andExpect(model().attribute("person", person))
				.andExpect(model().attribute("orders", hasItem(order)));
		
		verify(personService, times(1)).getByIdWithOrders(1);
		verifyNoMoreInteractions(personService);
	}

	@Test
	public void testDeleteOrderInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testIssueOrder() {
		fail("Not yet implemented");
	}

}
