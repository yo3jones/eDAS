package edu.unlv.cs.edas.design.domain;

import org.bson.types.ObjectId;

public interface Run {

	ObjectId getId();
	
	String getStringId();
	
	ObjectId getOwnerId();
	
	String getName();
	
	ObjectId getGraphId();
	
	String getStringGraphId();
	
	ObjectId getAlgorithmId();
	
	String getStringAlgorithmId();
}
