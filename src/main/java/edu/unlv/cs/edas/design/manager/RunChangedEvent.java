package edu.unlv.cs.edas.design.manager;

import org.bson.types.ObjectId;
import org.springframework.context.ApplicationEvent;

public class RunChangedEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3760673672025559975L;
	
	private ObjectId runId;
	
	public RunChangedEvent(Object source, ObjectId runId) {
		super(source);
		this.runId = runId;
	}

	public ObjectId getRunId() {
		return runId;
	}
	
}
