package edu.unlv.cs.edas.design.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.unlv.cs.edas.design.domain.DesignGraphDetails;
import edu.unlv.cs.edas.design.domain.DesignHashGraph;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsManager;

@Controller
@RequestMapping("/design/graphDetails")
public class DesignGraphDetailsController {

	@Autowired DesignGraphDetailsManager manager;
	
	@RequestMapping(value="/new", method=GET)
	public ModelAndView getNew() {
		DesignGraphDetails graphDetails = new DesignGraphDetails();
		graphDetails.setGraph(new DesignHashGraph());
		
		ObjectId id = manager.save(graphDetails);
		
		ModelAndView view = new ModelAndView("designGraphDetails");
		
		view.addObject("model", new DesignGraphDetailsModel()
			.setId(id.toHexString()));
		
		return view;
	}
	
}
