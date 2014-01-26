package com.ch018.library;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class SecurityControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginForm() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRegForm() {
		//fail("Not yet implemented");
	}

	@Test
	public void testProcessRegistration() {
		//fail("Not yet implemented");
	}

	@Test
	public void testConfirmEmail() {
		//fail("Not yet implemented");
	}

}
