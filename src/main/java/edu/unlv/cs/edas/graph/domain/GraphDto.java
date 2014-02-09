package edu.unlv.cs.edas.graph.domain;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String, V> vertices = new HashMap<String, V>();
	
	/**
	 * A collection of all the edges in this graph.
	 */
	private Map<String, E> edges = new HashMap<String, E>();
	
	public Map<String, V> getVertices() {
		return vertices;
	}
	
	public void setVertices(Map<String, V> vertices) {
		this.vertices = vertices;
	}
	
	public void addVertex(V vertex) {
		vertices.put(vertex.getId().getId().toString(), vertex);
	}
	
	public Map<String, E> getEdges() {
		return edges;
	}
	
	public void setEdges(Map<String, E> edges) {
		this.edges = edges;
	}
	
	public void addEdge(E edge) {
		edges.put(edge.getId().getFromKey().getId() + "-" + edge.getId().getToKey().getId(), edge);
	}
	
}
