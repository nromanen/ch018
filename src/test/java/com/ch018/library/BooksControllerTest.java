package com.ch018.library;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class BooksControllerTest {
	
	private Genre genre = new Genre();
	private Book book = new Book();
	private Book book2 = new Book();
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private PersonService personService;

	@Autowired
	private BooksInUseService booksInUseService;

	@Autowired
	private OrdersService ordersService;
	
	@Autowired 
	private MessageSource messageSource;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(personService);
		Mockito.reset(bookService);
		Mockito.reset(genreService);
		Mockito.reset(ordersService);
//		Mockito.reset(messageSource);
		Mockito.reset(booksInUseService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		List<Book> books = new ArrayList<>();
		
		
		genre.setId(1);
		genre.setName("Genre");
		
		
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
		book2.setGenre(genre);
		book2.setImage("img");
		
		books.add(book);
		books.add(book2);
		
		
		when(bookService.getAllBooks()).thenReturn(books);
		when(genreService.getAllGenres()).thenReturn(Arrays.asList(genre));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewOrUpdateBook() throws Exception {
		//book.setTerm(-1);
		mockMvc.perform(post("/book/update")
				.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
				.content( new ObjectMapper().writeValueAsBytes(book))
				)
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testShowBooks() throws Exception {
		mockMvc.perform(get("/books"))
				.andExpect(status().isOk())
				.andExpect(view().name("books"))
				.andExpect(model().attribute("genre", hasSize(1)))
				.andExpect(model().attribute("genre", hasItem(genre)))
				.andExpect(model().attribute("books", hasSize(2)))
				.andExpect(model().attribute("books", hasItem(book)))
				.andExpect(model().attribute("books", hasItem(book2)));
		verify(bookService, times(1)).getAllBooks();
		verifyNoMoreInteractions(bookService);
		verify(genreService, times(1)).getAllGenres();
		verifyNoMoreInteractions(genreService);
	}
	
	@Test
	public void testShowBooksWhisFailGetParam() throws Exception {
		mockMvc.perform(get("/books").param("show", "wrongparam"))
				.andExpect(status().isOk())
				.andExpect(view().name("books"))
				.andExpect(model().attribute("genre", hasSize(1)))
				.andExpect(model().attribute("genre", hasItem(genre)))
				.andExpect(model().attribute("books", hasSize(2)))
				.andExpect(model().attribute("books", hasItem(book)))
				.andExpect(model().attribute("books", hasItem(book2)));
		verify(bookService, times(1)).getAllBooks();
		verifyNoMoreInteractions(bookService);
		verify(genreService, times(1)).getAllGenres();
		verifyNoMoreInteractions(genreService);
	}
	
	@Test
	public void testShowBooksReturn() throws Exception {
		when(booksInUseService.getAllBooks()).thenReturn(Arrays.asList(book));
		mockMvc.perform(get("/books").param("show", "return"))
				.andExpect(status().isOk())
				.andExpect(view().name("books"))
				.andExpect(model().attribute("genre", hasSize(1)))
				.andExpect(model().attribute("genre", hasItem(genre)))
				.andExpect(model().attribute("books", hasSize(1)))
				.andExpect(model().attribute("books", hasItem(book)));
		verify(booksInUseService, times(1)).getAllBooks();
		verifyNoMoreInteractions(booksInUseService);
		verify(genreService, times(1)).getAllGenres();
		verifyNoMoreInteractions(genreService);
	}
	
	@Test
	public void testShowBooksReturnTd() throws Exception {
		when(booksInUseService.getReturnBooksToday()).thenReturn(Arrays.asList(book2));
		mockMvc.perform(get("/books").param("show", "returntd"))
				.andExpect(status().isOk())
				.andExpect(view().name("books"))
				.andExpect(model().attribute("genre", hasSize(1)))
				.andExpect(model().attribute("genre", hasItem(genre)))
				.andExpect(model().attribute("books", hasSize(1)))
				.andExpect(model().attribute("books", hasItem(book2)));
		verify(booksInUseService, times(1)).getReturnBooksToday();
		verifyNoMoreInteractions(booksInUseService);
		verify(genreService, times(1)).getAllGenres();
		verifyNoMoreInteractions(genreService);
	}
	
	@Test
	public void testShowBooksIssueTd() throws Exception {
		when(ordersService.toIssueToday()).thenReturn(Arrays.asList(book));
		mockMvc.perform(get("/books").param("show", "issuetd"))
				.andExpect(status().isOk())
				.andExpect(view().name("books"))
				.andExpect(model().attribute("genre", hasSize(1)))
				.andExpect(model().attribute("genre", hasItem(genre)))
				.andExpect(model().attribute("books", hasSize(1)))
				.andExpect(model().attribute("books", hasItem(book)));
		verify(ordersService, times(1)).toIssueToday();
		verifyNoMoreInteractions(ordersService);
		verify(genreService, times(1)).getAllGenres();
		verifyNoMoreInteractions(genreService);
	}
	
	@Test
	public void testShowBooksIssuePh() throws Exception {
		when(ordersService.toIssuePerHour()).thenReturn(Arrays.asList(book2));
		mockMvc.perform(get("/books").param("show", "issueph"))
				.andExpect(status().isOk())
				.andExpect(view().name("books"))
				.andExpect(model().attribute("genre", hasSize(1)))
				.andExpect(model().attribute("genre", hasItem(genre)))
				.andExpect(model().attribute("books", hasSize(1)))
				.andExpect(model().attribute("books", hasItem(book2)));
		verify(ordersService, times(1)).toIssuePerHour();
		verifyNoMoreInteractions(ordersService);
		verify(genreService, times(1)).getAllGenres();
		verifyNoMoreInteractions(genreService);
	}

	@Test
	public void testDeleteBook() throws Exception {
		int id = 1;
		when(bookService.deleteBook(id)).thenReturn(1);
		mockMvc.perform(delete("/book/delete{id}", id))
				.andExpect(status().isOk());
		verify(bookService, times(1)).deleteBook(1);
        verifyNoMoreInteractions(bookService);
	}

	@Test
	public void testAllSearch() throws Exception {
		String search = "Java";
		String field = "all";
		when(bookService.simpleSearch(search)).thenReturn(Arrays.asList(book, book2));
		mockMvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("search", search)
				.param("field", field)
				)
				.andExpect(status().isOk())
				.andExpect(view().name("books"))
				.andExpect(model().attribute("genre", hasSize(1)))
				.andExpect(model().attribute("genre", hasItem(genre)))
				.andExpect(model().attribute("books", hasSize(2)))
				.andExpect(model().attribute("books", hasItem(book)))
				.andExpect(model().attribute("books", hasItem(book2)));
				
		verify(bookService, times(1)).simpleSearch(search);
		verifyNoMoreInteractions(bookService);
		verify(genreService, times(1)).getAllGenres();
		verifyNoMoreInteractions(genreService);
	}
	
	@Test
	public void testParamSearch() throws Exception {
		String search = "Java";
		String field = "title";
		when(bookService.paramSearch(field, search)).thenReturn(Arrays.asList(book));
		mockMvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("search", search)
				.param("field", field)
				)
				.andExpect(status().isOk())
				.andExpect(view().name("books"))
				.andExpect(model().attribute("genre", hasSize(1)))
				.andExpect(model().attribute("genre", hasItem(genre)))
				.andExpect(model().attribute("books", hasSize(1)))
				.andExpect(model().attribute("books", hasItem(book)));
				
		verify(bookService, times(1)).paramSearch(field, search);
		verifyNoMoreInteractions(bookService);
		verify(genreService, times(1)).getAllGenres();
		verifyNoMoreInteractions(genreService);
	}

}
