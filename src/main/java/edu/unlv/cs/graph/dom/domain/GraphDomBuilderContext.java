package edu.unlv.cs.graph.dom.domain;

import org.w3c.dom.Document;

import edu.unlv.cs.graph.Graph;

/**
 * A basic DOM builder context that holds the document being generated and the
 * graph.
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            The key of the graph.
 * @param <V>
 *            The vertex value.
 * @param <E>
 *            The edge value.
 * @see GraphDomAdapter
 */
public interface GraphDomBuilderContext<K, V, E> {

	/**
	 * Returns the document being generated.
	 * 
	 * @return The document being generated.
	 */
	Document getDocument();
	
	/**
	 * Returns the graph.
	 * 
	 * @return The graph.
	 */
	Graph<K, V, E> getGraph();
	
}
