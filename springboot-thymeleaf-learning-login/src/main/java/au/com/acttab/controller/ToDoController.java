package au.com.acttab.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

}
