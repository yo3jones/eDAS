package edu.unlv.cs.edas.design.manager;

import java.util.Collection;

import org.bson.types.ObjectId;

import edu.unlv.cs.edas.design.domain.ImmutableRun;
import edu.unlv.cs.edas.design.domain.MutableRun;

public interface RunManager {

	ImmutableRun get(ObjectId id);
	
	ImmutableRun get(String id);
	
	ImmutableRun save(MutableRun run);
	
	void delete(ObjectId id);
	
	void delete(String id);
	
	Collection<ImmutableRun> getAllByOwner(ObjectId ownerId);
	
	Collection<ImmutableRun> getAllByOwner(String ownerId);
	
}
