package com.ch018.library.form;

public class Registration {
	private String email;
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
	
	public String getConfirmPassword(){
		return confirmPassword;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setConfirmPassword(String confirmPassword){
		this.confirmPassword = confirmPassword;
	}

}
