package edu.unlv.cs.edas.design.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.unlv.cs.edas.design.domain.ImmutableDesignGraphDetails;
import edu.unlv.cs.edas.design.domain.MutableDesignGraphDetails;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsManager;
import edu.unlv.cs.edas.user.domain.User;
import edu.unlv.cs.edas.user.domain.UserManager;

@Controller
@RequestMapping("/design/graphDetails")
public class DesignGraphDetailsController {

	@Autowired DesignGraphDetailsManager manager;
	@Autowired UserManager userManager;
	
	@RequestMapping(method=GET)
	public ModelAndView getAll() {
		User user = userManager.getCurrentUser();
		Collection<ImmutableDesignGraphDetails> graphDetails = manager.findAllOwnedBy(user.getId());
		
		ModelAndView view = new ModelAndView("design/allDesignGraphDetails");
		view.addObject("model", new AllDesignGraphDetailsModel()
				.setGraphDetails(graphDetails));
		
		return view;
	}
	
	@RequestMapping(value="/new", method=GET)
	public String getNew() {
		MutableDesignGraphDetails graphDetails = new MutableDesignGraphDetails();
		
		User user = userManager.getCurrentUser();
		graphDetails.setOwner(user.getId());
		
		ObjectId id = manager.save(graphDetails).getId();
		return "redirect:/design/graphDetails/" + id.toHexString();
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public ModelAndView get(@PathVariable String id) {
		manager.get(id);
		
		ModelAndView view = new ModelAndView("design/designGraphDetails");
		
		view.addObject("model", new DesignGraphDetailsModel()
			.setId(id));
		
		return view;
	}
	
}
