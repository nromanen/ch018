package com.ch018.test.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.service.LocalizationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-service-context.xml"
})
@WebAppConfiguration
public class BookServiceImplTest {
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private LocalizationService localizationService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddBook() {
		fail("Not yet implemented");
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
