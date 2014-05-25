package edu.unlv.cs.edas.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/common")
public class CommonController {

	@RequestMapping("/imports")
	public ModelAndView getImports() {
		return new ModelAndView("common/imports");
	}
	
	@RequestMapping("/header")
	public ModelAndView getHeader() {
		return new ModelAndView("common/header");
	}
	
}
