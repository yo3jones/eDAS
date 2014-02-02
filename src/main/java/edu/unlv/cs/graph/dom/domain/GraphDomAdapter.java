package edu.unlv.cs.graph.dom.domain;

import org.w3c.dom.Document;

import edu.unlv.cs.graph.Graph;

/**
 * An adapter that will generate a DOM document from a graph.
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            The vertex key of the graph
 * @param <V>
 *            The vertex of the graph
 * @param <E>
 *            The edge of the graph
 * @see Graph
 */
public interface GraphDomAdapter<K, V, E> {

	/**
	 * Returns a DOM document generated from the given graph.
	 * 
	 * @param graph
	 *            The graph used to generate the DOM document.
	 * @return A DOM document generated from the given graph.
	 */
	Document getDomFromGraph(Graph<K, V, E> graph);
	
}
