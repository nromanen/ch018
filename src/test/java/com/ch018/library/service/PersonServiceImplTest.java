package com.ch018.library.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.entity.Person;
import com.ch018.library.form.Registration;
import com.ch018.library.form.ResetPassword;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/fortest/database.xml",
									"file:src/main/webapp/WEB-INF/root-context.xml",
									"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml"
		})
public class PersonServiceImplTest {
	
	Connection connection; 
	Statement stmt;
	PreparedStatement preparedStmt;
	
	private Book book = new Book();
	private Person person = new Person();
	private BooksInUse booksInUse = new BooksInUse();
	private Localization localization = new Localization();
	private Genre genre = new Genre();

	@Autowired
	private PersonService personService;
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library_test", "root", "root");
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
			stmt = connection.createStatement();
			stmt.execute("insert into genre (id) VALUES (1)");
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
			stmt.execute("delete from person");
			stmt.execute("delete from genre");
			//stmt.execute("delete from booksinuse");
			//stmt.execute("delete from person");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testSave() {
		String query = "SELECT COUNT(*) FROM person";
        ResultSet rs;
        int i = 0;
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()){
				i = rs.getInt("COUNT(*)"); 
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		person.setEmail("new@test.com");
        personService.save(person);
        i++;
        assertEquals(2, i);
	}

	
	
	@Test
	public void testRegistrate() {
		String query = "SELECT COUNT(*) FROM person";
        ResultSet rs;
        int i = 0;
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()){
				i = rs.getInt("COUNT(*)"); 
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Registration registration = new Registration();
		registration.setEmail("correct@p1er1son.com");
		registration.setPassword("111111");
		registration.setConfirmPassword("111111");
		String message = "Thank you for joining our JLibrary. Please confirm your email by clicking next link: ";
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		when(request.getRequestURL()).thenReturn(new StringBuffer("http://test.te/te"));
		when(request.getServletPath()).thenReturn("/te");
		personService.registrate(registration, message, request);
		i++;
        assertEquals(2, i);
	}

	@Test
	public void testRemindPasswoed() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestorePassword() {
		String query = "select id, cellphone, confirmed, e_mail, "
				+ "activated, failedOrders, multibookAllowed, name, password, rating, "
				+ "role, sms, surname, timely_returns, untimely_returns, verificationkey from person where id=1";
		
        ResultSet rs;
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setPassword("222222");
        resetPassword.setConfirm("222222");
        resetPassword.setKey("123456");
        personService.restorePassword(person, resetPassword);
        Person actual = new Person();
        //actual = person;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				actual.setPassword(rs.getString("password"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(person.getPassword(), actual.getPassword());
	}

	@Test
	public void testDelete() {
		personService.delete(1);
		String query = "SELECT COUNT(*) FROM person";
        ResultSet rs;
        int i = 0;
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()){
				i = rs.getInt("COUNT(*)"); 
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(0, i);
	}

	@Test
	public void testUpdate() {
		person.setName("Updated");
		personService.update(person);
		String query = "select name from person where id=1";
		
        ResultSet rs;
        String actual = new String();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				actual = rs.getString("name");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(person.getName(), actual);
	}

	@Test
	public void testGetAll() {
		Person person2 = new Person();
		person2.setId(2);
		person2.setCellphone("2222222222");
		person2.setConfirm(true);
		person2.setEmail("test2@test2.tst");
		person2.setEmailConfirmed(true);
		person2.setFailedOrders(0);
		person2.setMultibookAllowed(10);
		person2.setName("User2");
		person2.setPassword("$2a$10$Gp.aP6XfF5mZRyzrL5FNTuiw2wyoTOjZFsrLPl9Tg8/qaICgK7qRK");
		person2.setRating(1);
		person2.setRole("ROLE_USER");
		person2.setSms(false);
		person2.setSurname("Surname2");
		person2.setTimelyReturns(2);
		person2.setUntimelyReturns(0);
		person2.setVerificationKey(null);
		
		try {
			stmt = connection.createStatement();
			String saveTestUser = "insert into person(id, cellphone, confirmed, e_mail, "
					+ "activated, failedOrders, multibookAllowed, name, password, rating, "
					+ "role, sms, surname, timely_returns, untimely_returns, verificationkey) values "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(saveTestUser);
			preparedStmt.setInt(1, 2);
			preparedStmt.setString(2, person2.getCellphone());
			preparedStmt.setBoolean(3, person2.getConfirm());
			preparedStmt.setString(4, person2.getEmail());
			preparedStmt.setBoolean(5, person2.getEmailConfirmed());
			preparedStmt.setInt(6, person2.getFailedOrders());
			preparedStmt.setInt(7, person2.getMultibookAllowed());
			preparedStmt.setString(8, person2.getName());
			preparedStmt.setString(9, person2.getPassword());
			preparedStmt.setDouble(10, person2.getRating());
			preparedStmt.setString(11, person2.getRole());
			preparedStmt.setBoolean(12, person2.getSms());
			preparedStmt.setString(13, person2.getSurname());
			preparedStmt.setInt(14, person2.getTimelyReturns());
			preparedStmt.setInt(15, person2.getUntimelyReturns());
			preparedStmt.setString(16, person2.getVerificationKey());
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		List<Person> expected = new ArrayList<>();
		expected.add(person);
		expected.add(person2);
		List<Person> actual = new ArrayList<>();
		actual.addAll(personService.getAll(0, 0, "id", true));
		assertEquals(expected, actual);
	}

	@Test
	public void testGetById() {
		String query = "select name from person where id=1";
        ResultSet rs;
        String expected = new String();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				expected = rs.getString("name");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		person.setName(expected);
		Person actual = new Person();
		actual = personService.getById(1);
		assertEquals(person, actual);
	}

	@Test
	public void testGetByIdWithBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByIdWithOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByEmail() {
		String query = "select name from person where e_mail=" + person.getEmail();
        ResultSet rs;
        String expected = new String();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				expected = rs.getString("name");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		person.setName(expected);
		Person actual = new Person();
		actual = personService.getByEmail(person.getEmail());
		assertEquals(person, actual);
	}

	@Test
	public void testGetByKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSmsEnabled() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAccProperties() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsExist() {
		fail("Not yet implemented");
	}

	@Test
	public void testLibrarianUpdatePerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testLibrarianSavePerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdminUpdatePerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdminSavePerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePassword() {
		fail("Not yet implemented");
	}

}
