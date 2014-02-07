package com.ch018.test.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.form.AdvancedSearch;
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
		"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class AuthorizedUserControllerTest {
	
	private Genre genre = new Genre();
	private Genre genre2 = new Genre();
	private Localization localization = new Localization();
	private Book book = new Book();
	private Book book2 = new Book();
	List<Book> books = new ArrayList<>();
	private String search = "java";
	private AdvancedSearch advancedSearch = new AdvancedSearch();
	
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
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		genre.setId(1);
		genre.setName("Genre");
		genre.setId(2);
		genre.setName("New");
		
		localization.setGenre(genre);
		localization.setId(1);
		localization.setLanguage("en");
		localization.setLocalizedName("Genre");
		
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
		
		advancedSearch.setAuthors("Authors2");
		advancedSearch.setAvailable(true);
		advancedSearch.setPublication("public2");
		advancedSearch.setSortby("id");
				
		books.add(book);
		books.add(book2);
		
		genre.setBooks(new HashSet<>(Arrays.asList(book)));
		genre2.setBooks(new HashSet<>(Arrays.asList(book2)));
		
		when(genreService.getAllGenres("en")).thenReturn(Arrays.asList(genre));
		when(localizationService.getName(genre.getId(), "en")).thenReturn(genre.getName());
		when(bookService.getAllBooks(0,IConstants.PAGE_SIZE,"name")).thenReturn(books);
		when(bookService.countBooks()).thenReturn(2L);
		when(bookService.simpleSearchCount(search)).thenReturn(1L);
		when(bookService.countBooksByGenre(search, 1)).thenReturn(1L);
		when(bookService.advancedSearchCount(advancedSearch)).thenReturn(1L);
		when(bookService.countBooks()).thenReturn(1L);
		when(bookService.getAllBooks(0, IConstants.PAGE_SIZE, "id")).thenReturn(books);
		when(bookService.simpleSearch(search, 0, IConstants.PAGE_SIZE, "id")).thenReturn(Arrays.asList(book));
		when(bookService.advancedSearch(advancedSearch, 0, IConstants.PAGE_SIZE)).thenReturn(Arrays.asList(book2));
		when(bookService.getBooksByGenre("", 1, 0, IConstants.PAGE_SIZE, "id")).thenReturn(Arrays.asList(book));
		when(bookService.getBooksByGenre(search, 1, 0, IConstants.PAGE_SIZE, "id")).thenReturn(Arrays.asList(book));
		when(bookService.getBooksByGenreWithAdvSearch(advancedSearch, 2, 0, IConstants.PAGE_SIZE)).thenReturn(Arrays.asList(book2));
		when(bookService.countBooksByGenreWithAdvSearch(advancedSearch, 2)).thenReturn(1L);
		
	}

	@After
	public void tearDown() throws Exception {
		books.clear();
	}

	@Test
	public void testWelomePage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(2)))
				.andExpect(model().attribute("latest", hasItem(book)))
				.andExpect(model().attribute("latest", hasItem(book2)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		verify(bookService, times(1)).getAllBooks(0,IConstants.PAGE_SIZE,"id");
	}
	
	@Test
	public void testWelomePageWithSearch() throws Exception {
		mockMvc.perform(get("/").sessionAttr("indexSearch", search))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(1)))
				.andExpect(model().attribute("latest", hasItem(book)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		verify(bookService, times(1)).simpleSearch(search, 0,IConstants.PAGE_SIZE,"id");
	}
	
	@Test
	public void testWelomePageWithAdvancedSearch() throws Exception {
		mockMvc.perform(get("/").sessionAttr("advancedSearch", advancedSearch))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(1)))
				.andExpect(model().attribute("latest", hasItem(book2)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		verify(bookService, times(1)).advancedSearch(advancedSearch, 0, IConstants.PAGE_SIZE);
	}
	
	@Test
	public void testWelomePageByGenre() throws Exception {
		mockMvc.perform(get("/").param("genre", "1"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(1)))
				.andExpect(model().attribute("latest", hasItem(book)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		//verify(bookService, times(1)).getAllBooks(0,IConstants.PAGE_SIZE,"id");
	}
	
	@Test
	public void testWelomePageByGenreWithSearch() throws Exception {
		mockMvc.perform(get("/")
				.sessionAttr("indexSearch", search)
				.param("genre", "1"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(1)))
				.andExpect(model().attribute("latest", hasItem(book)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		verify(bookService, times(1)).getBooksByGenre(search, 1, 0, IConstants.PAGE_SIZE, "id");
	}
	
	@Test
	public void testWelomePageByGenreWithAdvancedSearch() throws Exception {
		mockMvc.perform(get("/")
				.sessionAttr("advancedSearch", advancedSearch)
				.param("genre", "2"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("latest", hasSize(1)))
				.andExpect(model().attribute("latest", hasItem(book2)))
				.andExpect(model().attribute("pages", 1L))
				.andExpect(model().attribute("page", 1));
		verify(bookService, times(1)).getBooksByGenreWithAdvSearch(advancedSearch, 2, 0, IConstants.PAGE_SIZE);
	}

	@Test
	public void testSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvancedSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testBookPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testRate() {
		fail("Not yet implemented");
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
	public void testPasswordView() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

}
