package com.ch018.library.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.LocalizationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/fortest/database.xml",
									"file:src/main/webapp/WEB-INF/root-context.xml",
									"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml"
		})
public class BookServiceImplTest {
	
	private Book book = new Book();
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private GenreService genreService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Genre genre = genreService.getGenreById(1);
		book.setAuthors("Authors");
		book.setAvailable(1);
		book.setBookcase(1);
		book.setCount(1);
		book.setDescription("desc");
		book.setPages(200);
		book.setPublication("public");
		book.setRating(1);
		book.setShelf(1);
		book.setTerm(14);
		book.setTitle("title");
		book.setYear(2000);
		book.setImage("img");
		book.setNumberOfEvaluations(0);
		book.setGenre(genre);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*@AfterClass
	public void tearDownAfterClass() throws Exception {
		
	}*/

	@Test
	public void testAddBook() {
		Long expected = bookService.countBooks();
		bookService.addBook(book);
		Long actual = bookService.countBooks();
		expected++;
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateBook() {
		Book expected = bookService.getBooksById(1);
		expected.setTitle("New Title");
		bookService.updateBook(expected);
		Book actual = bookService.getBooksById(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAllBooks() {
		bookService.getAllBooks(0, 0, null, false);
	}

	@Test
	public void testGetAllBooksIntIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByIdWithUses() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByIdWithOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByAuthors() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testSimpleSearchString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSimpleSearchStringIntIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testParamSearchStringStringIntIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testParamSearchStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSimpleSearchCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountBooksByGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvancedSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvancedSearchCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByRating() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountBooksByGenreWithAdvSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByGenreWithAdvSearch() {
		fail("Not yet implemented");
	}

}
