package com.ch018.library.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.entity.Person;
import com.ch018.library.form.AdvancedSearch;
import com.ch018.library.form.Password;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.LocalizationService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
import com.ch018.library.util.IConstants;
import com.ch018.library.validator.AccountValidation;
import com.ch018.library.validator.ChangePasswordValid;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml" })
@WebAppConfiguration
public class AuthorizedUserControllerTest {

	private Genre genre = new Genre();
	private Genre genre2 = new Genre();
	private Localization localization = new Localization();
	private Book book = new Book();
	private Person person = new Person();
	private Book book2 = new Book();
	List<Book> books = new ArrayList<>();
	private String search = "java";
	private AdvancedSearch advancedSearch = new AdvancedSearch();
	private Password password = new Password();
	private BooksInUse booksInUse = new BooksInUse();

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private BookService bookService;

	@Autowired
	private PersonService personService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private WishListService wishListService;

	@Autowired
	private AccountValidation accountValidation;

	@Autowired
	private ChangePasswordValid changePasswordValid;

	@Autowired
	private BooksInUseService booksInUseService;

	@Autowired
	private LocalizationService localizationService;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(personService);
		Mockito.reset(bookService);
		Mockito.reset(genreService);
		Mockito.reset(booksInUseService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();

		genre.setId(1);
		genre.setName("Genre");
		genre2.setId(2);
		genre2.setName("New");

		localization.setGenre(genre);
		localization.setId(1);
		localization.setLanguage("en");
		localization.setLocalizedName("Genre");
		
		person.setId(1);
		person.setCellphone("0000000000");
		person.setConfirm(true);
		person.setEmail("test@test.tst");
		person.setEmailConfirmed(true);
		person.setFailedOrders(0);
		person.setMultibookAllowed(10);
		person.setName("User");
		person.setPassword("$2a$10$Gp.aP6XfF5mZRyzrL5FNTuiw2wyoTOjZFsrLPl9Tg8/qaICgK7qRK");
		person.setRating(1);
		person.setRole("ROLE_USER");
		person.setSms(false);
		person.setSurname("Surname");
		person.setTimelyReturns(2);
		person.setUntimelyReturns(0);
		person.setVerificationKey(null);

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
		book.setNumberOfEvaluations(0);

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

		advancedSearch.setAuthors("Authors2");
		advancedSearch.setAvailable(false);
		advancedSearch.setPublication("public2");
		advancedSearch.setTitle("title2");
		advancedSearch.setGenre(genre2.getId());
		advancedSearch.setYear(2002);

		books.add(book);
		books.add(book2);

		genre.setBooks(new HashSet<>(Arrays.asList(book)));
		genre2.setBooks(new HashSet<>(Arrays.asList(book2)));
		
		password.setPassword("123456");
		password.setNewPassword("654321");
		password.setConfirmPassword("654321");
		
		booksInUse.setBook(book);
		booksInUse.setBuid(1);
		booksInUse.setIssueDate(new Date());
		booksInUse.setMark(5);
		booksInUse.setPerson(person);
		booksInUse.setReturnDate(new Date());

		when(genreService.getAllGenres("en")).thenReturn(Arrays.asList(genre));
		when(localizationService.getName(genre.getId(), "en")).thenReturn(
				genre.getName());
		when(bookService.getAllBooks(0, IConstants.PAGE_SIZE, "name", false))
				.thenReturn(books);
		when(bookService.getBooksById(1)).thenReturn(book);
		when(bookService.countBooks()).thenReturn(2L);
		when(bookService.simpleSearchCount(search)).thenReturn(1L);
		when(bookService.advancedSearchCount(advancedSearch)).thenReturn(1L);
		when(bookService.getAllBooks(0, IConstants.PAGE_SIZE, "id", true))
				.thenReturn(books);
		when(bookService.simpleSearch(search, 0, IConstants.PAGE_SIZE, "id",
						false)).thenReturn(Arrays.asList(book));
		when(bookService.advancedSearch(advancedSearch, 0,
						IConstants.PAGE_SIZE)).thenReturn(Arrays.asList(book2));
		when(personService.getByEmail(person.getEmail())).thenReturn(person);
		when(personService.updatePassword(password, person)).thenReturn(true);
		when(booksInUseService.getById(1)).thenReturn(booksInUse);
		//when(principal.getName()).thenReturn(person.getEmail());
	}

	@After
	public void tearDown() throws Exception {
		books.clear();
	}

	@Test
	public void testWelomePage() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(2)))
				.andExpect(model().attribute("latest", hasItem(book)))
				.andExpect(model().attribute("latest", hasItem(book2)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		verify(bookService, times(1)).getAllBooks(0, IConstants.PAGE_SIZE,
				"id", true);
	}

	@Test
	public void testSearch() throws Exception {
		mockMvc.perform(
				get("/search").param("search", search).param("page", "1")
						.param("sort", "id").param("isasc", "false")
						.sessionAttr("indexSearch", search))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(1)))
				.andExpect(model().attribute("latest", hasItem(book)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		verify(bookService, times(1)).simpleSearch(search, 0,
				IConstants.PAGE_SIZE, "id", false);
	}

	@Test
	public void testAdvancedSearch() throws Exception {
		mockMvc.perform(
				get("/advsearch").sessionAttr("advancedSearch", advancedSearch)
						.param("title", advancedSearch.getTitle())
						.param("genre", advancedSearch.getGenre().toString())
						.param("authors", advancedSearch.getAuthors())
						.param("publication", advancedSearch.getPublication())
						.param("year", advancedSearch.getYear().toString()))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(1)))
				.andExpect(model().attribute("latest", hasItem(book2)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		verify(bookService, times(1)).advancedSearch(advancedSearch, 0,
				IConstants.PAGE_SIZE);
	}
	
	@Test
	public void testGetTitles() throws Exception {
		AdvancedSearch advancedSearch2 = new AdvancedSearch();
		advancedSearch2.setTitle("tit");
		advancedSearch2.setGenre(0);
		advancedSearch2.setAuthors("");
		advancedSearch2.setPublication("");
		advancedSearch2.setYear(0);
		when(bookService.advancedSearch(advancedSearch2, 0,	0))
				.thenReturn(Arrays.asList(book2));
		
		String actual = mockMvc.perform(
				get("/advsearch/getTitles").param("term", "tit"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String expected = "[\"title2\"]";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetAuthors() throws Exception {
		AdvancedSearch advancedSearch2 = new AdvancedSearch();
		advancedSearch2.setTitle("");
		advancedSearch2.setGenre(0);
		advancedSearch2.setAuthors("Aut");
		advancedSearch2.setPublication("");
		advancedSearch2.setYear(0);
		when(bookService.advancedSearch(advancedSearch2, 0,	0))
				.thenReturn(Arrays.asList(book2));
		
		String actual = mockMvc.perform(
				get("/advsearch/getAuthors").param("term", "Aut"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String expected = "[\"Authors2\"]";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetPublics() throws Exception {
		AdvancedSearch advancedSearch2 = new AdvancedSearch();
		advancedSearch2.setTitle("");
		advancedSearch2.setGenre(0);
		advancedSearch2.setAuthors("");
		advancedSearch2.setPublication("pub");
		advancedSearch2.setYear(0);
		when(bookService.advancedSearch(advancedSearch2, 0,	0))
				.thenReturn(Arrays.asList(book2));
		
		String actual = mockMvc.perform(
				get("/advsearch/getPublics").param("term", "pub"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String expected = "[\"public2\"]";
		assertEquals(expected, actual);
	}

	@Test
	public void testBookPage() throws Exception {
		mockMvc.perform(get("/book/{id}", 1)).andExpect(status().isOk())
				.andExpect(view().name("book"))
				.andExpect(model().attribute("book", book));
		verify(bookService, times(1)).getBooksById(1);
	}

	@Test
	public void testRate() throws Exception {
		mockMvc.perform(get("/rate").param("bookID", "1")
				.param("buID", "1"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("book", book))
				.andExpect(model().attribute("buid", 1))
				.andExpect(model().attribute("votes", book.getNumberOfEvaluations()));
		verify(bookService, times(1)).getBooksById(1);
	}

	@Test
	public void testViewAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditProfile() {
		fail("Not yet implemented");
	}

	@Test
	public void testPasswordView() throws Exception {
		mockMvc.perform(post("/pass").param("password", "123456")
				.param("confirmPassword", "654321")
				.param("newPassword", "654321"))
				.andExpect(status().isOk());
	}

	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

}
