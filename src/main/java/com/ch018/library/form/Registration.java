package com.ch018.library.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;


public class Registration {
	@Autowired 
	private MessageSource messageSource;
	
	@NotEmpty(message = "{NotEmpty.registration.email}")
	@Email(message = "{Email.registration.email}")
	private String email;
	
	@Size(min = 4, max = 20, message="{Size.registration.password}")
	private String password;
	
	private String confirmPassword;
	
	public Registration() {
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
