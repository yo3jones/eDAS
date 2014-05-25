package edu.unlv.cs.edas.design.manager;

import java.util.Collection;

import org.bson.types.ObjectId;

import edu.unlv.cs.edas.design.domain.ImmutableAlgorithm;
import edu.unlv.cs.edas.design.domain.MutableAlgorithm;

public interface AlgorithmManager {

	ImmutableAlgorithm get(ObjectId id);
	
	ImmutableAlgorithm get(String id);
	
	ImmutableAlgorithm save(MutableAlgorithm algorithm);
	
	void delete(ObjectId id);
	
	void delete(String id);
	
	Collection<ImmutableAlgorithm> getAllAlgorithmsByOwner(ObjectId ownerId);
	
	Collection<ImmutableAlgorithm> getAllAlgorithmsByOwner(String ownerId);
	
}
