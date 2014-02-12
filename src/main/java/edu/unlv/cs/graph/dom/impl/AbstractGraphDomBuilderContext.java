package edu.unlv.cs.graph.dom.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	 * The root element of the document.
	 */
	private Element rootElement;
	
	/**
	 * The graph.
	 */
	private Graph<K, V, E> graph;
	
	/**
	 * Constructs the context with the given document and graph.
	 * 
	 * @param document
	 * @param rootElement
	 * @param graph
	 */
	public AbstractGraphDomBuilderContext(Document document, Element rootElement, 
			Graph<K, V, E> graph) {
		this.document = document;
		this.rootElement = rootElement;
		this.graph = graph;
	}
	
	@Override
	public Document getDocument() {
		return document;
	}
	
	@Override
	public Element getRootElement() {
		return rootElement;
	}
	
	@Override
	public Graph<K, V, E> getGraph() {
		return graph;
	}

}
