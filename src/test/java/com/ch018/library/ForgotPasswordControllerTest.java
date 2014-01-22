package com.ch018.library;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.ch018.library.form.ResetPassword;
import com.ch018.library.service.PersonService;
import com.ch018.library.validator.ResetPwdValidation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/fortest/test-context.xml"
})
@WebAppConfiguration
public class ForgotPasswordControllerTest {
	
	Person person = new Person();
	String email = "example@mail.exm";
	String wrongMail = "wrong@mail.exm";

	private MockMvc mockMvc;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ResetPwdValidation resetPwdValidation;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() throws Exception {
		Mockito.reset(personService);
		Mockito.reset(resetPwdValidation);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		person.setId(1);
		person.setEmail("example@mail.exm");
		person.setCellphone("0000000000");
		person.setPassword("0000");
		
		when(personService.getByEmail(email)).thenReturn(person);
		when(personService.getByEmail(wrongMail)).thenReturn(null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemindForm() throws Exception {
		mockMvc.perform(get("/remind"))
				.andExpect(status().isOk())
				.andExpect(view().name("remind"))
				.andExpect(model().attribute("message", ""));
	}
	
	@Test
	public void testRemindFormWrongMail() throws Exception {
		mockMvc.perform(get("/remind")
				.param("error", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("remind"))
				.andExpect(model().attribute("message", "wrong email!"));
	}

	@Test
	public void testPasswordRestore() throws Exception {
		
		mockMvc.perform(post("/remind")
				.param("email", email))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/"));
		
		verify(personService, times(1)).getByEmail(email);
		//verifyNoMoreInteractions(personService);
	}
	
	@Test
	public void testPasswordRestoreWrongEmail() throws Exception {
		
		mockMvc.perform(post("/remind")
				.param("email", wrongMail))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/remind?error=mail"));
		verify(personService, times(1)).getByEmail(wrongMail);
		verifyNoMoreInteractions(personService);
	}

	@Test
	public void testResetPassForm() throws Exception {
		String key = "abc";
		ResetPassword resetPassword = new ResetPassword();
		resetPassword.setKey(key);
		mockMvc.perform(get("/remind/pass")
				.param("key", key))
				.andExpect(status().isOk())
				.andExpect(model().attribute("resetPassword", resetPassword))
				.andExpect(view().name("restore"));
	}

	@Test
	public void testPasswordRestoreResetPassword() {
		
		//fail("Not yet implemented");
	}

}
