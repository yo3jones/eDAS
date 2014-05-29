package edu.unlv.cs.edas.design.manager;

import org.bson.types.ObjectId;
import org.springframework.context.ApplicationEvent;

public class AlgorithmChangedEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8134207018148377210L;
	
	private ObjectId algorithmId;
	
	public AlgorithmChangedEvent(Object source, ObjectId algorithmId) {
		super(source);
		this.algorithmId = algorithmId;
	}

	public ObjectId getAlgorithmId() {
		return algorithmId;
	}
	
}
