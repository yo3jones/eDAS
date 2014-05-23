package edu.unlv.cs.edas.design.manager;

import java.util.Collection;

import org.bson.types.ObjectId;

import edu.unlv.cs.edas.design.domain.DesignGraphDetails;

/**
 * A {@link GraphManager} for design graphs.
 * 
 * @author Chris Jones
 *
 */
public interface DesignGraphDetailsManager {

	DesignGraphDetails get(String id);
	
	DesignGraphDetails get(ObjectId id);
	
	ObjectId save(DesignGraphDetails graphDetails);
	
	Collection<DesignGraphDetails> findAllOwnedBy(ObjectId ownerId);
	
}
