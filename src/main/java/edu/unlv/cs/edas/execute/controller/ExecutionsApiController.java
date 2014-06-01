package edu.unlv.cs.edas.execute.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.unlv.cs.edas.execute.domain.Execution;
import edu.unlv.cs.edas.execute.manager.ExecutionManager;
import edu.unlv.cs.edas.execute.process.ExecutionProcessor;

@RestController
@RequestMapping("/{version}/executions")
public class ExecutionsApiController {

	@Autowired ExecutionManager manager;
	
	@Autowired ExecutionProcessor processor;
	
	@RequestMapping(value="/{runId}/{round}", produces="application/json")
	public RoundModel get(@PathVariable String runId, @PathVariable Integer round) throws 
			NoSuchMethodException, ScriptException {
		Execution execution = manager.get(runId);
		
		processor.processToRound(execution, round);
		
		return new RoundModel()
				.setRound(execution.getRound(round))
				.setLog(execution.getLog());
	}
	
	@ExceptionHandler(ScriptException.class)
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	public String handleScriptException(ScriptException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	public String handleNoSuchMethodError(NoSuchMethodException e) {
		return e.getMessage();
	}
	
}
