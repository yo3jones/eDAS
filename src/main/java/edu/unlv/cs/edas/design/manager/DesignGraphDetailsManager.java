package edu.unlv.cs.edas.design.manager;

import java.util.Collection;

import org.bson.types.ObjectId;

import edu.unlv.cs.edas.design.domain.ImmutableDesignGraphDetails;
import edu.unlv.cs.edas.design.domain.MutableDesignGraphDetails;

/**
 * A {@link GraphManager} for design graphs.
 * 
 * @author Chris Jones
 *
 */
public interface DesignGraphDetailsManager {

	ImmutableDesignGraphDetails get(String id);
	
	ImmutableDesignGraphDetails get(ObjectId id);
	
	ImmutableDesignGraphDetails save(MutableDesignGraphDetails graphDetails);
	
	void delete(String id);
	
	void delete(ObjectId id);
	
	Collection<ImmutableDesignGraphDetails> findAllOwnedBy(ObjectId ownerId);
	
}
