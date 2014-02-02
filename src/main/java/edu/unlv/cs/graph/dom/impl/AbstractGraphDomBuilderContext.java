package edu.unlv.cs.graph.dom.impl;

import org.w3c.dom.Document;

import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.domain.GraphDomBuilderContext;

public abstract class AbstractGraphDomBuilderContext<K, V, E> implements 
		GraphDomBuilderContext<K, V, E> {
	
	private Document document;
	private Graph<K, V, E> graph;
	
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
