package com.josue.logInAndReg.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
	@NotBlank(message = "Email is required!")
	@Email(message = "please enter a valid email!")
	private String email;
	

	@NotEmpty(message = "Password is required!")
	@Size(min = 8, max = 255, message = "paswword must be at least 8 characters ling!")
	private String password;

	public LoginUser() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
