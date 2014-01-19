package edu.unlv.cs.graph;

import java.util.Set;

import org.springframework.util.Assert;

/**
 * A graph implementation that wraps a directional graph to make it bidirectional.
 * 
 * @author Chris Jones
 *
 * @param <K>
 * @param <V>
 * @param <E>
 * 
 * @see Graph
 */
public class BiDirectedGraph<K, V, E> implements Graph<K, V, E> {

	/**
	 * The backing graph that actually holds the graph data.
	 */
	private Graph<K, V, E> backingGraph;
	
	/**
	 * Constructs a bidirectional graph using the backing graph.
	 * 
	 * @param backingGraph
	 *            The backing graph.
	 * @throws IllegalArgumentException
	 *             If the backingGraph is null.
	 */
	public BiDirectedGraph(Graph<K, V, E> backingGraph) throws IllegalArgumentException {
		Assert.notNull(backingGraph);
		this.backingGraph = backingGraph;
	}
	
	@Override
	public V putVertex(K key, V vertex) throws IllegalArgumentException {
		return backingGraph.putVertex(key, vertex);
	}

	@Override
	public E putEdge(K fromKey, K toKey, E edge) throws IllegalArgumentException {
		backingGraph.putEdge(toKey, fromKey, edge);
		return backingGraph.putEdge(fromKey, toKey, edge);
	}

	@Override
	public V getVertex(K key) throws IllegalArgumentException {
		return backingGraph.getVertex(key);
	}

	@Override
	public E getEdge(K fromKey, K toKey) throws IllegalArgumentException {
		return backingGraph.getEdge(fromKey, toKey);
	}

	@Override
	public V removeVertex(K key) throws IllegalArgumentException {
		return backingGraph.removeVertex(key);
	}

	@Override
	public E removeEdge(K fromKey, K toKey) throws IllegalArgumentException {
		backingGraph.removeEdge(toKey, fromKey);
		return backingGraph.removeEdge(fromKey, toKey);
	}

	@Override
	public void clear() {
		backingGraph.clear();
	}

	@Override
	public Set<K> getAdjacentVertices(K key) throws IllegalArgumentException {
		return backingGraph.getAdjacentVertices(key);
	}

	@Override
	public Set<K> getKeySet() {
		return backingGraph.getKeySet();
	}

	@Override
	public int getVertexSize() {
		return backingGraph.getVertexSize();
	}

	@Override
	public int getEdgeSize() {
		return backingGraph.getEdgeSize();
	}

	@Override
	public boolean containsVertex(K key) throws IllegalArgumentException {
		return backingGraph.containsVertex(key);
	}

	@Override
	public boolean containsEdge(K fromKey, K toKey) throws IllegalArgumentException {
		return backingGraph.containsEdge(fromKey, toKey);
	}

	/**
	 * Returns the graph that is backing this bidirectional graph.
	 * 
	 * @return The graph that is backing this bidirectional graph.
	 */
	public Graph<K, V, E> getBackingGraph() {
		return backingGraph;
	}
	
}
