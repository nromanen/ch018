package com.ch018.test.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.IConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class PersonControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private PersonService personService;

	@Autowired
	private BooksInUseService booksInUseService;
		
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(personService);
		Mockito.reset(bookService);
		Mockito.reset(genreService);
		Mockito.reset(booksInUseService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testShowUsers() throws Exception  {
		List<Person> persons = new ArrayList<>();
		
		Person person = new Person();
		person.setId(1);
		person.setEmail("test@test.com");
		person.setCellphone("0000000000");
		person.setPassword("0000");
		
		Person person2 = new Person();
		person2.setId(2);
		person2.setEmail("test2@test.com");
		person2.setCellphone("0000000002");
		person2.setPassword("0002");
		
		Person person3 = new Person();
		person3.setId(0);
		person3.setEmail("");
		persons.add(person);
		persons.add(person2);
		when(personService.getAll(0,IConstants.PAGE_SIZE,"id")).thenReturn(persons);
		
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");

		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(view().name("users"))
				.andExpect(model().attribute("person", person3))
				.andExpect(model().attribute("persons", hasSize(2)))
				.andExpect(model().attribute("persons", hasItem(
						allOf(
								hasProperty("id", is(1)),
								hasProperty("email", is("test@test.com")),
								hasProperty("cellphone", is("0000000000")),
								hasProperty("password", is("0000"))
								)
						)))
				.andExpect(model().attribute("persons", hasItem(
						allOf(
								hasProperty("id", is(2)),
								hasProperty("email", is("test2@test.com")),
								hasProperty("cellphone", is("0000000002")),
								hasProperty("password", is("0002"))
								)
						))
						);
		verify(personService, times(1)).getAll(0,IConstants.PAGE_SIZE,"id");
		//verifyNoMoreInteractions(personService);
	}

	@Test
	public void testDeleteUser() throws Exception {
		Person person = new Person();
		person.setId(1);
		person.setEmail("test@test.com");
		person.setCellphone("0000000000");
		person.setPassword("0000");
		
		when(personService.delete(1)).thenReturn(1);
		mockMvc.perform(delete("/user/delete{id}", 1))
				.andExpect(status().isOk());
		verify(personService, times(1)).delete(1);
		verifyNoMoreInteractions(personService);

	}

	@Test
	public void testNewPerson() throws Exception {
		Person person = new Person();
		person.setId(1);
		person.setEmail("testtest.com");
		person.setCellphone("0000000000");
		person.setPassword("00000000");
		person.setFailedOrders(0);
		person.setMultibookAllowed(10);
		person.setName("name");
		person.setRating(1);
		person.setSurname("surname");
		person.setTimelyReturns(0);
		person.setUntimelyReturns(0);
		
		
		mockMvc.perform(post("/user/update")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.sessionAttr("person", person)
				)
//				.andExpect(status().isOk())
//				.andExpect(view().name("users"))
//				.andExpect(model().attributeHasFieldErrors("person", "email"))
				;
	}

}
