package com.ch018.library.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/fortest/database.xml",
									"file:src/main/webapp/WEB-INF/root-context.xml",
									"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml"
		})
public class OrdersServiceImplTest {
	
	Connection connection; 
	Statement stmt;
	PreparedStatement preparedStmt;
	
	private Book book = new Book();
	private Person person = new Person();
	private BooksInUse booksInUse = new BooksInUse();
	private Orders orders = new Orders();
	private Genre genre = new Genre();
	
	@Autowired
	private OrdersService ordersService;

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
			
			//stmt.execute("insert into genre (id) VALUES (1)");
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
			System.out.println(e);
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
		booksInUse.setBook(book);
		booksInUse.setBuid(1);
		booksInUse.setPerson(person);
	
		try {
			//stmt = connection.createStatement();
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
		} catch (Exception e) {
			System.err.println(e);
		}
		
		Orders orders = new Orders();
		orders.setBook(book);
		orders.setDate(new Date());
		orders.setId(1);
		orders.setIssueDate(new Date());
		orders.setPreOrder(false);
		orders.setTerm(14);
		orders.setBook(book);
		orders.setPerson(person);
		
		try {
			String saveTestBook = "insert into orders(id, order_date, issue_date, book_id, person_id, term, preOrdered) "
					+ "values (?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(saveTestBook);
			preparedStmt.setInt(1, 1);
			preparedStmt.setDate(2, new java.sql.Date(orders.getDate().getTime()));
			preparedStmt.setDate(3, new java.sql.Date(orders.getIssueDate().getTime()));
			preparedStmt.setInt(4, orders.getBook().getId());
			preparedStmt.setInt(5, orders.getPerson().getId());
			preparedStmt.setInt(6, orders.getTerm());
			preparedStmt.setBoolean(7, orders.getPreOrder());
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
			stmt.execute("delete from orders");
			stmt.execute("delete from person");
			stmt.execute("delete from book");
			stmt.execute("delete from genre");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testAddOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteOrderOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrdersByBooksId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrdersByPersonId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testToIssueToday() {
		fail("Not yet implemented");
	}

	@Test
	public void testFailedOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testToIssuePerHour() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteOrderInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrderExist() {
		fail("Not yet implemented");
	}

	@Test
	public void testMinOrderDateOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountOrdersByPerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountOrdersToday() {
		fail("Not yet implemented");
	}

	@Test
	public void testToIssueTodayIntIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountOrdersPerHour() {
		fail("Not yet implemented");
	}

	@Test
	public void testToIssuePerHourIntIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountOrdersBookBeetweenDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateissueDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrepareOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllOrdersAfter() {
		fail("Not yet implemented");
	}

}
