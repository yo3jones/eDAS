package edu.unlv.cs.edas.execute.process;

import javax.script.ScriptException;

import edu.unlv.cs.edas.execute.domain.Execution;

public interface ExecutionProcessor {

	void processToRound(Execution execution, Integer round) throws ScriptException, 
			NoSuchMethodException;
	
}
