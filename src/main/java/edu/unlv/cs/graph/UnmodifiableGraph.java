package edu.unlv.cs.graph;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.util.Set;

import org.apache.commons.collections4.set.UnmodifiableSet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class UnmodifiableGraph<K, V, E> extends AbstractGraph<K, V, E> {

	private static final long serialVersionUID = -2645993244643441617L;
	
	@JsonUnwrapped
	private Graph<K, V, E> graph;
	
	@Override
	protected void initGraph() {
		
	}
	
	public UnmodifiableGraph(Graph<K, V, E> graph) {
		this.graph = graph;
	}
	
	@Override
	public V putVertex(K key, V vertex) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public E putEdge(EdgeKey<K> key, E edge) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public V getVertex(K key) throws IllegalArgumentException {
		return graph.getVertex(key);
	}

	@Override
	public E getEdge(EdgeKey<K> key) throws IllegalArgumentException {
		return graph.getEdge(key);
	}

	@Override
	public V removeVertex(K key) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public E removeEdge(EdgeKey<K> key) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<K> getAdjacentVertices(K key) throws IllegalArgumentException {
		return graph.getAdjacentVertices(key);
	}

	@Override
	public Set<K> getDestinatingAdjacentVertices(K key) throws IllegalArgumentException {
		return graph.getDestinatingAdjacentVertices(key);
	}
	
	@Override
	public Set<K> getVertexSet() {
		return UnmodifiableSet.unmodifiableSet(graph.getVertexSet());
	}

	@Override
	public Set<EdgeKey<K>> getEdgeSet() {
		return UnmodifiableSet.unmodifiableSet(graph.getEdgeSet());
	}

	@Override
	public int getVertexSize() {
		return graph.getVertexSize();
	}

	@Override
	public int getEdgeSize() {
		return graph.getEdgeSize();
	}

	@Override
	public boolean containsVertex(K key) throws IllegalArgumentException {
		return graph.containsVertex(key);
	}

	@Override
	public boolean containsEdge(EdgeKey<K> key) throws IllegalArgumentException {
		return graph.containsEdge(key);
	}

}
