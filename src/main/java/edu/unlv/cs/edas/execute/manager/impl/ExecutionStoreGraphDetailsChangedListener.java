package edu.unlv.cs.edas.execute.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.design.manager.DesignGraphDetailsChangedEvent;
import edu.unlv.cs.edas.execute.manager.ExecutionsStore;

@Component @Scope("prototype")
public class ExecutionStoreGraphDetailsChangedListener implements
		ApplicationListener<DesignGraphDetailsChangedEvent> {

	@Autowired ExecutionsStore executions;
	
	@Override
	public void onApplicationEvent(DesignGraphDetailsChangedEvent event) {
		executions.removeByGraphDetailsId(event.getGraphDetailsId());
	}
	
}
