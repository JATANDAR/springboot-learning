package au.com.acttab.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.acttab.dao.impl.ToDoDAOImpl;
import au.com.acttab.model.ToDoDatumModel;

@Controller
public class WelcomeController {
	
	String chapters[]= new String[]{"Quick Start With Groovy","Quick Start With Java",
            "Debugging and Managing Your App",
            "Data Access with SpringBoot",
            "Securing Your App"};
	
	@Autowired
	private ToDoDAOImpl toDoImpl;
	
	@RequestMapping("/welcome")
    public String loginMessage(Model m){
		m.addAttribute("name", "Jatandar Dhirwani");
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
