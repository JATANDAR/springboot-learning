package au.com.acttab.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.acttab.model.User;
import au.com.acttab.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("newUser", new User());
		return "user/register";
	}
	
	@RequestMapping(value="/newUserRegister", method = RequestMethod.POST)
	public String newUserRegisteration(@ModelAttribute("newUser") User newUser, Model model, HttpSession session ) {
		System.out.println("newRegisteration=" + newUser);
		User saveUser = userService.saveUser(newUser);
		
		session.setAttribute("loggedInUser", saveUser);
		
		
		return "redirect:/welcome";
	}

}
