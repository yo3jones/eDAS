package edu.unlv.cs.edas.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.unlv.cs.edas.user.domain.UserManager;

@Controller
public class EdasController {
	
	@Autowired UserManager userManager;
	
	@RequestMapping(value="/", method=GET)
	public ModelAndView get() {
		ModelAndView view = new ModelAndView("edas");
		
		view.addObject("model", new EdasModel()
			.setUserName(userManager.getCurrentUser().getName()));
		
		return view;
	}
	
}
