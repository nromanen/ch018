package com.ch018.library.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class WishListControllerTest {

	@Autowired 
    private WishListService wish;
	
	@Autowired
    private PersonService personService; 
	
	@Mock
    private Principal principal;
    
	private MockMvc mockMvc;
	
	private Genre genre = new Genre();
	private Genre genre2 = new Genre();
	
	private WishList wish1 = new WishList();
	private WishList wish2 = new WishList();
	
	private Book book = new Book();
	private Book book2 = new Book();
	
	private Person pers = new Person();
	
	ArrayList<WishList> wishes = new ArrayList<WishList>();
	
	@Before
	public void setUp() throws Exception {
		Mockito.reset(wish);
		
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
		book.setTitle("java");
		book.setYear(2000);
		book.setGenre(genre);
		book.setImage("img");
		
		
		book2.setAuthors("Authors2");
		book2.setAvailable(2);
		book2.setBookcase(2);
		book2.setCount(2);
		book2.setDescription("desc2");
		book2.setId(2);
		book2.setPages(400);
		book2.setPublication("public2");
		book2.setRating(2);
		book2.setShelf(2);
		book2.setTerm(1);
		book2.setTitle("title2");
		book2.setYear(2002);
		book2.setGenre(genre2);
		book2.setImage("img");
		
		pers.setId(19);
		pers.setCellphone("(050) 279-1710");
		pers.setConfirm(true);
		pers.setEmail("alex9523@yandex.ru");
		pers.setEmailConfirmed(true);
		pers.setFailedOrders(0);
		pers.setMultibookAllowed(10);
		pers.setName("Alexandr");
		pers.setSurname("Krivorotenko");
		pers.setPassword("1111");
		pers.setRating(0.88);
		pers.setRole("ROLE_USER");
		pers.setSms(true);
		pers.setTimelyReturns(0);
		pers.setUntimelyReturns(0);
		pers.setVerificationKey("dfghjfghk");
		
		wish1.setBook(book);
		wish1.setPerson(pers);
		wish1.setId(1);
		
		wish2.setBook(book2);
		wish2.setPerson(pers);
		wish2.setId(2);
		
		wishes.add(wish1);
		wishes.add(wish2);
		
		genre.setId(1);
		genre.setName("Genre");
		genre2.setId(2);
		genre2.setName("New");
		
		when(principal.getName()).thenReturn("alex9523@yandex.ru");
		when(personService.getByEmail(principal.getName())).thenReturn(pers);
		when(wish.getWishesByPerson(personService.getByEmail(principal.getName()).getId())).thenReturn(wishes);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetWisheByPersonId() throws Exception {
		mockMvc.perform(get("/wishList"))
		        .andExpect(status().isOk())
		        .andExpect(view().name("wishList"))
		        .andExpect(model().attribute("wishByPers", hasSize(2)))
                .andExpect(model().attribute("wishByPers", hasItem(wish1)))
                .andExpect(model().attribute("wishByPers", hasItem(wish2)));
	}

	@Test
	public void testDeleteWish2() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddWish() {
		fail("Not yet implemented");
	}

}
