package au.com.acttab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.acttab.event.RegisterationCompleteEvent;
import au.com.acttab.model.User;
import au.com.acttab.model.VerificationToken;
import au.com.acttab.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	//@Autowired
	private ApplicationEventPublisher  emailPublisher;

	@Autowired
	private UserService userService;
	
	@Autowired
	public void setEmailPublisher(ApplicationEventPublisher  emailPublisher) {
		this.emailPublisher = emailPublisher;
	}


	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("newUser", new User());
		return "user/register";
	}

	@RequestMapping(value="/newUserRegister", method = RequestMethod.POST)
	public String newUserRegisteration(@ModelAttribute("newUser") User newUser, Model model, HttpSession session, HttpServletRequest httpRequest ) {
		System.out.println("newRegisteration=" + newUser);
		User saveUser = userService.saveUser(newUser);

		//session.setAttribute("loggedInUser", saveUser);
		try 
		{
			System.out.println("email Publisher=" + emailPublisher);
			String appUrl = "http://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort() + httpRequest.getContextPath();
			System.out.println("appUrl=" + appUrl);
			emailPublisher.publishEvent(new RegisterationCompleteEvent(saveUser, appUrl));
		} 
		catch (Throwable me) {
			me.printStackTrace();
			//return new ModelAndView("emailError", "user", accountDto);
		}
		model.addAttribute("name", newUser.getName());
		return "user/accountVerificationEmail";
	}
	
	@RequestMapping(value="/registerationConfirm", method = RequestMethod.GET)
	public String confirmRegisteraion(@RequestParam(value="token")String token, Model m, HttpSession session) 
	{
		System.out.println("Confirm Registeration token=" + token);
		
		VerificationToken verifiedToken = userService.verifyToken(token);
		if(verifiedToken != null) 
		{
			userService.deleteToken(verifiedToken.getTokenUUID());
			User userByTokenVerified = userService.getUserByTokenVerified(verifiedToken.getEmailAddress());
			session.setAttribute("loggedInUser", userByTokenVerified);
			//return "redirect:/welcome";
		}
		return "redirect:/welcome";
	}

}
