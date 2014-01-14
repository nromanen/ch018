package com.ch018.library.form;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.ch018.library.util.IConstants;

public class ResetPassword {
	
	@Autowired 
	private MessageSource messageSource;
	
	private String key;

	@Size(min = IConstants.PASS_MIN, max = IConstants.PASS_MAX, message = "{Size.registration.password}")
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
