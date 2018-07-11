package au.com.acttab.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.acttab.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("newUser", new User());
		return "register";
	}
	
	@RequestMapping(value="/newUserRegister", method = RequestMethod.POST)
	public String newUserRegisteration(@ModelAttribute("newUser") User newUser, Model model ) {
		
		System.out.println("newUser=" + newUser);
		return "redirect:/welcome";
	}

}
