package org.test.model.form;

import org.test.jpa.model.Account;

public class AccountForm {

	private String email;
	private String name;
	private String password;
	private String confirmPassword;
	private String phone;
	
	public AccountForm() {
		// TODO Auto-generated constructor stub
	}
	
	public Account newAccount(){
		if(!this.password.equals(this.confirmPassword)){
			return null;
		}else{
			return new Account(this.email, this.password, this.name, this.phone);
		}				
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
