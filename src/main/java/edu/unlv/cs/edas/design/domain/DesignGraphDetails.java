package edu.unlv.cs.edas.design.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;

public interface DesignGraphDetails extends Serializable {
	
	ObjectId getId();
	
	String getStringId();
	
	String getName();
	
	DesignGraph getGraph();
	
	ObjectId getOwner();
	
}
