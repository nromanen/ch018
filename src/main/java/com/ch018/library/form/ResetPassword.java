package com.ch018.library.form;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class ResetPassword {
	
	private static final int MIN = 4;
	private static final int MAX = 20;
	
	@Autowired 
	private MessageSource messageSource;
	
	private String key;

	@Size(min = MIN, max = MAX, message = "{Size.registration.password}")
	private String password;

	private String confirm;
	
	public ResetPassword() {
	}
	
	public String getKey() {
		return key;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getConfirm() {
		return confirm;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
}
