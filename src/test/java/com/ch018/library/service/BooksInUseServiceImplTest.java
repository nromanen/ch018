package com.ch018.library.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ch018.library.controller.BookInUseController;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/fortest/database.xml",
									"file:src/main/webapp/WEB-INF/root-context.xml",
									"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml"
		})
public class BooksInUseServiceImplTest {
	
	Connection connection; 
	Statement stmt;
	PreparedStatement preparedStmt;
	
	private Book book = new Book();
	private Person person = new Person();
	private BooksInUse booksInUse = new BooksInUse();
	private Orders orders = new Orders();
	private Genre genre = new Genre();
	
	@Autowired
	private BooksInUseService booksInUseService;

	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library_test", "root", "root");
		stmt = connection.createStatement();
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
		
		try {
			String saveTestUser = "insert into person(id, cellphone, confirmed, e_mail, "
					+ "activated, failedOrders, multibookAllowed, name, password, rating, "
					+ "role, sms, surname, timely_returns, untimely_returns, verificationkey) values "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(saveTestUser);
			preparedStmt.setInt(1, 1);
			preparedStmt.setString(2, person.getCellphone());
			preparedStmt.setBoolean(3, person.getConfirm());
			preparedStmt.setString(4, person.getEmail());
			preparedStmt.setBoolean(5, person.getEmailConfirmed());
			preparedStmt.setInt(6, person.getFailedOrders());
			preparedStmt.setInt(7, person.getMultibookAllowed());
			preparedStmt.setString(8, person.getName());
			preparedStmt.setString(9, person.getPassword());
			preparedStmt.setDouble(10, person.getRating());
			preparedStmt.setString(11, person.getRole());
			preparedStmt.setBoolean(12, person.getSms());
			preparedStmt.setString(13, person.getSurname());
			preparedStmt.setInt(14, person.getTimelyReturns());
			preparedStmt.setInt(15, person.getUntimelyReturns());
			preparedStmt.setString(16, person.getVerificationKey());
			preparedStmt.executeUpdate();

		} catch (Exception e) {
			System.err.println(e);
		}
		
		book.setId(1);
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

		try {
			stmt.execute("insert into genre (id) VALUES (1)");
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
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
		booksInUse.setBook(book);
		booksInUse.setReturnDate(new Date());
		booksInUse.setBuid(1);
		booksInUse.setIssueDate(new Date());
		booksInUse.setPerson(person);
		
		try {
			String saveTestOrders = "insert into booksinuse(id, return_date, issue_date, book_id, person_id) "
					+ "values (?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(saveTestOrders);
			preparedStmt.setInt(1, 1);
			preparedStmt.setDate(2, new java.sql.Date(booksInUse.getReturnDate().getTime()+1000));
			preparedStmt.setDate(3, new java.sql.Date(booksInUse.getIssueDate().getTime()));
			preparedStmt.setInt(4, booksInUse.getBook().getId());
			preparedStmt.setInt(5, booksInUse.getPerson().getId());
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		preparedStmt.close();
	}

	@After
	public void tearDown() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library_test", "root", "root");
		try {
			stmt = connection.createStatement();
			stmt.execute("delete from booksinuse");
			stmt.execute("delete from person");
			stmt.execute("delete from book");
			stmt.execute("delete from genre");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testAddBooksInUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllBooksInUse() {
		List <BooksInUse> actual = new ArrayList<BooksInUse>(booksInUseService.getAllBooksInUse());
		List <BooksInUse> expected = new ArrayList<BooksInUse>(Arrays.asList(booksInUse));
		assertEquals(expected, actual);
	}

	@Test
	public void testGetByPersonId() {
		List <BooksInUse> actual = new ArrayList<>(booksInUseService.getByPersonId(1));
		assertEquals(booksInUse, actual.get(0));
	}

	@Test
	public void testGetByBookId() {
		List <BooksInUse> actual = new ArrayList<>(booksInUseService.getByBookId(1));
		assertEquals(booksInUse, actual.get(0));
	}

	@Test
	public void testGetByIssueDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByReturnDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBooksInUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMinByReturnDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlreadyInUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountBooksInUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountBooksInUseToday() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReturnBooksToday() {
		List<Book> actual = new ArrayList<> (booksInUseService.getReturnBooksToday(0, 0, "id"));
		List<Book> expected = new ArrayList<>();
		expected.add(book);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetCountBooksByPerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountReturnBooksBeetweenDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBooksInUse() {
		fail("Not yet implemented");
	}

}
