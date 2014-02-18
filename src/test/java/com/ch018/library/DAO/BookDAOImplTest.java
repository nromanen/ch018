package com.ch018.library.DAO;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ch018.library.entity.Book;

@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/fortest/database.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml",
		"file:src/main/webapp/WEB-INF/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/root-context.xml",
		"file:src/main/webapp/WEB-INF/application-security.xml",
		"file:src/main/webapp/WEB-INF/tiles.xml"
})
public class BookDAOImplTest {
	
	Book book = new Book();
	
	@Autowired
	private BookDAO bookDAO;

	@Before
	public void setUp() throws Exception {
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddBook() {
		bookDAO.addBook(book);
	}

	@Test
	public void testUpdateBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllBooks() {
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
	public void testDeleteBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testSimpleSearchString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSimpleSearchStringIntIntStringBoolean() {
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
	public void testSimpleSearchCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByRating() {
		fail("Not yet implemented");
	}

}
