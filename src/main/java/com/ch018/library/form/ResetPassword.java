package com.ch018.library.form;

public class ResetPassword {
	
	private String key;
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
