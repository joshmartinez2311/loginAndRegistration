package com.josue.logInAndReg.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.josue.logInAndReg.models.LoginUser;
import com.josue.logInAndReg.models.User;
import com.josue.logInAndReg.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	//returns a list of all users
	public List<User> allUsers(){
		return userRepo.findAll();
	}

	// this method will take data from the reg form, check if valid and register user into databasa
	public User register(User newUser, BindingResult result) {
		Optional<User> registeredUser = userRepo.findByEmail(newUser.getEmail());
		
		//checks if users email is already taken
		if(registeredUser.isPresent()) {
			result.rejectValue("email", "matches", "email taken");
		}
		
		//checks if password matches confirm password
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirm", "matches", "password must match");
		}
		if(result.hasErrors()) {
			return null;
		}
		
		//this method will hash the new users password
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
	}
	
	//this method will login user and validated if login info exist in database
	public User login(LoginUser newLoginUser, BindingResult result) {
		Optional<User> registeredUser = userRepo.findByEmail(newLoginUser.getEmail());
		
		//checks to see if user exists in database
		if(!registeredUser.isPresent()) {
			result.rejectValue("password", "matches", "user not found");
			return null;
		}
		User user = registeredUser.get();
		
		//it will check if the password is valid
		if(!BCrypt.checkpw(newLoginUser.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Password");
		}
		if(result.hasErrors()) {
			return null;
		}
		return user;
	}
	
	//this method will look for a user by id
	public User findById(Long id) {
		Optional<User> registeredUser = userRepo.findById(id);
		if(registeredUser.isPresent()) {
			return registeredUser.get();
		}
		return null;
	}
}
