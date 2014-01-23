package edu.unlv.cs.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

/**
 * A directional implementation of {@link Graph} that uses java hashing for
 * storage and access of vertices and edges.
 * <p>
 * The following operations all execute in constant time.
 * <ul>
 * <li>{@link #putVertex(Object, Object)}</li>
 * <li>{@link #putEdge(Object, Object, Object)}</li>
 * <li>{@link #getVertex(Object)}</li>
 * <li>{@link #getEdge(Object, Object)}</li>
 * <li>{@link #removeEdge(Object, Object)}</li>
 * <li>{@link #clear()}</li>
 * <li>{@link #getAdjacentVertices(Object)}</li>
 * <li>{@link #getVertexSet()}</li>
 * <li>{@link #getVertexSize()}</li>
 * <li>{@link #getEdgeSize()}</li>
 * <li>{@link #containsVertex(Object)}</li>
 * <li>{@link #containsEdge(Object, Object)}</li>
 * </ul>
 * <p>
 * {@link #removeVertex(Object)} runs in O(n) where n is the number of vertices
 * that are connected to the vertex being removed.
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            {@link Graph} This type must implement all methods for use as a
 *            key in a java hash.
 * @param <V>
 *            {@link Graph}
 * @param <E>
 *            {@link Graph}
 * 
 * @see Graph
 */
public class HashGraph<K, V, E> implements Graph<K, V, E> {
	
	/**
	 * This class simply holds adjacent vertex information for a vertex.
	 * 
	 * @param <K> {@link HashGraph}
	 */
	public static class AdjacentVertices<K> {
		
		/**
		 * A set that holds all vertices that the vertex is directly connected to.
		 */
		private Set<K> adjacentVertices = new HashSet<K>();
		
		/**
		 * A set that holds all vertices that connect to this vertex.
		 */
		private Set<K> reverseAdjacentVertices = new HashSet<K>();
		
		/**
		 * Returns a set that holds all vertices that the vertex is directly
		 * connected to.
		 * 
		 * @return A set that holds all vertices that the vertex is directly
		 *         connected to.
		 */
		public Set<K> getAdjacentVertices() {
			return adjacentVertices;
		}
		
		/**
		 * Returns a set that holds all vertices that are connected to the
		 * vertex.
		 * 
		 * @return A set that holds all vertices that are connected to the
		 *         vertex.
		 */
		public Set<K> getReverseAdjacentVertices() {
			return reverseAdjacentVertices;
		}
		
	}
	
	/**
	 * A mapping between the key that uniquely identifies a vertex and the
	 * vertex's value.
	 */
	private Map<K, V> vertices = new HashMap<K, V>();
	
	/**
	 * A mapping between and {@link HashEdgeKey} and the edge's value.
	 */
	private Map<EdgeKey<K>, E> edges = new HashMap<EdgeKey<K>, E>();
	
	/**
	 * A mapping between a key that uniquely identifies a vertex and the sets of
	 * adjacent vertices. This map is maintained to aid in performance for
	 * operations.
	 * 
	 * @see AdjacentVertices
	 */
	private Map<K, AdjacentVertices<K>> adjacentVertices = new HashMap<K, AdjacentVertices<K>>();
	
	@Override
	public V putVertex(K key, V vertex) throws IllegalArgumentException {
		// first make sure we have valid arguments
		Assert.notNull(key);
		
		if (vertices.containsKey(key)) {
			// this vertex already exists, so just update it's value
			vertices.put(key, vertex);
		}
		
		// add a set for future adjacent vertices
		adjacentVertices.put(key, new AdjacentVertices<K>());
		
		// finally put the vertex in the vertices map
		return vertices.put(key, vertex);
	}

	@Override
	public E putEdge(EdgeKey<K> key, E edge) throws IllegalArgumentException {
		// first make sure we have valid arguments
		Assert.notNull(key);
		if (!vertices.containsKey(key.getFromKey())) {
			throw new IllegalArgumentException("Cannot add edge because the from vertex does not "
					+ "exist. [fromVertex=" + key.getFromKey() + "]");
		}
		if (!vertices.containsKey(key.getToKey())) {
			throw new IllegalArgumentException("Cannot add edge because the to vertex does not "
					+ "exist. [toVertex=" + key.getToKey() + "]");
		}
		
		if (edges.containsKey(key)) {
			// this edge already exists, so just update it's value
			edges.put(key, edge);
		}
		
		// update the adjacent set
		AdjacentVertices<K> fromAdjacentVertices = adjacentVertices.get(key.getFromKey());
		fromAdjacentVertices.getAdjacentVertices().add(key.getToKey());
		
		// update the reverse adjacent set
		AdjacentVertices<K> toAjacentVertices = adjacentVertices.get(key.getToKey());
		toAjacentVertices.getReverseAdjacentVertices().add(key.getFromKey());
		
		// put the edge in the edges map
		return edges.put(key, edge);
	}

	@Override
	public V getVertex(K key) throws IllegalArgumentException {
		Assert.notNull(key);
		return vertices.get(key);
	}

	@Override
	public E getEdge(EdgeKey<K> key) throws IllegalArgumentException {
		Assert.notNull(key);
		return edges.get(key);
	}

	@Override
	public V removeVertex(K key) throws IllegalArgumentException {
		// first verify the argument is valid
		Assert.notNull(key);
		
		if (!vertices.containsKey(key)) {
			// nothing to do, there is no vertex with this key
			return null;
		}
		
		/*
		 * Get a copy of the adjacent vertices and reverse adjacent vertices. We
		 * need to copy because we are removing during the iterations.
		 */
		AdjacentVertices<K> fromAdjacentVertices = this.adjacentVertices.get(key);
		Collection<K> adjacentVertices = new ArrayList<K>(
				fromAdjacentVertices.getAdjacentVertices());
		Collection<K> reverseAdjacentVertices = new ArrayList<K>(
				fromAdjacentVertices.getReverseAdjacentVertices());
		
		// remove the edges for this vertex
		for (K adjacentVertex : adjacentVertices) {
			removeEdge(new EdgeKey<K>(key, adjacentVertex));
		}
		
		// remove the reverse edges for this vertex
		for (K reverseAdjacentVertex : reverseAdjacentVertices) {
			removeEdge(new EdgeKey<K>(reverseAdjacentVertex, key));
		}
		
		// now we can remove the adjacent vertices for this vertex
		this.adjacentVertices.remove(key);
		
		// finally we can remove this vertex
		return vertices.remove(key);
	}
	
	@Override
	public E removeEdge(EdgeKey<K> key) throws IllegalArgumentException {
		// make sure the arguments are valid
		Assert.notNull(key);
		
		if (!vertices.containsKey(key.getFromKey()) || !vertices.containsKey(key.getToKey())) {
			// at least one of the vertices is not in this graph, so nothing to do
			return null;
		}
		
		// remove this edge from the adjacent vertices set
		AdjacentVertices<K> fromAdjacentVertices = adjacentVertices.get(key.getFromKey());
		fromAdjacentVertices.getAdjacentVertices().remove(key.getToKey());
		
		// remove this edge from the reverse adjacent vertices set
		AdjacentVertices<K> toAdjacentVertices = adjacentVertices.get(key.getToKey());
		toAdjacentVertices.getReverseAdjacentVertices().remove(key.getFromKey());
		
		// now we can remove this edge from the edges map
		return edges.remove(key);
	}
	
	@Override
	public void clear() {
		vertices.clear();
		edges.clear();
		adjacentVertices.clear();
	}
	
	@Override
	public Set<K> getAdjacentVertices(K key) throws IllegalArgumentException {
		// verify the argument
		Assert.notNull(key);
		if (!vertices.containsKey(key)) {
			throw new IllegalArgumentException("Cannot get the adjacent vertices for a vertex "
					+ "that is not in this graph. [key=" + key + "]");
		}
		
		return adjacentVertices.get(key).getAdjacentVertices();
	}

	@Override
	public Set<K> getVertexSet() {
		return Collections.unmodifiableSet(vertices.keySet());
	}

	@Override
	public Set<EdgeKey<K>> getEdgeSet() {
		return Collections.unmodifiableSet(edges.keySet());
	}
	
	@Override
	public int getVertexSize() {
		return vertices.size();
	}
	
	@Override
	public int getEdgeSize() {
		return edges.size();
	}
	
	@Override
	public boolean containsVertex(K key) throws IllegalArgumentException {
		Assert.notNull(key);
		return vertices.containsKey(key);
	}
	
	@Override
	public boolean containsEdge(EdgeKey<K> key) throws IllegalArgumentException {
		Assert.notNull(key);
		return edges.containsKey(key);
	}
	
}
