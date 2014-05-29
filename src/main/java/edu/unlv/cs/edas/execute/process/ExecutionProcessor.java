package edu.unlv.cs.edas.execute.process;

import edu.unlv.cs.edas.execute.domain.Execution;

public interface ExecutionProcessor {

	void processToRound(Execution execution, Integer round);
	
}
