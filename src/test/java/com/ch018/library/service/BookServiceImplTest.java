package com.ch018.library.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.entity.Person;
import com.ch018.library.form.AdvancedSearch;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.LocalizationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/fortest/database.xml",
									"file:src/main/webapp/WEB-INF/root-context.xml",
									"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml"
		})
public class BookServiceImplTest {
	
	Connection connection; 
	Statement stmt;
	PreparedStatement preparedStmt;
	
	private Book book = new Book();
	private Person person = new Person();
	private BooksInUse booksInUse = new BooksInUse();
	private Localization localization = new Localization();
	private Genre genre = new Genre();
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private static LocalizationService localizationService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library_test", "root", "root");
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
		genre.setId(1);
		book.setGenre(genre);
		genre.setBooks(new HashSet<>(Arrays.asList(book)));
		booksInUse.setBook(book);
		booksInUse.setBuid(1);
		booksInUse.setPerson(person);
		
		try {
			stmt = connection.createStatement();
			stmt.execute("insert into genre (id) VALUES (1)");
			//stmt.execute("insert into booksinuse (id, book_id, person_id) VALUES (1, 1, 1)");
			String saveTestBook = "insert into book(id, authors, available, bookcase, count, description, genre_id, image, "
					+ "numberOfEvaluations, pages, publication, rating, shelf, term, title, year_public) values "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(saveTestBook);
			preparedStmt.setInt(1, 1);
			preparedStmt.setString(2, book.getAuthors());
			preparedStmt.setInt(3, book.getAvailable());
			preparedStmt.setInt(4, book.getBookcase());
			preparedStmt.setInt(5, book.getCount());
			preparedStmt.setString(6, book.getDescription());
			preparedStmt.setInt(7, 1);
			preparedStmt.setString(8, book.getImage());
			preparedStmt.setInt(9, book.getNumberOfEvaluations());
			preparedStmt.setInt(10, book.getPages());
			preparedStmt.setString(11, book.getPublication());
			preparedStmt.setFloat(12, book.getRating());
			preparedStmt.setInt(13, book.getShelf());
			preparedStmt.setInt(14, book.getTerm());
			preparedStmt.setString(15, book.getTitle());
			preparedStmt.setInt(16, book.getYear());
			preparedStmt.executeUpdate();
			preparedStmt.close();


		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@After
	public void tearDown() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library_test", "root", "root");
		try {
			stmt = connection.createStatement();
			stmt.execute("delete from book");
			stmt.execute("delete from genre");
			preparedStmt.close();
			//stmt.execute("delete from booksinuse");
			//stmt.execute("delete from person");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

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
		List<Book> books = new ArrayList<>(bookService.getAllBooks(0, 0, null, false));
		assertEquals(books.size(), 1);
		assertEquals(books.get(0), book);
	}

	@Test
	public void testGetAllBooksIntIntString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetBooksById() {
		Book actual = bookService.getBooksById(1);
		assertEquals(book, actual);
	}

	@Test
	public void testGetBooksByIdWithUses() {
		Book actual = bookService.getBooksByIdWithUses(1);
		assertEquals(book, actual);
		assertEquals(book.getBooksinuses(), actual.getBooksinuses());
	}

	@Test
	public void testGetBooksByIdWithOrders() {
		Book actual = bookService.getBooksByIdWithOrders(1);
		assertEquals(book, actual);
		assertEquals(book.getOrders(), actual.getOrders());
	}

	@Test
	public void testDeleteBook() {
		bookService.deleteBook(1);
		assertEquals(bookService.getAllBooks(0, 0, null, false).size(), 0);
	}

	@Test
	public void testSimpleSearch() {
		try {
			stmt = connection.createStatement();
			String saveTestBook = "insert into book(id, authors, available, bookcase, count, description, genre_id, image, "
					+ "numberOfEvaluations, pages, publication, rating, shelf, term, title, year_public) values "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(saveTestBook);
			preparedStmt.setInt(1, 2);
			preparedStmt.setString(2, "NewAuthor");
			preparedStmt.setInt(3, 2);
			preparedStmt.setInt(4, book.getBookcase());
			preparedStmt.setInt(5, book.getCount());
			preparedStmt.setString(6, book.getDescription());
			preparedStmt.setInt(7, 1);
			preparedStmt.setString(8, book.getImage());
			preparedStmt.setInt(9, book.getNumberOfEvaluations());
			preparedStmt.setInt(10, book.getPages());
			preparedStmt.setString(11, book.getPublication());
			preparedStmt.setFloat(12, book.getRating());
			preparedStmt.setInt(13, book.getShelf());
			preparedStmt.setInt(14, book.getTerm());
			preparedStmt.setString(15, "NewTiitle");
			preparedStmt.setInt(16, book.getYear());
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
		List<Book> books = new ArrayList<>(bookService.simpleSearch("title", 0, 0, "id", false));
		assertEquals(book, books.get(0));
	}

	@Test
	public void testSimpleSearchCount() {
		assertEquals(bookService.simpleSearchCount("title"), 1);
	}

	@Test
	public void testAdvancedSearch() {
		try {
			stmt = connection.createStatement();
			String saveTestBook = "insert into book(id, authors, available, bookcase, count, description, genre_id, image, "
					+ "numberOfEvaluations, pages, publication, rating, shelf, term, title, year_public) values "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(saveTestBook);
			preparedStmt.setInt(1, 2);
			preparedStmt.setString(2, "NewAuthor");
			preparedStmt.setInt(3, 2);
			preparedStmt.setInt(4, book.getBookcase());
			preparedStmt.setInt(5, book.getCount());
			preparedStmt.setString(6, book.getDescription());
			preparedStmt.setInt(7, 1);
			preparedStmt.setString(8, book.getImage());
			preparedStmt.setInt(9, book.getNumberOfEvaluations());
			preparedStmt.setInt(10, book.getPages());
			preparedStmt.setString(11, book.getPublication());
			preparedStmt.setFloat(12, book.getRating());
			preparedStmt.setInt(13, book.getShelf());
			preparedStmt.setInt(14, book.getTerm());
			preparedStmt.setString(15, "NewTitle");
			preparedStmt.setInt(16, book.getYear());
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
		AdvancedSearch advancedSearch = new AdvancedSearch();
		advancedSearch.setAuthors(book.getAuthors());
		advancedSearch.setAvailable(true);
		advancedSearch.setGenre(1);
		advancedSearch.setPublication(book.getPublication());
		advancedSearch.setTitle(book.getTitle());
		advancedSearch.setYear(book.getYear());
		List<Book> books = new ArrayList<>(bookService.advancedSearch(advancedSearch, 0, 0));
		assertEquals(book, books.get(0));
	}

	@Test
	public void testAdvancedSearchCount() {
		AdvancedSearch advancedSearch = new AdvancedSearch();
		advancedSearch.setAuthors(book.getAuthors());
		advancedSearch.setAvailable(true);
		advancedSearch.setGenre(1);
		advancedSearch.setPublication(book.getPublication());
		advancedSearch.setTitle(book.getTitle());
		advancedSearch.setYear(book.getYear());
		assertEquals(bookService.advancedSearchCount(advancedSearch), 1);
	}

	@Test
	public void testGetBooksByRating() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testGetMostRatedByGenre() {
		//fail("Not yet implemented");
	}

}
