package au.com.acttab.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.acttab.dao.impl.ToDoDAOImpl;
import au.com.acttab.model.ToDoDatumModel;

@Controller
public class ToDoController {
	
	@Autowired
	private ToDoDAOImpl toDoImpl;
	
	@RequestMapping("/edit/{recordId}")
	public String edit(@PathVariable Integer recordId, Model m ) {
		System.out.println("recordId to edit=" + recordId);
		m.addAttribute("todo", toDoImpl.getSpecificToDo(recordId));
		return "edit";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveToDo(@RequestBody @ModelAttribute ToDoDatumModel todo) {
		System.out.println("todo to edit="+todo);
		toDoImpl.save(todo);
		return "redirect:/welcome";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteToDo(@PathVariable("id") String id, Model model, Principal principal, Authentication authentication) {
		
		System.out.println("I am in delete principal="+ principal );
		if( principal != null) {
			System.out.println("authentication=" + authentication);
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			
			for(GrantedAuthority a: authentication.getAuthorities()) 
			{
				System.out.println("grantedAuthority=" + a.getAuthority());
				if(a.getAuthority().equalsIgnoreCase("delete")) 
				{
					System.out.println(principal.getName() +  "   can delete id=" + id);
					toDoImpl.delete(Integer.parseInt(id));
					return "redirect:/welcome";
				}
			}
//			if(authentication.getAuthorities().contains("DELETE")) {
//				System.out.println(principal.getName() +  "   can delete");
//				toDoImpl.delete();
//				return "redirect:/welcome";
//			}
		}
		else {
			return "error";
		}
		return "error";
		
	}

}
