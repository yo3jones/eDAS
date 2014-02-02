package edu.unlv.cs.graph.dom.impl;

import org.w3c.dom.Document;

import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.domain.GraphDomBuilderContext;

/**
 * A simple implementation of {@link GraphDomBuilderContext} that simply holds
 * the document and graph being generated.
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            The key of the vertices of the graph.
 * @param <V>
 *            The vertex values of the graph.
 * @param <E>
 *            The edge values of the graph.
 */
public abstract class AbstractGraphDomBuilderContext<K, V, E> implements 
		GraphDomBuilderContext<K, V, E> {
	
	/**
	 * The DOM document being generated.
	 */
	private Document document;
	
	/**
	 * The graph.
	 */
	private Graph<K, V, E> graph;
	
	/**
	 * Constructs the context with the given document and graph.
	 * 
	 * @param document
	 * @param graph
	 */
	public AbstractGraphDomBuilderContext(Document document, Graph<K, V, E> graph) {
		this.document = document;
		this.graph = graph;
	}
	
	@Override
	public Document getDocument() {
		return document;
	}

	@Override
	public Graph<K, V, E> getGraph() {
		return graph;
	}

}
