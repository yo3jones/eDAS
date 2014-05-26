package edu.unlv.cs.edas.design.domain;

import org.bson.types.ObjectId;

public interface DesignGraphDetails {
	
	ObjectId getId();
	
	String getStringId();
	
	String getName();
	
	DesignGraph getGraph();
	
	ObjectId getOwner();
	
}
