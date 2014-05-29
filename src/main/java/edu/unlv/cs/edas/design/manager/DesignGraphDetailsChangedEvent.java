package edu.unlv.cs.edas.design.manager;

import org.bson.types.ObjectId;
import org.springframework.context.ApplicationEvent;

public class DesignGraphDetailsChangedEvent extends ApplicationEvent {

	private static final long serialVersionUID = -515767403217429443L;
	
	private ObjectId graphDetailsId;
	
	public DesignGraphDetailsChangedEvent(Object source, ObjectId graphDetailsId) {
		super(source);
		this.graphDetailsId = graphDetailsId;
	}

	public ObjectId getGraphDetailsId() {
		return graphDetailsId;
	}
	
}
