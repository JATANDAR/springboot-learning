package au.com.acttab;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	String chapters[]= new String[]{"Quick Start With Groovy","Quick Start With Java",
            "Debugging and Managing Your App",
            "Data Access with SpringBoot",
            "Securing Your App"};
	
	@RequestMapping("/welcome")
    public String loginMessage(Model m){
		m.addAttribute("name", "Jatandar Dhirwani");
		m.addAttribute("chapters", chapters);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
		System.out.println("simpleDateFormat.format()=" + simpleDateFormat.format(new Date()));
		m.addAttribute("toDoDate", simpleDateFormat.format(new Date()));
        return "welcome";
    }
}
