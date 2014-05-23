package edu.unlv.cs.graph;

public abstract class AbstractGraph<K, V, E> implements Graph<K, V, E> {

	public AbstractGraph() {
		initGraph();
	}
	
	public AbstractGraph(Graph<K, V, E> graph) {
		initGraph();
		for (K key : graph.getVertexSet()) {
			putVertex(key, graph.getVertex(key));
		}
		
		for (EdgeKey<K> edgeKey : graph.getEdgeSet()) {
			putEdge(edgeKey, graph.getEdge(edgeKey));
		}
	}
	
	protected abstract void initGraph();
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!Graph.class.isAssignableFrom(obj.getClass())) return false;
		
		@SuppressWarnings("unchecked")
		Graph<Object, Object, Object> that = (Graph<Object, Object, Object>) obj;
		
		if (this.getVertexSize() != that.getVertexSize()) return false;
		if (this.getEdgeSize() != that.getEdgeSize()) return false;
		
		for (K key : this.getVertexSet()) {
			if (!that.containsVertex(key)) return false;
			V thisVertex = this.getVertex(key);
			Object thatVertex = that.getVertex(key);
			if (thisVertex == thatVertex) continue;
			if (thisVertex == null || thatVertex == null) return false;
			if (!thisVertex.equals(thatVertex)) return false;
		}
		
		for (EdgeKey<K> edgeKey : this.getEdgeSet()) {
			@SuppressWarnings("unchecked")
			EdgeKey<Object> thisEdgeKey = (EdgeKey<Object>) edgeKey;
			if (!that.containsEdge(thisEdgeKey)) return false;
			E thisEdge = this.getEdge(edgeKey);
			Object thatEdge = that.getEdge(thisEdgeKey);
			if (thisEdge == thatEdge) continue;
			if (thisEdge == null || thatEdge == null) return false;
			if (!thisEdge.equals(thatEdge)) return false;
		}
		
		return true;
	}
	
}
