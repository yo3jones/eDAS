package edu.unlv.cs.graph;

import java.util.Set;

public interface Graph<K, V, E> {
	
	V putVertex(K key, V vertex);
	
	E putEdge(K fromKey, K toKey, E edge);
	
	V getVertex(K key);
	
	E getEdge(K fromKey, K toKey);
	
	V removeVertex(K key);
	
	E removeEdge(K fromKey, K toKey);
	
	void clear();
	
	Set<K> getAdjacentVertices(K key);
	
	Set<K> getKeySet();
	
	int getVertexSize();
	
	int getEdgeSize();
	
	boolean containsVertex(K key);
	
	boolean containsEdge(K fromKey, K toKey);
	
}
