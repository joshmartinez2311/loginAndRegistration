package com.josue.logInAndReg.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity 
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@NotBlank(message = "Username can not be blank!")
	@Size(min = 3, max = 30, message = "Username must be at least 3 characters long!")
	private String userName;

	@NotBlank(message = "Email is required!")
	@Email(message = "please enter a valid email!")
	private String email;
	
	@NotEmpty(message = "Password is required!")
	@Size(min = 8, max = 255, message = "paswword must be at least 8 characters ling!")
	private String password;
	
	@Transient
	@NotEmpty(message = "confirm password is required!")
	@Size(min = 8, max = 255, message = "paswword must be at least 8 characters ling!")
	private String confirmPassword;

	//constructors
	public User() {
		
	}
	
	public User(Long id,
		 String userName,
		 String email,
		 String password,
		 String confirmPassword) {
	
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	// getters and setters
	

}
