package edu.unlv.cs.edas.execute.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.design.manager.RunChangedEvent;
import edu.unlv.cs.edas.execute.manager.ExecutionsStore;

@Component @Scope("prototype")
public class ExecutionStoreRunChangedListener implements ApplicationListener<RunChangedEvent> {

	@Autowired ExecutionsStore executions;
	
	@Override
	public void onApplicationEvent(RunChangedEvent event) {
		executions.remove(event.getRunId());
	}
	
}
