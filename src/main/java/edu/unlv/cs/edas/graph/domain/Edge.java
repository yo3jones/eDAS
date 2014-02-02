package edu.unlv.cs.edas.graph.domain;

import org.springframework.util.Assert;

import edu.unlv.cs.graph.EdgeKey;

/**
 * A mutable object that holds information about an edge in a graph for the
 * distributed algorithm simulator.
 * 
 * @author Chris Jones
 * 
 */
public class Edge {

	/**
	 * The id of the edge.
	 */
	private EdgeKey<Key> id;
	
	protected Edge() {
		
	}
	
	/**
	 * Constructs an edge with the given id.
	 * 
	 * @param id
	 *            The id of this edge.
	 * @throws IllegalArgumentException
	 *             If the id is null.
	 */
	public Edge(EdgeKey<Key> id) throws IllegalArgumentException {
		Assert.notNull(id);
		this.id = id;
	}
	
	/**
	 * Returns the id of this edge.
	 * 
	 * @return The id of this edge.
	 */
	public EdgeKey<Key> getId() {
		return id;
	}
	
	void setId(EdgeKey<Key> id) {
		this.id = id;
	}
	
}
