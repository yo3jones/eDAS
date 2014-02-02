package edu.unlv.cs.graph.dom.domain;

import org.w3c.dom.Document;

import edu.unlv.cs.graph.Graph;

public interface GraphDomAdapter<K, V, E> {

	Document getDomFromGraph(Graph<K, V, E> graph);
	
}
