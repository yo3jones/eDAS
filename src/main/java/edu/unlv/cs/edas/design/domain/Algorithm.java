package edu.unlv.cs.edas.design.domain;

import org.bson.types.ObjectId;

public interface Algorithm {

	ObjectId getId();
	
	ObjectId getOwnerId();
	
	String getStringId();
	
	String getName();
	
	String getAlgorithm();
	
}
