package edu.unlv.cs.distsim.graph;

import org.springframework.util.Assert;

/**
 * A mutable object that holds the data about a vertex in a graph for the
 * distributed algorithm simulator.
 * 
 * @author Chris Jones
 * 
 */
public class Vertex {

	/**
	 * The unique identifier of this vertex.
	 */
	private Key id;
	
	/**
	 * Constructs a vertex with the given id.
	 * 
	 * @param id
	 *            The unique identifier of the vertex.
	 * @throws IllegalArgumentException
	 *             If the id is null.
	 */
	public Vertex(Key id) throws IllegalArgumentException {
		Assert.notNull(id);
		this.id = id;
	}
	
	/**
	 * Returns the id of this vertex.
	 * 
	 * @return The id of this vertex.
	 */
	public final Key getId() {
		return id;
	}
	
}
