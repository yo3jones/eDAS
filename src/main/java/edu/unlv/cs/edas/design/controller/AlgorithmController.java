package edu.unlv.cs.edas.design.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.unlv.cs.edas.design.domain.Algorithm;
import edu.unlv.cs.edas.design.domain.ImmutableAlgorithm;
import edu.unlv.cs.edas.design.domain.MutableAlgorithm;
import edu.unlv.cs.edas.design.manager.AlgorithmManager;
import edu.unlv.cs.edas.user.domain.User;
import edu.unlv.cs.edas.user.domain.UserManager;

@Controller
@RequestMapping("/design/algorithms")
public class AlgorithmController {

	@Autowired AlgorithmManager manager;
	
	@Autowired UserManager userManager;
	
	@RequestMapping(method=GET)
	public ModelAndView getAlgorithms() {
		User user = userManager.getCurrentUser();
		
		ModelAndView view = new ModelAndView("design/algorithms");
		
		view.addObject("models", manager.getAllAlgorithmsByOwner(user.getId()));
		
		return view;
	}
	
	@RequestMapping(method=POST)
	public String postAlgorithm() {
		User user = userManager.getCurrentUser();
		MutableAlgorithm newAlgorithm = new MutableAlgorithm();
		newAlgorithm.setOwnerId(user.getId());
		ImmutableAlgorithm algorithm = manager.save(newAlgorithm);
		return "redirect:/design/algorithms/" + algorithm.getStringId();
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public ModelAndView getAlgorithm(@PathVariable String id) {
		return getAlgorithmView(id);
	}
	
	@RequestMapping(value="/{id}", method=PUT)
	public ModelAndView putAlgorithm(@PathVariable String id, 
			@RequestParam String name,
			@RequestParam String algorithm,
			@RequestParam String stateDisplayPattern,
			@RequestParam String messageDisplayPattern,
			@RequestParam(required=false, defaultValue="false") Boolean bidirectional) {
		MutableAlgorithm mutableAlgorithm = new MutableAlgorithm(manager.get(id));
		mutableAlgorithm.setName(name);
		mutableAlgorithm.setAlgorithm(algorithm);
		mutableAlgorithm.setStateDisplayPattern(stateDisplayPattern);
		mutableAlgorithm.setMessageDisplayPattern(messageDisplayPattern);
		mutableAlgorithm.setBidirectional(bidirectional);
		
		return getAlgorithmView(manager.save(mutableAlgorithm));
	}
	
	@RequestMapping(value="/{id}", method=DELETE)
	public ModelAndView deleteAlgorithm(@PathVariable String id) {
		manager.delete(id);
		return getAlgorithms();
	}
	
	@RequestMapping(method=DELETE)
	public ModelAndView deleteAlgorithmParam(@RequestParam String id) {
		return deleteAlgorithm(id);
	}
	
	private ModelAndView getAlgorithmView(String id) {
		return getAlgorithmView(manager.get(id));
	}
	
	private ModelAndView getAlgorithmView(Algorithm algorithm) {
		ModelAndView view = new ModelAndView("design/algorithm");
		view.addObject("model", algorithm);
		return view;
	}
	
}
