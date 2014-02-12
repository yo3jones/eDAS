package edu.unlv.cs.edas.graph.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * A DTO graph for the eDAS application.
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            The vertex key type.
 * @param <V>
 *            The vertex type.
 * @param <E>
 *            The edge type.
 */
public class GraphDto<K, V extends VertexDto, E extends EdgeDto> {

	/**
	 * A map of the vertices of this graph DTO.
	 */
	private Map<String, V> vertices = new HashMap<String, V>();
	
	/**
	 * A map of the edges of this graph DTO.
	 */
	private Map<String, E> edges = new HashMap<String, E>();
	
	/**
	 * Returns the map of vertices of this graph DTO.
	 * 
	 * @return The map of vertices of this graph DTO.
	 */
	public Map<String, V> getVertices() {
		return vertices;
	}
	
	/**
	 * Sets the map of vertices of this graph DTO.
	 * 
	 * @param vertices
	 *            The map of vertices of this graph DTO.
	 */
	public void setVertices(Map<String, V> vertices) {
		this.vertices = vertices;
	}
	
	/**
	 * Returns the map of edges of this graph DTO.
	 * 
	 * @return The map of edges of this graph DTO.
	 */
	public Map<String, E> getEdges() {
		return edges;
	}
	
	/**
	 * Sets the map of edges of this graph DTO.
	 * 
	 * @param edges
	 *            The map of edges of this graph DTO.
	 */
	public void setEdges(Map<String, E> edges) {
		this.edges = edges;
	}
	
	/**
	 * Adds a vertex to this graph DTO.
	 * 
	 * @param vertexKey
	 *            The unique key of the vertex being added.
	 * @param vertex
	 *            The verext being added.
	 */
	public void addVertex(K vertexKey, V vertex) {
		String key = vertexKey.toString();
		vertices.put(key, vertex);
	}
	
	/**
	 * Adds an edge to the graph DTO.
	 * 
	 * @param fromVertexKey
	 *            The key of the from vertex for the edge being added.
	 * @param toVertexKey
	 *            The key of the to vertex for the edge being added.
	 * @param edge
	 *            The edge being added.
	 */
	public void addEdge(K fromVertexKey, K toVertexKey, E edge) {
		String fromKey = fromVertexKey.toString();
		String toKey = toVertexKey.toString();
		String key = fromKey + "-" + toKey;
		edges.put(key, edge);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		GraphDto<?, ?, ?> that = (GraphDto<?, ?, ?>) obj;
		return new EqualsBuilder()
			.append(this.vertices, that.vertices)
			.append(this.edges, that.edges)
			.isEquals();
	}
	
}
