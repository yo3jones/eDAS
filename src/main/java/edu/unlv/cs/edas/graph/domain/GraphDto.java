package edu.unlv.cs.edas.graph.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.util.Assert;

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
	private Collection<V> vertices = new ArrayList<V>();
	
	/**
	 * A collection of all the edges in this graph.
	 */
	private Collection<E> edges = new ArrayList<E>();
	
	/**
	 * Returns all the vertices in this graph.
	 * 
	 * @return All the vertices in this graph.
	 */
	public Collection<V> getVertices() {
		return Collections.unmodifiableCollection(vertices);
	}
	
	public void setVertices(Collection<V> vertices) {
		Assert.notNull(vertices);
		this.vertices = vertices;
	}
	
	public void addVertex(V vertex) {
		Assert.notNull(vertex);
		vertices.add(vertex);
	}
	
	/**
	 * Returns all the edges in this graph.
	 * 
	 * @return All the edges in this graph.
	 */
	public Collection<E> getEdges() {
		return Collections.unmodifiableCollection(edges);
	}
	
	public void setEdges(Collection<E> edges) {
		Assert.notNull(edges);
		this.edges = edges;
	}
	
	public void addEdge(E edge) {
		edges.add(edge);
	}
	
}
