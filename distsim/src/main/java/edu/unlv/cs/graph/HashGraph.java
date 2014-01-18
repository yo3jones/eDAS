package edu.unlv.cs.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.Assert;

public class HashGraph<K, V, E> implements Graph<K, V, E> {

	private static class EdgeKey<K> {
		
		private K fromKey;
		private K toKey;
		
		public EdgeKey(K fromKey, K toKey) {
			this.fromKey = fromKey;
			this.toKey = toKey;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) { return false; }
			if (obj == this) { return true; }
			if (obj.getClass() != getClass()) { return false; }
			EdgeKey<?> that = (EdgeKey<?>) obj;
			return new EqualsBuilder()
					.append(this.fromKey, that.fromKey)
					.append(this.toKey, that.toKey)
					.isEquals();
		}
		
		@Override
		public int hashCode() {
			return new HashCodeBuilder()
					.append(fromKey)
					.append(toKey)
					.toHashCode();
		}
		
	}
	
	public static class AdjacentVertices<K> {
		
		private Set<K> adjacentVertices = new HashSet<K>();
		private Set<K> reverseAdjacentVertices = new HashSet<K>();
		
		public Set<K> getAdjacentVertices() {
			return adjacentVertices;
		}
		
		public Set<K> getReverseAdjacentVertices() {
			return reverseAdjacentVertices;
		}
		
	}
	
	private Map<K, V> vertices = new HashMap<K, V>();
	private Map<EdgeKey<K>, E> edges = new HashMap<EdgeKey<K>, E>();
	private Map<K, AdjacentVertices<K>> adjacentVertices = new HashMap<K, AdjacentVertices<K>>();
	
	@Override
	public V putVertex(K key, V vertex) {
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
	public E putEdge(K fromKey, K toKey, E edge) {
		// first make sure we have valid arguments
		Assert.notNull(fromKey);
		Assert.notNull(toKey);
		if (!vertices.containsKey(fromKey)) {
			throw new IllegalArgumentException("Cannot add edge because the from vertex does not "
					+ "exist. [fromVertex=" + fromKey + "]");
		}
		if (!vertices.containsKey(toKey)) {
			throw new IllegalArgumentException("Cannot add edge because the to vertex does not "
					+ "exist. [toVertex=" + toKey + "]");
		}
		
		// create an edge key
		EdgeKey<K> key = new EdgeKey<K>(fromKey, toKey);
		
		if (edges.containsKey(key)) {
			// this edge already exists, so just update it's value
			edges.put(key, edge);
		}
		
		// update the adjacent set
		AdjacentVertices<K> fromAdjacentVertices = adjacentVertices.get(fromKey);
		fromAdjacentVertices.getAdjacentVertices().add(toKey);
		
		// update the reverse adjacent set
		AdjacentVertices<K> toAjacentVertices = adjacentVertices.get(toKey);
		toAjacentVertices.getReverseAdjacentVertices().add(fromKey);
		
		// put the edge in the edges map
		return edges.put(key, edge);
	}

	@Override
	public V getVertex(K key) {
		Assert.notNull(key);
		return vertices.get(key);
	}

	@Override
	public E getEdge(K fromKey, K toKey) {
		Assert.notNull(fromKey);
		Assert.notNull(toKey);
		EdgeKey<K> key = new EdgeKey<K>(fromKey, toKey);
		return edges.get(key);
	}

	@Override
	public V removeVertex(K key) {
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
			removeEdge(key, adjacentVertex);
		}
		
		// remove the reverse edges for this vertex
		for (K reverseAdjacentVertex : reverseAdjacentVertices) {
			removeEdge(reverseAdjacentVertex, key);
		}
		
		// now we can remove the adjacent vertices for this vertex
		this.adjacentVertices.remove(key);
		
		// finally we can remove this vertex
		return vertices.remove(key);
	}
	
	@Override
	public E removeEdge(K fromKey, K toKey) {
		// make sure the arguments are valid
		Assert.notNull(fromKey);
		Assert.notNull(toKey);
		
		if (!vertices.containsKey(fromKey) || !vertices.containsKey(toKey)) {
			// at least one of the vertices is not in this graph, so nothing to do
			return null;
		}
		
		// remove this edge from the adjacent vertices set
		AdjacentVertices<K> fromAdjacentVertices = adjacentVertices.get(fromKey);
		fromAdjacentVertices.getAdjacentVertices().remove(toKey);
		
		// remove this edge from the reverse adjacent vertices set
		AdjacentVertices<K> toAdjacentVertices = adjacentVertices.get(toKey);
		toAdjacentVertices.getReverseAdjacentVertices().remove(fromKey);
		
		// now we can remove this edge from the edges map
		EdgeKey<K> key = new EdgeKey<K>(fromKey, toKey);
		return edges.remove(key);
	}
	
	@Override
	public void clear() {
		vertices.clear();
		edges.clear();
		adjacentVertices.clear();
	}
	
	@Override
	public Set<K> getAdjacentVertices(K key) {
		// verify the argument
		Assert.notNull(key);
		if (!vertices.containsKey(key)) {
			throw new IllegalArgumentException("Cannot get the adjacent vertices for a vertex "
					+ "that is not in this graph. [key=" + key + "]");
		}
		
		return adjacentVertices.get(key).getAdjacentVertices();
	}

	@Override
	public Set<K> getKeySet() {
		return vertices.keySet();
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
	public boolean containsVertex(K key) {
		Assert.notNull(key);
		return vertices.containsKey(key);
	}
	
	@Override
	public boolean containsEdge(K fromKey, K toKey) {
		Assert.notNull(fromKey);
		Assert.notNull(toKey);
		EdgeKey<K> key = new EdgeKey<K>(fromKey, toKey);
		return edges.containsKey(key);
	}
	
}
