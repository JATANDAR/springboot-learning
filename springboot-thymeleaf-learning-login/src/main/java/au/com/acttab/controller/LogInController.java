package au.com.acttab.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.acttab.model.User;
import au.com.acttab.service.UserService;


@Controller
public class LogInController 
{
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/login", method= RequestMethod.GET)
	public String showLoginForm(Model m) {
		m.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value = "/login", method= RequestMethod.POST)
	public String verifyLogin(User user, Model model, HttpSession session) {
		System.out.println("verifyLogin=" + user);
		User loginUser = userService.loginUser(user);
		
		if(loginUser != null) {
			session.setAttribute("loggedInUser", loginUser);
			//model.addAttribute("user", loginUser);
			return "redirect:/welcome";
		}
		model.addAttribute("loginError", "Email or password not found");
		return "login";
	}

}
