package edu.unlv.cs.graph.dom.domain;

import org.w3c.dom.Document;

import edu.unlv.cs.graph.Graph;

public interface GraphDomBuilderContext<K, V, E> {

	Document getDocument();
	
	Graph<K, V, E> getGraph();
	
}
