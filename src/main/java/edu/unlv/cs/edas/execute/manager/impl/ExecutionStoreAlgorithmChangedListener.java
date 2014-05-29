package edu.unlv.cs.edas.execute.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.design.manager.AlgorithmChangedEvent;
import edu.unlv.cs.edas.execute.manager.ExecutionsStore;

@Component @Scope("prototype")
public class ExecutionStoreAlgorithmChangedListener implements 
		ApplicationListener<AlgorithmChangedEvent> {
	
	@Autowired ExecutionsStore executions;
	
	public void onApplicationEvent(AlgorithmChangedEvent event) {
		executions.removeByAlgorithmId(event.getAlgorithmId());
	}
	
}
