package au.com.acttab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Jatandar on 17/06/2018.
 */
@Controller
public class ViewBasedApp {

    String chapters[]= new String[]{"Quick Start With Groovy","Quick Start With Java",
            "Debugging and Managing Your App",
            "Data Access with SpringBoot",
            "Securing Your App"};

    @RequestMapping("/")
    public String home(@RequestParam(value="name", defaultValue="Jatandar Dhirwani")String n, Model m){
            m.addAttribute("name", n);
            m.addAttribute("chapters", chapters);
            return "home";
    }
}
