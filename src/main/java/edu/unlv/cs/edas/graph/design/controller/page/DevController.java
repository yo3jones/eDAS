package edu.unlv.cs.edas.graph.design.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/graphDev")
public class DevController {

	@RequestMapping
	public ModelAndView get() {
		return new ModelAndView("graphDev");
	}
	
}
