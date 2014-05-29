package edu.unlv.cs.edas.execute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unlv.cs.edas.execute.domain.Execution;
import edu.unlv.cs.edas.execute.domain.Round;
import edu.unlv.cs.edas.execute.manager.ExecutionManager;
import edu.unlv.cs.edas.execute.process.ExecutionProcessor;

@RestController
@RequestMapping("/{version}/executions")
public class ExecutionsApiController {

	@Autowired ExecutionManager manager;
	
	@Autowired ExecutionProcessor processor;
	
	@RequestMapping(value="/{runId}/{round}", produces="application/json")
	public Round get(@PathVariable String runId, @PathVariable Integer round) {
		Execution execution = manager.get(runId);
		
		processor.processToRound(execution, round);
		
		return execution.getRound(round);
	}
	
}
