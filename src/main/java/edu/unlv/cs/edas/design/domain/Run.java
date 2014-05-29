package edu.unlv.cs.edas.design.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;

public interface Run extends Serializable {

	ObjectId getId();
	
	String getStringId();
	
	ObjectId getOwnerId();
	
	String getName();
	
	ObjectId getGraphId();
	
	String getStringGraphId();
	
	ObjectId getAlgorithmId();
	
	String getStringAlgorithmId();
}
