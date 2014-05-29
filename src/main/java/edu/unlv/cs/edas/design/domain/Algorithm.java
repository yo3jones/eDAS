package edu.unlv.cs.edas.design.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;

public interface Algorithm extends Serializable {

	ObjectId getId();
	
	ObjectId getOwnerId();
	
	String getStringId();
	
	String getName();
	
	String getAlgorithm();
	
	String getStateDisplayPattern();
	
	String getMessageDisplayPattern();
	
	Boolean getBidirectional();
	
}
