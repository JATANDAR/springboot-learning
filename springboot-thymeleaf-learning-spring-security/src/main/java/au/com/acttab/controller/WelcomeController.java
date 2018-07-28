package au.com.acttab.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.acttab.dao.impl.ToDoDAOImpl;
import au.com.acttab.model.ToDoDatumModel;

/**
 * 
 * https://stackoverflow.com/questions/41388332/thymeleaf-3-0-spring-boot-security-integration-does-not-work
 * ***/

@Controller
public class WelcomeController {

	String chapters[]= new String[]{"Quick Start With Groovy","Quick Start With Java",
			"Debugging and Managing Your App",
			"Data Access with SpringBoot",
	"Securing Your App"};

	@Autowired
	private ToDoDAOImpl toDoImpl;

	@RequestMapping("/welcome")
	public String loginMessage(Model m, HttpSession session, Principal principal, Authentication authentication)
	{
		
		if(principal != null) 
		{
			System.out.println("principal=" + principal.getName());
			System.out.println("getAuthorities" + authentication.getAuthorities());
			
		}
		
		//User loggedInUser = (User) session.getAttribute("loggedInUser");
		if(principal != null && principal.getName() != null) {
			m.addAttribute("name", principal.getName());
		}
		else
		{
			m.addAttribute("name", "Guest");
		}
		m.addAttribute("chapters", chapters);

		List<ToDoDatumModel> allToDos = toDoImpl.getAllToDos();

		System.out.println("allToDos=" + allToDos); 

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
		System.out.println("simpleDateFormat.format()=" + simpleDateFormat.format(new Date()));
		m.addAttribute("toDoDate", simpleDateFormat.format(new Date()));
		m.addAttribute("todoList", allToDos);
		return "welcome";
	}
}
