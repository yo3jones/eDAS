package edu.unlv.cs.edas.graph.design;

import edu.unlv.cs.edas.graph.domain.GraphId;

/**
 * An implementation of {@link GraphId} for design graphs.
 * 
 * @author Chris Jones
 *
 */
public class DesignGraphId extends GraphId {

	/**
	 * Constructs a design graph ID with the given long value.
	 * 
	 * @param id
	 *            The value of the ID.
	 */
	public DesignGraphId(Long id) {
		super(id);
	}

}
