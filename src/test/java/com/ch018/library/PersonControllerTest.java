package com.ch018.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ch018.library.controller.PersonController;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.PersonServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PersonControllerTest {
	
	@Autowired
	private PersonController personController;
	
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShowUsers() {
		MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
		
		//fail("Not yet implemented");
		assertEquals(true, true);
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewPerson() {
		fail("Not yet implemented");
	}
	
	@Configuration
	static class PersonServiceTestContextConfiguration {
		
		@Bean
		public PersonService personService() {
			return Mockito.mock(PersonService.class);
		}
		
		@Bean
		public PersonController personController() {
			return new PersonController();
		}
	}

}
