package edu.unlv.cs.edas.execute.manager;

import org.bson.types.ObjectId;

import edu.unlv.cs.edas.execute.domain.Execution;

public interface ExecutionsStore {

	Execution get(ObjectId id);
	
	void put(ObjectId id, Execution execution);
	
	boolean contains(ObjectId id);
	
	void remove(ObjectId id);
	
	void removeByAlgorithmId(ObjectId algorithmId);
	
	void removeByGraphDetailsId(ObjectId graphDetailsId);
	
}
