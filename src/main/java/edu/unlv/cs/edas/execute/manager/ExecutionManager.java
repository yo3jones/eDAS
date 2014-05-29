package edu.unlv.cs.edas.execute.manager;

import org.bson.types.ObjectId;

import edu.unlv.cs.edas.execute.domain.Execution;

public interface ExecutionManager {
	
	Execution get(ObjectId id);
	
	Execution get(String id);
	
	void clear(ObjectId id);
	
	void clear(String id);
	
}
