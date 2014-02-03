package com.ch018.library;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import com.ch018.library.entity.Person;
import com.ch018.library.form.Registration;
import com.ch018.library.service.PersonService;
import com.ch018.library.validator.RegistrationValidation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class SecurityControllerTest {
	
	private Person person = new Person();
	private Registration registration = new Registration();
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private RegistrationValidation registrationValidation;

	@Before
	public void setUp() throws Exception {
		person.setName("Name");
		person.setSurname("Surname");
		person.setId(1);
		person.setEmail("test@test.com");
		person.setCellphone("0000000000");
		person.setPassword("0000");
		person.setConfirm(false);
		person.setEmailConfirmed(false);
		person.setFailedOrders(0);
		person.setMultibookAllowed(10);
		person.setRating(1);
		person.setRole("ROLE_USER");
		person.setVerificationKey("some_random_key");
		
		registration.setEmail(person.getEmail());
		registration.setPassword("0000");
		registration.setPassword("0000");
		
		Mockito.reset(personService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		when(personService.getByKey(person.getVerificationKey())).thenReturn(person);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginForm() {
		
	}

	@Test
	public void testRegForm() throws Exception {
		mockMvc.perform(get("/registration"))
		.andExpect(status().isOk())
		.andExpect(view().name("registration"));
	}

	@Test
	public void testProcessRegistration() {
		//fail("Not yet implemented");
	}

	@Test
	public void testConfirmEmail() throws Exception {
		mockMvc.perform(get("/registration/confirm")
				.param("key", person.getVerificationKey()))
				.andExpect(status().isFound());
		assertTrue(person.getEmailConfirmed());
		verify(personService, times(1)).getByKey(person.getVerificationKey());
	}

}
