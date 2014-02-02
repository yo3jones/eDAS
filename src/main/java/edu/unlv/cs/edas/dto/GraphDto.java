package edu.unlv.cs.edas.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.util.Assert;

import edu.unlv.cs.edas.graph.Edge;
import edu.unlv.cs.edas.graph.Vertex;

/**
 * An immutable data transfer object that holds graph information for the
 * distributed algorithm simulator. This object is meant to have a very flat
 * structure to help with serialization.
 * 
 * @author Chris Jones
 * 
 * @param <V>
 *            The vertex type.
 * @param <E>
 *            The edge type.
 */
public class GraphDto<V extends Vertex, E extends Edge> {

	/**
	 * A collection of all the vertices in this graph.
	 */
	private Collection<V> vertices;
	
	/**
	 * A collection of all the edges in this graph.
	 */
	private Collection<E> edges;
	
	/**
	 * Constructs a graph data transfer object with the given vertices and
	 * edges.
	 * 
	 * @param vertices
	 *            The vertices of the graph.
	 * @param edges
	 *            The edges of the graph.
	 * @throws IllegalArgumentException
	 *             If either the vertices or edges are null.
	 */
	public GraphDto(Collection<V> vertices, Collection<E> edges) throws IllegalArgumentException {
		Assert.notNull(vertices);
		Assert.notNull(edges);
		this.vertices = new ArrayList<V>(vertices);
		this.edges = new ArrayList<E>(edges);
	}
	
	/**
	 * Returns all the vertices in this graph.
	 * 
	 * @return All the vertices in this graph.
	 */
	public Collection<V> getVertices() {
		return Collections.unmodifiableCollection(vertices);
	}
	
	/**
	 * Returns all the edges in this graph.
	 * 
	 * @return All the edges in this graph.
	 */
	public Collection<E> getEdges() {
		return Collections.unmodifiableCollection(edges);
	}
	
}
