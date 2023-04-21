package com.josue.logInAndReg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.josue.logInAndReg.models.LoginUser;
import com.josue.logInAndReg.models.User;
import com.josue.logInAndReg.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	// log and reg index page, using model model to set user and loginUser objects
	@GetMapping("/")
	public String loginAndReg(Model model) {
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "loginAndRegForm.jsp";
	}

	//post request to bind registered user data from form:form
	@PostMapping("/register")
	public String register(
			@Valid
			@ModelAttribute("newUser") User newUser,
			BindingResult result,
			Model model,
			HttpSession session) {
		
		userService.register(newUser, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "loginAndRegForm.jsp";
		}
		
		session.setAttribute("userId", newUser.getId());
		
		return "redirect:/dashboard";
	}
	
	// post request that checks for error and redirects back to log and reg or sets userId to seesion
	// if no errors are found
	@PostMapping("/login")
	public String login(
			@Valid
			@ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result,
			Model model,
			HttpSession session) {
		User user = userService.login(newLogin, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "loginAndRegForm.jsp";
		}
		 session.setAttribute("userId", user.getId());
		 return "redirect:/dashboard";
	}
	
	//get request to direct login user/registerUser to the dashboard page
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		
		//gets userId FROM SESSIONS
		Long userId = (Long) session.getAttribute("userId");
		
		//if user id not present then redirect to login and reg page
		if(userId==null) {
			return "redirect:/";
		}
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		return "dashBoard.jsp";
	}
	
	//get request to log out user and clear sessions
	@GetMapping("/logOut")
	public String logOut(HttpSession session) {
		session.setAttribute("userId", null);
		return "redirect:/";
	}
}
