package au.com.acttab.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.acttab.model.User;
import au.com.acttab.service.UserService;

/**
 * 
 * https://stackoverflow.com/questions/20632134/accessing-session-attributes-in-thymeleaf-templates/22120387
 * https://github.com/thymeleaf/thymeleafexamples-layouts/blob/master/src/main/webapp/WEB-INF/views/fragments/header.html
 * https://www.thymeleaf.org/doc/articles/layouts.html
 * http://www.baeldung.com/spring-thymeleaf-3-expressions
 * https://github.com/jbake-org/jbake-example-project-thymeleaf/tree/master/templates
 * https://www.thymeleaf.org/doc/articles/springmvcaccessdata.html
 * https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#expression-basic-objects
 * **/




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
	
	@RequestMapping(value = "/logout", method= RequestMethod.POST)
	public String logout(Model model, HttpSession session) {
		
		Object attribute = session.getAttribute("loggedInUser");
		if(attribute != null) {
			session.removeAttribute("loggedInUser");
		}
		return "redirect:/welcome";
	}

}
