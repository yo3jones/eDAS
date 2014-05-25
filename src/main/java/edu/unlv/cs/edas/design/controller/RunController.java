package edu.unlv.cs.edas.design.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.unlv.cs.edas.design.domain.DesignGraphDetails;
import edu.unlv.cs.edas.design.domain.ImmutableAlgorithm;
import edu.unlv.cs.edas.design.domain.ImmutableRun;
import edu.unlv.cs.edas.design.domain.MutableRun;
import edu.unlv.cs.edas.design.domain.Run;
import edu.unlv.cs.edas.design.manager.AlgorithmManager;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsManager;
import edu.unlv.cs.edas.design.manager.RunManager;
import edu.unlv.cs.edas.user.domain.User;
import edu.unlv.cs.edas.user.domain.UserManager;

@Controller
@RequestMapping("/design/runs")
public class RunController {

	@Autowired RunManager manager;
	
	@Autowired UserManager userManager;
	
	@Autowired DesignGraphDetailsManager graphManager;
	
	@Autowired AlgorithmManager algorithmManager;
	
	@RequestMapping(method=GET)
	public ModelAndView getRuns() {
		User user = userManager.getCurrentUser();
		Collection<RunModel> models = new ArrayList<RunModel>();
		for (ImmutableRun run : manager.getAllByOwner(user.getId())) {
			RunModel model = new RunModel();
			model.setRun(run);
			if (run.getGraphId() != null) {
				model.setGraph(graphManager.get(run.getGraphId()));
			}
			if (run.getAlgorithmId() != null) {
				model.setAlgorithm(algorithmManager.get(run.getAlgorithmId()));
			}
			models.add(model);
		}
		return new ModelAndView("design/runs")
			.addObject("models", models);
	}
	
	@RequestMapping(method=POST)
	public String postRun() {
		User user = userManager.getCurrentUser();
		
		MutableRun run = new MutableRun();
		run.setOwnerId(user.getId());
		
		ImmutableRun savedRun = manager.save(run);
		
		return "redirect:/design/runs/" + savedRun.getStringId();
	}
	
	@RequestMapping(method=DELETE)
	public ModelAndView deleteRun(@RequestParam String id) {
		manager.delete(id);
		return getRuns();
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public ModelAndView getRun(@PathVariable String id) {
		return getRunView(id);
	}
	
	@RequestMapping(value="/{id}", method=PUT)
	public ModelAndView putRun(@PathVariable String id,
			@RequestParam String name,
			@RequestParam String graphId,
			@RequestParam String algorithmId) {
		MutableRun run = new MutableRun(manager.get(id));
		run.setName(name);
		run.setGraphId(StringUtils.isBlank(graphId) ? null : new ObjectId(graphId));
		run.setAlgorithmId(StringUtils.isBlank(algorithmId) ? null : new ObjectId(algorithmId));
		ImmutableRun savedRun = manager.save(run);
		return getRunView(savedRun);
	}
	
	private ModelAndView getRunView(String id) {
		return getRunView(manager.get(id));
	}
	
	private ModelAndView getRunView(Run run) {
		User user = userManager.getCurrentUser();
		Collection<DesignGraphDetails> graphs = graphManager.findAllOwnedBy(user.getId());
		Collection<ImmutableAlgorithm> algorithms = algorithmManager.getAllAlgorithmsByOwner(
				user.getId());
		
		RunModel model = new RunModel();
		model.setRun(run);
		if (run.getGraphId() != null) {
			model.setGraph(graphManager.get(run.getGraphId()));
		}
		model.setGraphs(graphs);
		if (run.getAlgorithmId() != null) {
			model.setAlgorithm(algorithmManager.get(run.getAlgorithmId()));
		}
		model.setAlgorithms(algorithms);
		return new ModelAndView("design/run")
			.addObject("model", model);
	}
	
}
